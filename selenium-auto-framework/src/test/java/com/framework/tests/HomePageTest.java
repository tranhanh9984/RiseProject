package com.framework.tests;

import com.framework.base.BaseTest;
import com.framework.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Test class for Home page functionality.
 */
public class HomePageTest extends BaseTest {

    private HomePage homePage;

    @BeforeMethod(alwaysRun = true, dependsOnMethods = "setUp")
    public void initPage() {
        homePage = new HomePage();
        homePage.open();
    }

    @Test(description = "Verify Home page loads correctly")
    public void testHomePageLoaded() {
        Assert.assertTrue(homePage.isPageLoaded(), "Home page should be loaded");
    }

    @Test(description = "Verify Home page heading text")
    public void testHomePageHeading() {
        String heading = homePage.getPageHeading();
        Assert.assertEquals(heading, "Welcome to the-internet",
                "Heading should match expected text");
    }

    @Test(description = "Verify available examples are listed")
    public void testAvailableExamples() {
        int count = homePage.getExamplesCount();
        Assert.assertTrue(count > 0, "Should have at least one example listed");
        log.info("Number of available examples: {}", count);
    }

    @Test(description = "Verify page title is not empty")
    public void testPageTitle() {
        String title = homePage.getPageTitle();
        Assert.assertNotNull(title, "Page title should not be null");
        Assert.assertFalse(title.isEmpty(), "Page title should not be empty");
        log.info("Page title: {}", title);
    }
}
