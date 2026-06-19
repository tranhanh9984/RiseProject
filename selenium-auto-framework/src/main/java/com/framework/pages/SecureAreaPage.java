package com.framework.pages;

import org.openqa.selenium.By;

/**
 * Page Object for the Secure Area page (after successful login).
 */
public class SecureAreaPage extends BasePage {

    // ==================== Locators ====================
    private final By flashMessage = By.id("flash");
    private final By logoutButton = By.cssSelector("a.button");
    private final By pageHeading = By.tagName("h2");
    private final By subHeading = By.tagName("h4");

    // ==================== Actions ====================

    public String getFlashMessage() {
        return getText(flashMessage);
    }

    public String getPageHeading() {
        return getText(pageHeading);
    }

    public String getSubHeading() {
        return getText(subHeading);
    }

    public LoginPage clickLogout() {
        click(logoutButton);
        return new LoginPage();
    }

    public boolean isPageLoaded() {
        return isDisplayed(pageHeading) && getPageHeading().contains("Secure Area");
    }

    public boolean isLogoutButtonDisplayed() {
        return isDisplayed(logoutButton);
    }
}
