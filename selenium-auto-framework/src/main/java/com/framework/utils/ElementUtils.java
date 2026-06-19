package com.framework.utils;

import com.framework.driver.DriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Utility class for advanced element interactions.
 */
public class ElementUtils {

    private ElementUtils() {
    }

    public static boolean isElementPresent(By locator) {
        try {
            DriverManager.getDriver().findElement(locator);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public static boolean isElementDisplayed(By locator) {
        try {
            return DriverManager.getDriver().findElement(locator).isDisplayed();
        } catch (NoSuchElementException | StaleElementReferenceException e) {
            return false;
        }
    }

    public static void highlightElement(By locator) {
        WebElement element = DriverManager.getDriver().findElement(locator);
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("arguments[0].style.border='3px solid red'", element);
    }

    public static void scrollToBottom() {
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    public static void scrollToTop() {
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("window.scrollTo(0, 0)");
    }

    public static Object executeJS(String script, Object... args) {
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        return js.executeScript(script, args);
    }

    public static String switchToNewWindow() {
        WebDriver driver = DriverManager.getDriver();
        String originalWindow = driver.getWindowHandle();
        Set<String> allWindows = driver.getWindowHandles();

        for (String window : allWindows) {
            if (!window.equals(originalWindow)) {
                driver.switchTo().window(window);
                return originalWindow;
            }
        }
        return originalWindow;
    }

    public static void switchToWindowByIndex(int index) {
        WebDriver driver = DriverManager.getDriver();
        List<String> windows = new ArrayList<>(driver.getWindowHandles());
        if (index < windows.size()) {
            driver.switchTo().window(windows.get(index));
        }
    }

    public static void waitAndRetryClick(By locator, int maxRetries) {
        WebDriver driver = DriverManager.getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        for (int i = 0; i < maxRetries; i++) {
            try {
                wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
                return;
            } catch (StaleElementReferenceException | ElementClickInterceptedException e) {
                if (i == maxRetries - 1) {
                    throw e;
                }
            }
        }
    }
}
