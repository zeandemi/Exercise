package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage {

    @FindBy(how = How.XPATH,using = "//*[@id=\"www-wikipedia-org\"]/h1/div/div")
    WebElement homePageHeader;

    @FindBy(how = How.XPATH,using = "//*[@id=\"searchInput\"]")
    WebElement searchBox;

    @FindBy(how = How.XPATH,using = "//*[@id=\"search-form\"]/fieldset/button")
    WebElement searchButton;

    @FindBy(how = How.XPATH,using = "//*[@id=\"jsLangLabel\"]")
    WebElement languageOption;
    private final WebDriver driver;

    public HomePage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver,this);
        this.driver = driver;
    }

    public String getHomePageTitle(){
        String s = getText(homePageHeader);
        System.out.println(s);
        return getText(homePageHeader);
    }

    public HomePage searchForString(String word){
        sendText(searchBox,word);
        return this;
    }

    public String verifyDefaultLanguage(){
       return getText(languageOption);
    }

    public SearchResultPage clickSearchButton(){
        click(searchButton);
        return new SearchResultPage(driver);
    }






}
