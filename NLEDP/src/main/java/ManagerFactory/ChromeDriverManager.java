package ManagerFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ChromeDriverManager implements DriverManager {

    WebDriver driver;
    private String path = System.getProperty("user.dir");
    private String driverPath = path + "\\driver\\";

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver() {
        System.setProperty("webdriver.chrome.driver", driverPath + "chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        this.driver = driver;
    }
}
