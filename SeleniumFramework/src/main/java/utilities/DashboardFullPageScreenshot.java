package utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.openqa.selenium.*;
import org.openqa.selenium.io.FileHandler;

public class DashboardFullPageScreenshot {

    public static void captureFullPageScreenshot(WebDriver driver) throws IOException {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Switch to the frame
        driver.switchTo().frame("mainframe");
        System.out.println("Switched to frame: mainframe");

        // Get the calling method name using Java Reflection
        String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();

        // Generate a timestamp
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

        // Define the screenshot save location
        String screenshotDirectory = "D:\\Orbund\\Automation\\Screenshots\\FullPageScrolling";

        // Ensure the directory exists
        File directory = new File(screenshotDirectory);
        if (!directory.exists()) {
            directory.mkdirs();  // Create directories if not present
        }

        // Get total page height and viewport height within the frame
        long pageHeight = (long) js.executeScript("return document.body.scrollHeight;");
        long viewportHeight = (long) js.executeScript("return window.innerHeight;");
        long scrollPosition = 0;

        int screenshotIndex = 1;

        while (scrollPosition < pageHeight) {
            // Take a screenshot of the current viewport inside the frame
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            // Create a filename with the method name, timestamp, and index
            String screenshotFilename = methodName + "_Part" + screenshotIndex + "_" + timestamp + ".png";
            File destinationFile = new File(screenshotDirectory + "\\" + screenshotFilename);

            // Save the screenshot
            FileHandler.copy(screenshot, destinationFile);
            System.out.println("Saved Screenshot: " + destinationFile.getAbsolutePath());

            // Scroll down inside the frame
            scrollPosition += viewportHeight;
            js.executeScript("window.scrollTo(0, " + scrollPosition + ");");

            // Wait for scrolling to take effect
            try {
                Thread.sleep(500); // Adjust delay as needed
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            screenshotIndex++;
        }

        System.out.println("Full-page screenshots saved for function: " + methodName);

        // Switch back to the main window
        driver.switchTo().defaultContent();
        System.out.println("Switched back to main content");
    }
}
