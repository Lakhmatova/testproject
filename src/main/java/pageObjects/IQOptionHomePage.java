package pageObjects;
import com.sun.org.glassfish.gmbal.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static java.util.logging.Logger.getLogger;


public class IQOptionHomePage extends BasePage{
    public IQOptionHomePage (WebDriver driver) {
        super(driver);
    }

    public void openUrl(String url) {
        driver.get(url);
    }

    @Description("search")
    @FindBy(xpath = "//*[@class='Button NavBtn css-1h5sh8h e1wcb5tu1']")
    public WebElement search;
    @Description("Email")
    @FindBy(xpath = "//input[@name='email']")
    public WebElement findbutton;
    @Description("Password")
    @FindBy(xpath = "//input[@name='password']")
    public WebElement findbuttonpas;
    @Description("Enter")
    @FindBy(xpath = "//*[@class ='Button Button_green Button_big Button_fontBig Button_bold css-1060k2v']")
    public WebElement enter;

    public void clickOnSearch() {
        waitForElementToAppear(By.xpath("//*[@class='Button NavBtn css-1h5sh8h e1wcb5tu1']"));
        search.click();
    }

    public void searchInFieldEmail(String value) {
        waitForElementToAppear(By.xpath("//*[@class ='css-4k6kap e1qwc8wr0'][1]"));
        findbutton.sendKeys(value);
    }

    public void searchInFieldPassword(String value) {
        findbuttonpas.sendKeys(value);
    }

    public void clickOnEnter() {
        enter.click();
    }

    public void setCheckPage(String url, int sec) {
        WebDriverWait waitForElement = new WebDriverWait(driver, sec);
        try {
            waitForElement.until(ExpectedConditions.urlToBe(url));
        } catch (TimeoutException ex) {
            getLogger(" is not present");
        }
    }
}
