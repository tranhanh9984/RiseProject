package com.framework.pages;

import com.framework.config.ConfigReader;
import com.framework.driver.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

/**
 * Base class for all Page Objects.
 * Provides common Selenium interactions with built-in waits and logging.
 */
public abstract class BasePage {

    protected final WebDriver driver;
    protected final WebDriverWait wait;
    protected final Actions actions;
    protected final Logger log;

    protected BasePage() {
        this.driver = DriverManager.getDriver();
        int timeout = ConfigReader.getInstance().getExplicitWait();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        this.actions = new Actions(driver);
        this.log = LogManager.getLogger(this.getClass());
        PageFactory.initElements(driver, this);
    }

    // ==================== Navigation ====================

    protected void navigateTo(String url) {
        log.info("Navigating to: {}", url);
        driver.get(url);
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    // ==================== Wait & Find ====================

    protected WebElement waitForVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected WebElement waitForClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected List<WebElement> waitForAllVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    protected boolean waitForInvisible(By locator) {
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    // ==================== Actions ====================

    protected void click(By locator) {
        log.info("Clicking element: {}", locator);
        waitForClickable(locator).click();
    }

    protected void type(By locator, String text) {
        log.info("Typing '{}' into element: {}", text, locator);
        WebElement element = waitForVisible(locator);
        element.clear();
        element.sendKeys(text);
    }

    protected String getText(By locator) {
        String text = waitForVisible(locator).getText();
        log.info("Got text '{}' from element: {}", text, locator);
        return text;
    }

    protected String getAttribute(By locator, String attribute) {
        return waitForVisible(locator).getAttribute(attribute);
    }

    protected boolean isDisplayed(By locator) {
        try {
            return waitForVisible(locator).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    protected boolean isEnabled(By locator) {
        return waitForVisible(locator).isEnabled();
    }

    // ==================== Dropdown ====================

    protected void selectByVisibleText(By locator, String text) {
        log.info("Selecting '{}' from dropdown: {}", text, locator);
        new Select(waitForVisible(locator)).selectByVisibleText(text);
    }

    protected void selectByValue(By locator, String value) {
        new Select(waitForVisible(locator)).selectByValue(value);
    }

    protected void selectByIndex(By locator, int index) {
        new Select(waitForVisible(locator)).selectByIndex(index);
    }

    // ==================== Advanced Actions ====================

    protected void hoverOver(By locator) {
        log.info("Hovering over element: {}", locator);
        actions.moveToElement(waitForVisible(locator)).perform();
    }

    protected void doubleClick(By locator) {
        actions.doubleClick(waitForClickable(locator)).perform();
    }

    protected void rightClick(By locator) {
        actions.contextClick(waitForClickable(locator)).perform();
    }

    protected void dragAndDrop(By source, By target) {
        actions.dragAndDrop(waitForVisible(source), waitForVisible(target)).perform();
    }

    protected void scrollToElement(By locator) {
        WebElement element = waitForVisible(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    protected void clickByJS(By locator) {
        WebElement element = waitForVisible(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    // ==================== Frames & Windows ====================

    protected void switchToFrame(By locator) {
        driver.switchTo().frame(waitForVisible(locator));
    }

    protected void switchToDefaultContent() {
        driver.switchTo().defaultContent();
    }

    protected void switchToWindow(String windowHandle) {
        driver.switchTo().window(windowHandle);
    }

    // ==================== Alerts ====================

    protected void acceptAlert() {
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
    }

    protected void dismissAlert() {
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().dismiss();
    }

    protected String getAlertText() {
        wait.until(ExpectedConditions.alertIsPresent());
        return driver.switchTo().alert().getText();
    }
}
