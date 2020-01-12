package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {

    public static WebDriver driver;
    WebDriverWait wait;
    private static final long waitTime = 20L ;
    private WebElement element;


    public BasePage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(this.driver, waitTime);
    }

    public WebElement displayedElement(WebElement webElement){
        wait.until(ExpectedConditions.visibilityOf(webElement));
        return webElement;
    }

    public Boolean isElementDisplayed(){
        return this.displayedElement(element).isEnabled();
    }

    public void click(WebElement element){
        this.element = element;
        try {
            if (isElementDisplayed()){
                element.click();
            }else {
                System.out.println("Element not presence");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void sendText(WebElement element, String message){
        this.element = element;
        try {
            if (isElementDisplayed()){
                element.sendKeys(message);
            }else {
                System.out.println("Element not presence");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public String getText(WebElement element) {
        String message = null;
        this.element = element;
        try {
            if (isElementDisplayed()) {
                message = element.getText();
            } else {
                System.out.println("Element not presence");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }return message;
    }

}
