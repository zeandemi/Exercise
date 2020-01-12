package PageTest;

import Pages.HomePage;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class HomePageTest extends BaseSetUpTest {

        HomePage homePage;
        WebDriver driver;

@Parameters({"word"})
@Test
    public void searchForStringTest(String word) throws InterruptedException {
        driver = getDriver();
        homePage = new HomePage(driver);
        String title = "Wikipedia";
        String defLanguage = "EN";
        Assert.assertTrue(title.equalsIgnoreCase(homePage.getHomePageTitle().trim()));
        Assert.assertTrue(defLanguage.equalsIgnoreCase(homePage.verifyDefaultLanguage().trim()));
        homePage.searchForString(word).clickSearchButton().verifyTitle(word).verifyOtherLanguage().selectAnotherLanguage().checkForEnglishLanguage();
    }


}
