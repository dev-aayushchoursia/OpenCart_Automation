package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage{
    @FindBy(xpath = "//h2[normalize-space()='My Account']")
    WebElement myAccountValidation;

    @FindBy(xpath="(//a[normalize-space()='Logout'])[2]")
    WebElement logoutBtn;

    public MyAccountPage(WebDriver driver) {
        super(driver);
    }

    public boolean validateMyAccount() {
        try {
            return (myAccountValidation.isDisplayed());
        } catch (Exception e) {
            return false;
        }
    }

        public void logoutAccount(){
            logoutBtn.click();;
        }
}