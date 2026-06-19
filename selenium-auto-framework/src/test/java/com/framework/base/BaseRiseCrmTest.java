package com.framework.base;

import com.framework.config.ConfigReader;
import com.framework.driver.DriverManager;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

/**
 * Base test class for RISE CRM tests.
 * Bypasses CAPTCHA login by injecting ci_session cookie.
 */
public class BaseRiseCrmTest extends BaseTest {

    protected String riseBaseUrl;

    protected void loginWithCookie() {
        ConfigReader config = ConfigReader.getInstance();
        riseBaseUrl = config.getProperty("rise.base.url");
        String ciSession = config.getProperty("rise.ci_session");

        WebDriver driver = DriverManager.getDriver();

        // Navigate to base URL first to set the domain
        driver.get(riseBaseUrl);

        // Add ci_session cookie to bypass CAPTCHA login
        Cookie sessionCookie = new Cookie.Builder("ci_session", ciSession)
                .domain(riseBaseUrl.replaceAll("https?://", "").replaceAll("/.*", ""))
                .path("/")
                .isHttpOnly(true)
                .build();
        driver.manage().addCookie(sessionCookie);

        log.info("ci_session cookie injected successfully. Bypassing CAPTCHA login.");
    }

    protected void navigateToRise(String path) {
        String url = riseBaseUrl + path;
        log.info("Navigating to RISE CRM: {}", url);
        DriverManager.getDriver().get(url);
    }
}
