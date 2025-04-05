package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {
    @FindBy(xpath = "//span[normalize-space()='My Account']")
    WebElement myaccountDiv;
    @FindBy(xpath = "//div[@id='top-links']//a[normalize-space()='Register']")
    WebElement registerDiv;
    @FindBy(xpath = "//a[normalize-space()='Login']")
    WebElement LoginDiv;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void clickMyAccount() {
        myaccountDiv.click();
    }

    public void clickRegister() {
        registerDiv.click();
    }

    public void clickLogin() {
        LoginDiv.click();
    }
}