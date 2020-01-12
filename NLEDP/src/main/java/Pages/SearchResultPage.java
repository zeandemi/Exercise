package Pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

public class SearchResultPage extends BasePage {

    @FindBy(how = How.XPATH,using = "//*[@id=\"firstHeading\"]")
    WebElement searchResultPageHeader;
    @FindBy(how = How.XPATH,using = "//*[@id=\"p-lang-label\"]")
    WebElement languages;
    @FindBy(how = How.XPATH,using = "/html/body/div[1]/div[2]/div[1]/div/ul[1]/li[4]")
    WebElement languageOption;
    @FindBy(how = How.XPATH,using = "//*[@id=\"p-lang\"]/div/ul/button")
    WebElement Options;
    @FindBy(how = How.XPATH,using = "//*[@id=\"search\"]/div/div/input[2]")
    WebElement languageSearchBox;
    private final WebDriver driver;
    private JavascriptExecutor js;
    List<WebElement> langOptions;
    List<WebElement> english;

    public SearchResultPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
        langOptions = driver.findElements(By.xpath("//*[@id=\"p-lang\"]/div/ul/li"));
        english = driver.findElements(By.xpath("/html/body/div[1]/div[2]/div[9]/div/ul/li"));
    }

    private void scrollToView(WebElement element) throws InterruptedException {
        js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", element );
        Thread.sleep(5000);
    }

    public String getPageTitle(){
        String searchPageHeader = getText(searchResultPageHeader);
        return searchPageHeader;
    }

    public SearchResultPage verifyTitle(String searchWord){
        Assert.assertTrue(searchWord.equalsIgnoreCase(getPageTitle()));
        return this;
    }

    public List<WebElement> verifyLangauges() throws InterruptedException {
        scrollToView(languages);
        return langOptions;
    }

    public SearchResultPage verifyOtherLanguage() throws InterruptedException {
        Assert.assertTrue(!verifyLangauges().isEmpty());
        return this;
    }

    public SearchResultPage clickLanguagesOptions() throws InterruptedException {
        click(Options);
        Thread.sleep(2000);
        return this;
    }

    public SearchResultPage clickLanguages() throws InterruptedException {
        click(languageOption);
        Thread.sleep(2000);
        return this;
    }

    public SearchResultPage selectAnotherLanguage() throws InterruptedException {
        clickLanguagesOptions().clickLanguages();
        return this;
    }

    public SearchResultPage checkForEnglishOption() throws InterruptedException {
        String s = "English";
        sendText(languageSearchBox,s);
        Thread.sleep(2000);
        for(WebElement e : english){
            Assert.assertTrue(e.getText().equalsIgnoreCase(s));
        }
        return this;
    }

    public SearchResultPage verifyEnglish() throws InterruptedException {
        scrollToView(languages);
        clickLanguagesOptions().checkForEnglishOption();
        return this;
    }

    public SearchResultPage checkForEnglishLanguage() throws InterruptedException {
        return verifyEnglish();
    }


}
