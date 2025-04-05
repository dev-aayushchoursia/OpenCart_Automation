package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountRegistrationPaga;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC_001_AccountRegistrationTest extends BaseClass {

    @Test(groups = {"Regression", "Master"})
    public void verify_account_registration() {
        logger.info("***** Starting TC_001_AccountRegistrationTest *****");

        try {
            HomePage hp = new HomePage(driver);
            hp.clickMyAccount();
            logger.info("Clicked on My Account link.");

            hp.clickRegister();
            logger.info("Clicked on Register link.");

            AccountRegistrationPaga regPage = new AccountRegistrationPaga(driver);
            logger.info("Filling in customer details...");

            String firstName = randomString().toUpperCase();
            String lastName = randomString().toUpperCase();
            String email = randomString() + "@gmail.com";
            String telephone = randomNumber();
            String password = randomAlphaNumeric();

            regPage.setFirstName(firstName);
            regPage.setLastName(lastName);
            regPage.setEmail(email);
            regPage.setTelephone(telephone);
            regPage.setPassword(password);
            regPage.setConfirmPassword(password);
            regPage.setPrivacyPolicy();
            regPage.clickContinueBtn();

            logger.info("Validating the confirmation message...");

            String expectedMessage = "Your Account Has Been Created!";
            String actualMessage = regPage.getConfirmation();

            Assert.assertEquals(actualMessage, expectedMessage, "Confirmation message mismatch");

            logger.info("Test passed: Account successfully registered.");
        } catch (Throwable e) {  // Catch both Exception & AssertionError
            logger.error("Test failed due to: " + e.getMessage(), e);
            //logger.debug("Debug Error : ");

            Assert.fail("Test failed due to: " + e.getMessage());
        } finally {
            logger.info("***** Finished TC_001_AccountRegistrationTest *****");
        }
    }
}
