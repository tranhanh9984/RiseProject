package com.framework.pages;

import com.framework.config.ConfigReader;
import org.openqa.selenium.By;

/**
 * Page Object for the Login page (https://the-internet.herokuapp.com/login).
 */
public class LoginPage extends BasePage {

    // ==================== Locators ====================
    private final By usernameInput = By.id("username");
    private final By passwordInput = By.id("password");
    private final By loginButton = By.cssSelector("button[type='submit']");
    private final By flashMessage = By.id("flash");
    private final By pageHeading = By.tagName("h2");

    // ==================== Actions ====================

    public LoginPage open() {
        navigateTo(ConfigReader.getInstance().getProperty("login.url"));
        return this;
    }

    public LoginPage enterUsername(String username) {
        type(usernameInput, username);
        return this;
    }

    public LoginPage enterPassword(String password) {
        type(passwordInput, password);
        return this;
    }

    public SecureAreaPage clickLogin() {
        click(loginButton);
        return new SecureAreaPage();
    }

    public LoginPage clickLoginExpectingFailure() {
        click(loginButton);
        return this;
    }

    /**
     * Perform full login with credentials from config.
     */
    public SecureAreaPage loginWithDefaultCredentials() {
        ConfigReader config = ConfigReader.getInstance();
        enterUsername(config.getProperty("username"));
        enterPassword(config.getProperty("password"));
        return clickLogin();
    }

    /**
     * Perform login with custom credentials.
     */
    public SecureAreaPage loginAs(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        return clickLogin();
    }

    public String getFlashMessage() {
        return getText(flashMessage);
    }

    public String getPageHeading() {
        return getText(pageHeading);
    }

    public boolean isPageLoaded() {
        return isDisplayed(pageHeading) && getPageHeading().contains("Login Page");
    }
}
