package testcases;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import config.DriverManager;
import pages.LoginPage;
import utilities.DashboardFullPageScreenshot;
import utilities.VisiblePageScreenshot;

public class LoginTest {
    WebDriver driver;
    LoginPage loginPage;
    String baseURL="https://qa1.studymoo.com/einstein-freshair/touch/index.jsp";
    String username="test.admin19";
    String password="Security!_Test@QA@!";
    String role="Administrator";

    @BeforeClass
    public void setUp() {
        driver = DriverManager.getDriver();
        driver.get(baseURL);
        loginPage = new LoginPage(driver);
    }

    @Test(priority = 1)
    public void testValidLogin() throws InterruptedException {
        try {
            // Perform login
            loginPage.login(username, password, role);

            // Take screenshot after login
            Thread.sleep(2000);
            //DashboardFullPageScreenshot.captureFullPageScreenshot(driver);
            // ✅ Fix: Pass method name as a string
            VisiblePageScreenshot.captureVisiblePageScreenshot(driver, "StandardReports");

            // Validate login success
            Assert.assertTrue(driver.getTitle().contains("Aspire"), "Login failed!");
        } catch (IOException e) {
            e.printStackTrace();
            Assert.fail("Screenshot capture failed!");
        }
    }

    @AfterClass
    public void tearDown() {
        // Do NOT quit the driver here to keep the session active for ReportsTest
        System.out.println("LoginTest completed. Keeping the session active for ReportsTest.");
    }
}
