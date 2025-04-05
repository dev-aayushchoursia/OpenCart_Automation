package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;


public class TC_002_LoginTest extends BaseClass {

    @Test(groups = {"Sanity", "Master"})
    public void verify_Login() {
        logger.info("***** Starting TC_002_LoginTest *****");
        try {
            // Home Page Test
            HomePage hp = new HomePage(driver);
            hp.clickMyAccount();
            logger.info("Clicked on My Account link.");
            hp.clickLogin();
            logger.info("Clicked on Register link.");

            // Log-in Page Test
            LoginPage lp = new LoginPage(driver);
            logger.info("Provided Email");
            lp.setEmail(p.getProperty("email"));
            logger.info("Provided password");
            lp.setPassword(p.getProperty("password"));
            logger.info("Clicked On Login Btn");
            lp.loginBtn();

            MyAccountPage ap = new MyAccountPage(driver);
            logger.info("My Account Displayed");
            ap.validateMyAccount();
        } catch (Exception e) {
            Assert.fail();
        }
        logger.info("TC_002 Test-Case Finished");
    }
}
