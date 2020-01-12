package ManagerFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FireFoxDriverManager implements DriverManager {
    WebDriver driver;
    private String path = System.getProperty("user.dir");
    private String driverPath = path = "\\driver\\";

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver() {
        System.setProperty("webdriver.gecko.driver", driverPath + "geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        this.driver = driver;

    }
}
