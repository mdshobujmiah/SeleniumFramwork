package testcases;

import config.DriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import utilities.FullPageForEachFeature;
import utilities.VisiblePageScreenshot;
import java.io.IOException;
import java.time.Duration;
import java.util.Set;

public class ReportsTest {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setUp() {
        driver = DriverManager.getDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    @Test(priority = 2, dependsOnMethods = "testcases.LoginTest.testValidLogin")
    public void StandardReports() {
        try {
            // Open the Side Menu
            WebElement toggler = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"main-menu-toggler\"]/i")));    
            toggler.click();
            System.out.println("Toggler clicked.");

            // Click Reports Tab
            WebElement reportsTab = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"headingAQUERYREPORTS\"]/button")));
            reportsTab.click();
            System.out.println("Reports tab opened.");

            // Expand the Report Dropdown
            WebElement menuFrame = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"menuAQUERYREPORTS\"]/div/a[3]")));

            // Scroll down to make the element visible
            Actions actions = new Actions(driver);
            actions.scrollToElement(menuFrame).perform();
            System.out.println("Scrolled to Standard Reports.");

            // Click to open Standard Report List
            menuFrame.click();
            System.out.println("Standard Reports list opened.");
            Thread.sleep(3000);

            // ✅ Fix: Pass method name as a string
            VisiblePageScreenshot.captureVisiblePageScreenshot(driver, "StandardReports");

            Assert.assertTrue(true, "StandardReports test passed!");

        } catch (IOException e) {
            e.printStackTrace();
            Assert.fail("Screenshot capture failed!");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("StandardReports test failed: " + e.getMessage());
        }
    }
    @Test(dependsOnMethods = "testcases.LoginTest.testValidLogin",enabled = true, priority = 3)
    public void R1_Studentenrollmentbynationality() throws IOException {
    	try {
    	//Switch to main frame I mean body
        driver.switchTo().frame("mainframe");
        // Store the main window handle
        String mainWindow = driver.getWindowHandle();
        // Click on a hyperlink that opens a new tab for Report
        WebElement hyperLinkReport=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"searchable\"]/tr[1]/td[2]/a")));
        hyperLinkReport.click();
        // Get all window handles
        Set<String> windowHandles = driver.getWindowHandles();
        String reportWindow = null;

        // Switch to the new report tab
        for (String handle : windowHandles) {
            if (!handle.equals(mainWindow)) {
                reportWindow = handle;
                driver.switchTo().window(reportWindow);
                break;
            }
        }

        // Perform actions in the report tab
        System.out.println("New Report title: " + driver.getCurrentUrl());

        // Click on Print (which opens a new tab)
        WebElement clickPrint=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div/div[1]/div[2]/a[1]/i")));
        clickPrint.click();
        // Get all window handles again
        Set<String> windowHandlesAfterPrint = driver.getWindowHandles();
        String printWindow = null;

        // Switch to the new print tab
        for (String handle : windowHandlesAfterPrint) {
            if (!handle.equals(mainWindow) && !handle.equals(reportWindow)) {
                printWindow = handle;
                driver.switchTo().window(printWindow);
                break;
            }
        }

        // Perform actions in the print tab
        System.out.println("New Print Report title: " + driver.getCurrentUrl());
        //Take Screenshots
        FullPageForEachFeature.captureFullPageScreenshot1(driver);
        // Close the print tab
        driver.close();

        // Switch back to the report tab
        driver.switchTo().window(reportWindow);

        // Close the report tab
        driver.close();

        // Switch back to the main window
        driver.switchTo().window(mainWindow);
    	
        Assert.assertTrue(true, "Student enrollment by nationality test passed!");
        } 
    	catch (Exception e) {
    	Assert.fail("Student enrollment by nationality test failed: " + e.getMessage());
        }
    }
    
    @Test(dependsOnMethods="testcases.LoginTest.testValidLogin",enabled=true,priority=4)
    public void R2_Listofstudentsenrolledbyyear() throws IOException {
    	try {
    	//Switch to main frame I mean body
        driver.switchTo().frame("mainframe");
        // Store the main window handle
        String mainWindow = driver.getWindowHandle();
        // Click on a hyperlink that opens a new tab for Report
        WebElement hyperLinkReport=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"searchable\"]/tr[2]/td[2]/a")));
        hyperLinkReport.click();
        // Get all window handles
        Set<String> windowHandles = driver.getWindowHandles();
        String reportWindow = null;

        // Switch to the new report tab
        for (String handle : windowHandles) {
            if (!handle.equals(mainWindow)) {
                reportWindow = handle;
                driver.switchTo().window(reportWindow);
                break;
            }
        }

        // Perform actions in the report tab
        System.out.println("New Report title: " + driver.getCurrentUrl());

        // Click on Print (which opens a new tab)
        WebElement clickPrint=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"print-button\"]/i")));
        clickPrint.click();
        // Get all window handles again
        Set<String> windowHandlesAfterPrint = driver.getWindowHandles();
        String printWindow = null;

        // Switch to the new print tab
        for (String handle : windowHandlesAfterPrint) {
            if (!handle.equals(mainWindow) && !handle.equals(reportWindow)) {
                printWindow = handle;
                driver.switchTo().window(printWindow);
                break;
            }
        }

        // Perform actions in the print tab
        System.out.println("New Print Report title: " + driver.getCurrentUrl());
        //Take Screenshots
        FullPageForEachFeature.captureFullPageScreenshot1(driver);
        // Close the print tab
        driver.close();

        // Switch back to the report tab
        driver.switchTo().window(reportWindow);

        // Close the report tab
        driver.close();

        // Switch back to the main window
        driver.switchTo().window(mainWindow);
        Assert.assertTrue(true, "List of students enrolled by year test passed!");
        } 
    	catch (Exception e) {
        Assert.fail("List of students enrolled by year test failed: " + e.getMessage());
        }
    }
    
    @Test(dependsOnMethods="testcases.LoginTest.testValidLogin",enabled=true,priority=5)
    public void R3_Listofstudentsnotpassed() throws IOException {
    	try {
    	//Switch to main frame I mean body
        driver.switchTo().frame("mainframe");
        // Store the main window handle
        String mainWindow = driver.getWindowHandle();
        // Click on a hyperlink that opens a new tab for Report
        WebElement hyperLinkReport = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"searchable\"]/tr[3]/td[2]/a")));
        hyperLinkReport.click();
        // Get all window handles
        Set<String> windowHandles = driver.getWindowHandles();
        String reportWindow = null;

        // Switch to the new report tab
        for (String handle : windowHandles) {
            if (!handle.equals(mainWindow)) {
                reportWindow = handle;
                driver.switchTo().window(reportWindow);
                break;
            }
        }

        // Perform actions in the report tab
        System.out.println("New Report title: " + driver.getCurrentUrl());

        // Click on Print (which opens a new tab)
        WebElement clickPrint = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"print-button\"]/i")));
        clickPrint.click();

        // Get all window handles again
        Set<String> windowHandlesAfterPrint = driver.getWindowHandles();
        String printWindow = null;

        // Switch to the new print tab
        for (String handle : windowHandlesAfterPrint) {
            if (!handle.equals(mainWindow) && !handle.equals(reportWindow)) {
                printWindow = handle;
                driver.switchTo().window(printWindow);
                break;
            }
        }

        // Perform actions in the print tab
        System.out.println("New Print Report title: " + driver.getCurrentUrl());
        //Take Screenshots
        FullPageForEachFeature.captureFullPageScreenshot1(driver);
        // Close the print tab
        driver.close();

        // Switch back to the report tab
        driver.switchTo().window(reportWindow);

        // Close the report tab
        driver.close();

        // Switch back to the main window
        driver.switchTo().window(mainWindow);
        Assert.assertTrue(true, "List of students not passed test passed!");
        } 
    	catch (Exception e) {
        Assert.fail("List of students not passed test failed: " + e.getMessage());
        }
    }

    @Test(dependsOnMethods="testcases.LoginTest.testValidLogin",enabled=true,priority=6)
    public void R4_StudentsemesterGPAreport() throws IOException {
    	try {
    	//Switch to main frame I mean body
        driver.switchTo().frame("mainframe");
        // Store the main window handle
        String mainWindow = driver.getWindowHandle();
        // Click on a hyperlink that opens a new tab for Report
        WebElement hyperLinkReport = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"searchable\"]/tr[4]/td[2]/a")));
        hyperLinkReport.click();
        // Get all window handles
        Set<String> windowHandles = driver.getWindowHandles();
        String reportWindow = null;

        // Switch to the new report tab
        for (String handle : windowHandles) {
            if (!handle.equals(mainWindow)) {
                reportWindow = handle;
                driver.switchTo().window(reportWindow);
                break;
            }
        }

        // Perform actions in the report tab
        System.out.println("New Report title: " + driver.getCurrentUrl());

        // Click on Print (which opens a new tab)
        WebElement clickPrint = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"frmreportbuilder\"]/input[10]")));
        clickPrint.click();

        // Get all window handles again
        Set<String> windowHandlesAfterPrint = driver.getWindowHandles();
        String printWindow = null;

        // Switch to the new print tab
        for (String handle : windowHandlesAfterPrint) {
            if (!handle.equals(mainWindow) && !handle.equals(reportWindow)) {
                printWindow = handle;
                driver.switchTo().window(printWindow);
                break;
            }
        }

        // Perform actions in the print tab
        System.out.println("New Print Report title: " + driver.getCurrentUrl());
        //Take Screenshots
        FullPageForEachFeature.captureFullPageScreenshot1(driver);
        // Close the print tab
        driver.close();

        // Switch back to the report tab
        driver.switchTo().window(reportWindow);
        
        driver.findElement(By.xpath("//td[@class='rta']/input[@value='Export to Excel']")).click();

        // Close the report tab
        driver.close();

        // Switch back to the main window
        driver.switchTo().window(mainWindow);
     // Get the current method name dynamically
        String methodName1 = new Object(){}.getClass().getEnclosingMethod().getName();
        Assert.assertTrue(true, methodName1 + " test passed!");
        } 
    	catch (Exception e) {
    		// Get the current method name dynamically
    	    String methodName = new Object(){}.getClass().getEnclosingMethod().getName();

    	    // Assert failure with dynamic method name
    	    Assert.fail(methodName + " test failed: " + e.getMessage());
        }
    }

    @Test(dependsOnMethods="testcases.LoginTest.testValidLogin",enabled=true,priority=7)
    public void R5_StudentYearlyGPAreport() throws IOException {
    	try {
    	//Switch to main frame I mean body
        driver.switchTo().frame("mainframe");
        // Store the main window handle
        String mainWindow = driver.getWindowHandle();
        // Click on a hyperlink that opens a new tab for Report
        WebElement hyperLinkReport = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"searchable\"]/tr[5]/td[2]/a")));
        hyperLinkReport.click();
        // Get all window handles
        Set<String> windowHandles = driver.getWindowHandles();
        String reportWindow = null;

        // Switch to the new report tab
        for (String handle : windowHandles) {
            if (!handle.equals(mainWindow)) {
                reportWindow = handle;
                driver.switchTo().window(reportWindow);
                break;
            }
        }

        // Perform actions in the report tab
        System.out.println("New Report title: " + driver.getCurrentUrl());

        // Click on Print (which opens a new tab)
        WebElement clickPrint = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"printReport\"]/i")));
        clickPrint.click();

        // Get all window handles again
        Set<String> windowHandlesAfterPrint = driver.getWindowHandles();
        String printWindow = null;

        // Switch to the new print tab
        for (String handle : windowHandlesAfterPrint) {
            if (!handle.equals(mainWindow) && !handle.equals(reportWindow)) {
                printWindow = handle;
                driver.switchTo().window(printWindow);
                break;
            }
        }

        // Perform actions in the print tab
        System.out.println("New Print Report title: " + driver.getCurrentUrl());
        //Take Screenshots
        FullPageForEachFeature.captureFullPageScreenshot1(driver);
        // Close the print tab
        driver.close();

        // Switch back to the report tab
        driver.switchTo().window(reportWindow);

        // Close the report tab
        driver.close();

        // Switch back to the main window
        driver.switchTo().window(mainWindow);
     // Get the current method name dynamically
        String methodName1 = new Object(){}.getClass().getEnclosingMethod().getName();
        Assert.assertTrue(true, methodName1 + " test passed!");
        } 
    	catch (Exception e) {
    		// Get the current method name dynamically
    	    String methodName = new Object(){}.getClass().getEnclosingMethod().getName();

    	    // Assert failure with dynamic method name
    	    Assert.fail(methodName + " test failed: " + e.getMessage());
        }
    }
    
    @Test(dependsOnMethods="testcases.LoginTest.testValidLogin",enabled=true,priority=8)
    public void R6_StudentCGPAreport() throws IOException {
    	try {
    	//Switch to main frame I mean body
        driver.switchTo().frame("mainframe");
        // Store the main window handle
        String mainWindow = driver.getWindowHandle();
        // Click on a hyperlink that opens a new tab for Report
        WebElement hyperLinkReport=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"searchable\"]/tr[6]/td[2]/a")));
        hyperLinkReport.click();
        // Get all window handles
        Set<String> windowHandles = driver.getWindowHandles();
        String reportWindow = null;

        // Switch to the new report tab
        for (String handle : windowHandles) {
            if (!handle.equals(mainWindow)) {
                reportWindow = handle;
                driver.switchTo().window(reportWindow);
                break;
            }
        }

        // Perform actions in the report tab
        System.out.println("New Report title: " + driver.getCurrentUrl());

        // Click on Print (which opens a new tab)
        driver.findElement(By.xpath("//*[@id=\"frmreportbuilder\"]/a")).click();

        // Get all window handles again
        Set<String> windowHandlesAfterPrint = driver.getWindowHandles();
        String printWindow = null;

        // Switch to the new print tab
        for (String handle : windowHandlesAfterPrint) {
            if (!handle.equals(mainWindow) && !handle.equals(reportWindow)) {
                printWindow = handle;
                driver.switchTo().window(printWindow);
                break;
            }
        }

        // Perform actions in the print tab
        System.out.println("New Print Report title: " + driver.getCurrentUrl());
        //Take Screenshots
        FullPageForEachFeature.captureFullPageScreenshot1(driver);
        // Close the print tab
        driver.close();

        // Switch back to the report tab
        driver.switchTo().window(reportWindow);
        // Download in Excel
        WebElement clickDownload=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div/div[2]/div[2]/table/tbody/tr/td/div/a")));
        clickDownload.click();
        // Close the report tab
        driver.close();

        // Switch back to the main window
        driver.switchTo().window(mainWindow);
     // Get the current method name dynamically
        String methodName1 = new Object(){}.getClass().getEnclosingMethod().getName();
        Assert.assertTrue(true, methodName1 + " test passed!");
        } 
    	catch (Exception e) {
    		// Get the current method name dynamically
    	    String methodName = new Object(){}.getClass().getEnclosingMethod().getName();

    	    // Assert failure with dynamic method name
    	    Assert.fail(methodName + " test failed: " + e.getMessage());
        }
    }

    @Test(dependsOnMethods="testcases.LoginTest.testValidLogin",enabled=true,priority=9)
    public void R7C_Listofallcoursesremainingtograduate() throws IOException {
    	try {
    	//Switch to main frame I mean body
        driver.switchTo().frame("mainframe");
        // Store the main window handle
        String mainWindow = driver.getWindowHandle();
        // Click on a hyperlink that opens a new tab for Report
        WebElement hyperLinkReport=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"searchable\"]/tr[7]/td[2]/a")));
        hyperLinkReport.click();
        // Get all window handles
        Set<String> windowHandles = driver.getWindowHandles();
        String reportWindow = null;

        // Switch to the new report tab
        for (String handle : windowHandles) {
            if (!handle.equals(mainWindow)) {
                reportWindow = handle;
                driver.switchTo().window(reportWindow);
                break;
            }
        }

        // Perform actions in the report tab
        System.out.println("New Report title: " + driver.getCurrentUrl());

        // Click on Print (which opens a new tab)
        WebElement clickPrint=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div/div[1]/div[2]/a[1]/i")));
        clickPrint.click();

        // Get all window handles again
        Set<String> windowHandlesAfterPrint = driver.getWindowHandles();
        String printWindow = null;

        // Switch to the new print tab
        for (String handle : windowHandlesAfterPrint) {
            if (!handle.equals(mainWindow) && !handle.equals(reportWindow)) {
                printWindow = handle;
                driver.switchTo().window(printWindow);
                break;
            }
        }

        // Perform actions in the print tab
        System.out.println("New Print Report title: " + driver.getCurrentUrl());
        //Take Screenshots
        FullPageForEachFeature.captureFullPageScreenshot1(driver);
        // Close the print tab
        driver.close();

        // Switch back to the report tab
        driver.switchTo().window(reportWindow);
        // Download in Excel
        //WebElement clickDownload=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div/div[2]/div[2]/table/tbody/tr/td/div/a")));
        //clickDownload.click();
        // Close the report tab
        driver.close();

        // Switch back to the main window
        driver.switchTo().window(mainWindow);
     // Get the current method name dynamically
        String methodName1 = new Object(){}.getClass().getEnclosingMethod().getName();
        Assert.assertTrue(true, methodName1 + " test passed!");
        } 
    	catch (Exception e) {
    		// Get the current method name dynamically
    	    String methodName = new Object(){}.getClass().getEnclosingMethod().getName();

    	    // Assert failure with dynamic method name
    	    Assert.fail(methodName + " test failed: " + e.getMessage());
        }
    }
    
    @Test(dependsOnMethods="testcases.LoginTest.testValidLogin",enabled=true,priority=10)
    public void R7B_Listofcoursesremainingtograduate() throws IOException {
    	try {
    	//Switch to main frame I mean body
        driver.switchTo().frame("mainframe");
        // Store the main window handle
        String mainWindow = driver.getWindowHandle();
        // Click on a hyperlink that opens a new tab for Report
        WebElement hyperLinkReport=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"searchable\"]/tr[8]/td[2]/a")));
        hyperLinkReport.click();
        // Get all window handles
        Set<String> windowHandles = driver.getWindowHandles();
        String reportWindow = null;

        // Switch to the new report tab
        for (String handle : windowHandles) {
            if (!handle.equals(mainWindow)) {
                reportWindow = handle;
                driver.switchTo().window(reportWindow);
                break;
            }
        }

        // Perform actions in the report tab
        System.out.println("New Report title: " + driver.getCurrentUrl());

        // Click on Print (which opens a new tab)
        WebElement clickPrint=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div/div[1]/div[2]/a[1]/i")));
        clickPrint.click();

        // Get all window handles again
        Set<String> windowHandlesAfterPrint = driver.getWindowHandles();
        String printWindow = null;

        // Switch to the new print tab
        for (String handle : windowHandlesAfterPrint) {
            if (!handle.equals(mainWindow) && !handle.equals(reportWindow)) {
                printWindow = handle;
                driver.switchTo().window(printWindow);
                break;
            }
        }

        // Perform actions in the print tab
        System.out.println("New Print Report title: " + driver.getCurrentUrl());
        //Take Screenshots
        FullPageForEachFeature.captureFullPageScreenshot1(driver);
        // Close the print tab
        driver.close();

        // Switch back to the report tab
        driver.switchTo().window(reportWindow);
        // Download in Excel
        //WebElement clickDownload=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div/div[2]/div[2]/table/tbody/tr/td/div/a")));
        //clickDownload.click();
        // Close the report tab
        driver.close();

        // Switch back to the main window
        driver.switchTo().window(mainWindow);
     // Get the current method name dynamically
        String methodName1 = new Object(){}.getClass().getEnclosingMethod().getName();
        Assert.assertTrue(true, methodName1 + " test passed!");
        } 
    	catch (Exception e) {
    		// Get the current method name dynamically
    	    String methodName = new Object(){}.getClass().getEnclosingMethod().getName();

    	    // Assert failure with dynamic method name
    	    Assert.fail(methodName + " test failed: " + e.getMessage());
        }
    }
    
    @Test(dependsOnMethods="testcases.LoginTest.testValidLogin",enabled=true,priority=11)
    public void R7A_Listofcoursesremainingtograduate() throws IOException {
    	try {
    	//Switch to main frame I mean body
        driver.switchTo().frame("mainframe");
        // Store the main window handle
        String mainWindow = driver.getWindowHandle();
        // Click on a hyperlink that opens a new tab for Report
        WebElement hyperLinkReport=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"searchable\"]/tr[9]/td[2]/a")));
        hyperLinkReport.click();
        // Get all window handles
        Set<String> windowHandles = driver.getWindowHandles();
        String reportWindow = null;

        // Switch to the new report tab
        for (String handle : windowHandles) {
            if (!handle.equals(mainWindow)) {
                reportWindow = handle;
                driver.switchTo().window(reportWindow);
                break;
            }
        }

        // Perform actions in the report tab
        System.out.println("New Report title: " + driver.getCurrentUrl());

        // Click on Print (which opens a new tab)
        WebElement clickPrint=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div/div[1]/div[2]/a[1]/i")));
        clickPrint.click();

        // Get all window handles again
        Set<String> windowHandlesAfterPrint = driver.getWindowHandles();
        String printWindow = null;

        // Switch to the new print tab
        for (String handle : windowHandlesAfterPrint) {
            if (!handle.equals(mainWindow) && !handle.equals(reportWindow)) {
                printWindow = handle;
                driver.switchTo().window(printWindow);
                break;
            }
        }

        // Perform actions in the print tab
        System.out.println("New Print Report title: " + driver.getCurrentUrl());
        //Take Screenshots
        FullPageForEachFeature.captureFullPageScreenshot1(driver);
        // Close the print tab
        driver.close();

        // Switch back to the report tab
        driver.switchTo().window(reportWindow);
        // Download in Excel
        //WebElement clickDownload=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div/div[2]/div[2]/table/tbody/tr/td/div/a")));
        //clickDownload.click();
        // Close the report tab
        driver.close();

        // Switch back to the main window
        driver.switchTo().window(mainWindow);
     // Get the current method name dynamically
        String methodName1 = new Object(){}.getClass().getEnclosingMethod().getName();
        Assert.assertTrue(true, methodName1 + " test passed!");
        } 
    	catch (Exception e) {
    		// Get the current method name dynamically
    	    String methodName = new Object(){}.getClass().getEnclosingMethod().getName();

    	    // Assert failure with dynamic method name
    	    Assert.fail(methodName + " test failed: " + e.getMessage());
        }
    }
    
    @Test(dependsOnMethods="testcases.LoginTest.testValidLogin",enabled=true,priority=12)
    public void R8_StudentsGraduatingInSpring2025() throws IOException {
    	try {
    	//Switch to main frame I mean body
        driver.switchTo().frame("mainframe");
        // Store the main window handle
        String mainWindow = driver.getWindowHandle();
        // Click on a hyperlink that opens a new tab for Report
        WebElement hyperLinkReport=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"searchable\"]/tr[10]/td[2]/a")));
        hyperLinkReport.click();
        // Get all window handles
        Set<String> windowHandles = driver.getWindowHandles();
        String reportWindow = null;

        // Switch to the new report tab
        for (String handle : windowHandles) {
            if (!handle.equals(mainWindow)) {
                reportWindow = handle;
                driver.switchTo().window(reportWindow);
                break;
            }
        }

        // Perform actions in the report tab
        System.out.println("New Report title: " + driver.getCurrentUrl());

        // Click on Print (which opens a new tab)
        WebElement clickPrint=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div/div[1]/div[2]/a[1]/i")));
        clickPrint.click();

        // Get all window handles again
        Set<String> windowHandlesAfterPrint = driver.getWindowHandles();
        String printWindow = null;

        // Switch to the new print tab
        for (String handle : windowHandlesAfterPrint) {
            if (!handle.equals(mainWindow) && !handle.equals(reportWindow)) {
                printWindow = handle;
                driver.switchTo().window(printWindow);
                break;
            }
        }

        // Perform actions in the print tab
        System.out.println("New Print Report title: " + driver.getCurrentUrl());
        //Take Screenshots
        FullPageForEachFeature.captureFullPageScreenshot1(driver);
        // Close the print tab
        driver.close();

        // Switch back to the report tab
        driver.switchTo().window(reportWindow);
        // Download in Excel
        //WebElement clickDownload=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div/div[2]/div[2]/table/tbody/tr/td/div/a")));
        //clickDownload.click();
        // Close the report tab
        driver.close();

        // Switch back to the main window
        driver.switchTo().window(mainWindow);
     // Get the current method name dynamically
        String methodName1 = new Object(){}.getClass().getEnclosingMethod().getName();
        Assert.assertTrue(true, methodName1 + " test passed!");
        } 
    	catch (Exception e) {
    		// Get the current method name dynamically
    	    String methodName = new Object(){}.getClass().getEnclosingMethod().getName();

    	    // Assert failure with dynamic method name
    	    Assert.fail(methodName + " test failed: " + e.getMessage());
        }
    }
    
    @Test(dependsOnMethods="testcases.LoginTest.testValidLogin",enabled=true,priority=13)
    public void R9_StudentsNeedingLessThan12CreditsToGraduate() throws IOException {
    	try {
    	//Switch to main frame I mean body
        driver.switchTo().frame("mainframe");
        // Store the main window handle
        String mainWindow = driver.getWindowHandle();
        // Click on a hyperlink that opens a new tab for Report
        WebElement hyperLinkReport=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"searchable\"]/tr[11]/td[2]/a")));
        hyperLinkReport.click();
        // Get all window handles
        Set<String> windowHandles = driver.getWindowHandles();
        String reportWindow = null;

        // Switch to the new report tab
        for (String handle : windowHandles) {
            if (!handle.equals(mainWindow)) {
                reportWindow = handle;
                driver.switchTo().window(reportWindow);
                break;
            }
        }

        // Perform actions in the report tab
        System.out.println("New Report title: " + driver.getCurrentUrl());

        // ScrollDown & Generate Report
        WebElement generateReport = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"selectfrm\"]/div/table/tbody/tr[9]/td[2]/input")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", generateReport);
        generateReport.click();
        // Get all window handles again
        Set<String> windowHandlesAfterGenerateReport = driver.getWindowHandles();
        String generateReportWindow = null;

        // Switch to the new print tab
        for (String handle : windowHandlesAfterGenerateReport) {
            if (!handle.equals(mainWindow) && !handle.equals(reportWindow)){
            	generateReportWindow = handle;
                driver.switchTo().window(generateReportWindow);
                break;
            }
        }
        System.out.println("New Print Report title: " + driver.getCurrentUrl());
        //Take Screenshots
        //FullPageForEachFeature.captureFullPageScreenshot1(driver);
        //Click on Print
        //WebElement clickPrint = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[3]/div/div[1]/div[2]/button[1]/i")));
        //clickPrint.click();
        FullPageForEachFeature.captureFullPageScreenshot1(driver);
        //Click DOwnload
        WebElement clickDownload=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[3]/div/div[1]/div[2]/button[2]/i")));
        clickDownload.click();
        // Switch back to the report tab
        driver.switchTo().window(reportWindow);
        // Download in Excel
        //WebElement clickDownload=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div/div[2]/div[2]/table/tbody/tr/td/div/a")));
        //clickDownload.click();
        // Close the report tab
        driver.close();

        // Switch back to the main window
        driver.switchTo().window(mainWindow);
     // Get the current method name dynamically
        String methodName1 = new Object(){}.getClass().getEnclosingMethod().getName();
        Assert.assertTrue(true, methodName1 + " test passed!");
        } 
    	catch (Exception e) {
    		// Get the current method name dynamically
    	    String methodName = new Object(){}.getClass().getEnclosingMethod().getName();

    	    // Assert failure with dynamic method name
    	    Assert.fail(methodName + " test failed: " + e.getMessage());
        }
    }
    
    @AfterClass
    public void tearDown() {
       DriverManager.quitDriver();
    }
}
