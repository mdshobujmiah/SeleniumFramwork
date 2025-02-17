package utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.openqa.selenium.*;
import org.openqa.selenium.io.FileHandler;

public class VisiblePageScreenshot {

    // Define the screenshot save location
    private static final String SCREENSHOT_DIR = "D:\\Orbund\\Automation\\Screenshots\\VisiblePages";

    public static void captureVisiblePageScreenshot(WebDriver driver, String methodName) throws IOException {
        // Ensure the directory exists
        File directory = new File(SCREENSHOT_DIR);
        if (!directory.exists()) {
            directory.mkdirs();  // Create directory if not present
        }

        // Take screenshot of the visible viewport
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        // Generate a timestamp
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

        // Create a unique filename with method name and timestamp
        String screenshotFilename = methodName + "_" + timestamp + ".png";
        File destinationFile = new File(SCREENSHOT_DIR + "\\" + screenshotFilename);

        // Save the screenshot
        FileHandler.copy(screenshot, destinationFile);
        System.out.println("Visible Page Screenshot Saved: " + destinationFile.getAbsolutePath());
    }
}
