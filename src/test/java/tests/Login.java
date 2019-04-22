package tests;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import pageObjects.IQOptionHomePage;

public class Login extends BaseTest {

    @Test
    public void rbcTests() {
        IQOptionHomePage iqOptionHomePage;
        iqOptionHomePage = PageFactory.initElements(getDriver(), IQOptionHomePage.class);
        iqOptionHomePage.openUrl("https://iqoption.com");
        iqOptionHomePage.clickOnSearch();
        iqOptionHomePage.searchInFieldEmail("ttesttest651@gmail.com");
        iqOptionHomePage.searchInFieldPassword("qazwsx098");
        iqOptionHomePage.clickOnEnter();
        iqOptionHomePage.setCheckPage("https://iqoption.com/ru", 10);
    }
}
