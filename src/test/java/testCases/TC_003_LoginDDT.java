package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;


public class TC_003_LoginDDT extends BaseClass {

    @Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class, groups = {"DataDriven", "Master"})
    //getting data provider from different class
    public void DataDriverLogin(String email, String password, String result) {
        logger.info("***** Starting TC_003_LoginDDT *****");
        try {
            HomePage hp = new HomePage(driver);
            hp.clickMyAccount();
            logger.info("Clicked on My Account link.");
            hp.clickLogin();
            logger.info("Clicked on Register link.");

            // Log-in Page Test
            LoginPage lp = new LoginPage(driver);
            logger.info("Provided Email");
            lp.setEmail(email);
            logger.info("Provided password");
            lp.setPassword(password);
            logger.info("Clicked On Login Btn");
            lp.loginBtn();

            MyAccountPage myap = new MyAccountPage(driver);
            logger.info("My Account Displayed");
            Boolean targetPage = myap.validateMyAccount();

            // here ignorecase is used for upper and lower case alphabetical difference
            if (result.equalsIgnoreCase("Valid")) {
                if (targetPage) {
                    Assert.assertTrue(true);
                    myap.logoutAccount();
                } else {
                    Assert.fail();
                }
            }
            if (result.equalsIgnoreCase("Invalid")) {
                if (targetPage) {
                    myap.logoutAccount();
                    Assert.fail();
                } else {
                    Assert.assertTrue(true);
                }
            }
        } catch (Exception e) {
            Assert.fail();
        }
        logger.info("TC_003 Test-Case Finished");
    }
}