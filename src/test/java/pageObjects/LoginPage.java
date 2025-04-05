package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
    @FindBy(xpath = "//input[@id='input-email']")
    WebElement emailInp;
    @FindBy(xpath = "//input[@id='input-password']")
    WebElement passInp;
    @FindBy(xpath = "//input[@value='Login']")
    WebElement loginBtn;
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void setEmail(String email) {
        emailInp.sendKeys(email);
    }

    public void setPassword(String password) {
        passInp.sendKeys(password);
    }

    public void loginBtn() {
        loginBtn.click();
    }
}