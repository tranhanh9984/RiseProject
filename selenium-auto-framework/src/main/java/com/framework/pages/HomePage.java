package com.framework.pages;

import com.framework.config.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Page Object for the Home page (https://the-internet.herokuapp.com).
 */
public class HomePage extends BasePage {

    // ==================== Locators ====================
    private final By pageHeading = By.tagName("h1");
    private final By subHeading = By.tagName("h2");
    private final By availableExamples = By.cssSelector("#content ul li a");

    // ==================== Actions ====================

    public HomePage open() {
        navigateTo(ConfigReader.getInstance().getBaseUrl());
        return this;
    }

    public String getPageHeading() {
        return getText(pageHeading);
    }

    public String getSubHeading() {
        return getText(subHeading);
    }

    public List<String> getAvailableExamples() {
        List<WebElement> elements = waitForAllVisible(availableExamples);
        return elements.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public int getExamplesCount() {
        return waitForAllVisible(availableExamples).size();
    }

    public void clickExample(String exampleName) {
        By exampleLink = By.linkText(exampleName);
        click(exampleLink);
    }

    public boolean isPageLoaded() {
        return isDisplayed(pageHeading);
    }
}
