package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Rupak Mansingh
 * Partner bank page.
 */

public class PartnerBankPage extends BasePage {

    @FindBy(id = "email_create")
    public WebElement txtNewEmail;

    public PartnerBankPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
}

