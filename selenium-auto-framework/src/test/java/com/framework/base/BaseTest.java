package com.framework.base;

import com.framework.config.ConfigReader;
import com.framework.driver.DriverManager;
import com.framework.utils.ScreenshotUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestResult;
import org.testng.annotations.*;

/**
 * Base test class that all test classes should extend.
 * Handles WebDriver lifecycle and common test setup/teardown.
 */
public class BaseTest {

    protected final Logger log = LogManager.getLogger(this.getClass());

    @BeforeSuite(alwaysRun = true)
    public void beforeSuite() {
        log.info("========== Test Suite Started ==========");
    }

    @BeforeMethod(alwaysRun = true)
    @Parameters({"browser"})
    public void setUp(@Optional String browser) {
        if (browser != null && !browser.isEmpty()) {
            System.setProperty("browser", browser);
        }
        log.info("Initializing WebDriver...");
        DriverManager.initDriver();
        log.info("WebDriver initialized successfully. Browser: {}",
                ConfigReader.getInstance().getBrowser());
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            log.error("Test FAILED: {}", result.getName());
            ScreenshotUtils.takeScreenshot(result.getName());
        } else if (result.getStatus() == ITestResult.SKIP) {
            log.warn("Test SKIPPED: {}", result.getName());
        } else {
            log.info("Test PASSED: {}", result.getName());
        }
        DriverManager.quitDriver();
        log.info("WebDriver closed.");
    }

    @AfterSuite(alwaysRun = true)
    public void afterSuite() {
        log.info("========== Test Suite Finished ==========");
    }

    protected void openUrl(String url) {
        DriverManager.getDriver().get(url);
    }

    protected void openBaseUrl() {
        String baseUrl = ConfigReader.getInstance().getBaseUrl();
        DriverManager.getDriver().get(baseUrl);
    }
}
