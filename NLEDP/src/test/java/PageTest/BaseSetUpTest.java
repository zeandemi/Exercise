package PageTest;

import BaseSetUp.BrowserSetUp;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

public class BaseSetUpTest {

    BrowserSetUp browserSetUp = new BrowserSetUp();
    public  static WebDriver driver;

    @BeforeTest
    @Parameters({"driverType"})
    public void startBrowser(String driverType){
        driver = browserSetUp.startBrowser(driverType);
    }

    @AfterTest
    public void quitBrowser(){
        browserSetUp.closeBrowser();
    }

    public static WebDriver getDriver(){
        return driver;
    }
}
