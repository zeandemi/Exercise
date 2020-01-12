package BaseSetUp;

import ManagerFactory.DriverFactoryManager;
import org.openqa.selenium.WebDriver;

public class BrowserSetUp {
    private WebDriver driver;

    public WebDriver startBrowser(String driverType){
        DriverFactoryManager driverFactoryManager = new DriverFactoryManager();
        driverFactoryManager.initDriver(driverType);
        driver = driverFactoryManager.getDriver();
        String appUrl = "http://www.wikipedia.org/";
        driver.navigate().to(appUrl);
        driver.manage().window().fullscreen();
        return driver;
    }

    public void closeBrowser(){
        driver.quit();

    }
}
