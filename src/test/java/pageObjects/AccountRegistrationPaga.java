package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPaga extends BasePage {

    //Locators
    @FindBy(xpath = "//input[@id='input-firstname']")
    WebElement firstNameInp;
    @FindBy(xpath = "//input[@id='input-lastname']")
    WebElement lastnameInp;
    @FindBy(xpath = "//input[@id='input-email']")
    WebElement emailInp;
    @FindBy(xpath = "//input[@id='input-telephone']")
    WebElement telephoneInp;
    @FindBy(xpath = "//input[@id='input-password']")
    WebElement passwordInp;
    @FindBy(xpath = "//input[@id='input-confirm']")
    WebElement confirmPasswordInp;
    @FindBy(xpath = "//input[@name='agree']")
    WebElement termsAgreeDiv;
    @FindBy(xpath = "//input[@value='Continue']")
    WebElement continueBtn;
    @FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']")
    WebElement AccountCreationConfirmation;
    public AccountRegistrationPaga(WebDriver driver) {
        super(driver);
    }

    public void setFirstName(String firstName) {
        firstNameInp.sendKeys(firstName);
    }

    public void setLastName(String lastName) {
        lastnameInp.sendKeys(lastName);
    }

    public void setEmail(String setEmail) {
        emailInp.sendKeys(setEmail);
    }

    public void setTelephone(String telephone) {
        telephoneInp.sendKeys(telephone);
    }

    public void setPassword(String pass) {
        passwordInp.sendKeys(pass);
    }

    public void setConfirmPassword(String pass) {
        confirmPasswordInp.sendKeys(pass);
    }

    public void setPrivacyPolicy() {
        termsAgreeDiv.click();
    }

    public void clickContinueBtn() {
        continueBtn.click();
    }

    public String getConfirmation() {
        try {
            String res = AccountCreationConfirmation.getText();
            return res;
        } catch (Exception e) {
            return (e.getMessage());
        }
    }
}