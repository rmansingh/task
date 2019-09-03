package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Rupak Mansingh this class helps the activities of partner bank page
 * every action on the page is done here
 */

public class OfferPage extends BasePage {

    @FindBy(id = "nav-main-link")
    public WebElement OfferTab;

    @FindBy(id = "SubmitCreate")
    public WebElement btnCreateAccount;

    public OfferPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
    @Step("Navigate to offer page")
    public void navigateToOffersPage() {
        log("Navigate to offer page");
        driver.get(baseURL + "our-offers/");
    }
}
