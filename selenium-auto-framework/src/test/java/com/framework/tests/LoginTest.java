package com.framework.tests;

import com.framework.base.BaseTest;
import com.framework.pages.LoginPage;
import com.framework.pages.SecureAreaPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Test class for Login functionality.
 */
public class LoginTest extends BaseTest {

    private LoginPage loginPage;

    @BeforeMethod(alwaysRun = true, dependsOnMethods = "setUp")
    public void initPage() {
        loginPage = new LoginPage();
        loginPage.open();
    }

    @Test(description = "Verify successful login with valid credentials")
    public void testSuccessfulLogin() {
        SecureAreaPage secureArea = loginPage.loginAs("tomsmith", "SuperSecretPassword!");

        Assert.assertTrue(secureArea.isPageLoaded(), "Secure Area page should be loaded");
        Assert.assertTrue(secureArea.getFlashMessage().contains("You logged into a secure area!"),
                "Success flash message should be displayed");
        Assert.assertTrue(secureArea.isLogoutButtonDisplayed(),
                "Logout button should be visible");
    }

    @Test(description = "Verify login fails with invalid username")
    public void testLoginWithInvalidUsername() {
        loginPage.enterUsername("invalidUser");
        loginPage.enterPassword("SuperSecretPassword!");
        loginPage.clickLoginExpectingFailure();

        String flashMessage = loginPage.getFlashMessage();
        Assert.assertTrue(flashMessage.contains("Your username is invalid!"),
                "Error message should indicate invalid username");
    }

    @Test(description = "Verify login fails with invalid password")
    public void testLoginWithInvalidPassword() {
        loginPage.enterUsername("tomsmith");
        loginPage.enterPassword("wrongPassword");
        loginPage.clickLoginExpectingFailure();

        String flashMessage = loginPage.getFlashMessage();
        Assert.assertTrue(flashMessage.contains("Your password is invalid!"),
                "Error message should indicate invalid password");
    }

    @Test(description = "Verify successful logout after login")
    public void testLogout() {
        SecureAreaPage secureArea = loginPage.loginAs("tomsmith", "SuperSecretPassword!");
        Assert.assertTrue(secureArea.isPageLoaded(), "Should be on Secure Area page");

        LoginPage loginPageAfterLogout = secureArea.clickLogout();
        String flashMessage = loginPageAfterLogout.getFlashMessage();
        Assert.assertTrue(flashMessage.contains("You logged out of the secure area!"),
                "Logout success message should be displayed");
    }

    @Test(description = "Verify Login page loads correctly")
    public void testLoginPageElements() {
        Assert.assertTrue(loginPage.isPageLoaded(), "Login page should be loaded");
        Assert.assertEquals(loginPage.getPageHeading(), "Login Page",
                "Page heading should be 'Login Page'");
    }
}
