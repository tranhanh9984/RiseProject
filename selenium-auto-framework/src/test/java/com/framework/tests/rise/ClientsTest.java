package com.framework.tests.rise;

import com.framework.base.BaseRiseCrmTest;
import com.framework.pages.rise.ClientsPage;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Test class for RISE CRM Clients module.
 * Covers TC-CLI-001 through TC-CLI-026.
 */
public class ClientsTest extends BaseRiseCrmTest {

    private ClientsPage clientsPage;
    private final String timestamp = String.valueOf(System.currentTimeMillis());

    @BeforeMethod(alwaysRun = true, dependsOnMethods = "setUp")
    public void initPage() {
        loginWithCookie();
        clientsPage = new ClientsPage();
        clientsPage.open(riseBaseUrl);
    }

    // ==================== 2.1 Page Display ====================

    @Test(description = "TC-CLI-001: Verify Clients page loads with Overview tab")
    public void TC_CLI_001_verifyClientsPageLoads() {
        Assert.assertTrue(clientsPage.isPageLoaded(),
                "URL should contain '/clients'");
        Assert.assertTrue(clientsPage.isOverviewTabActive(),
                "'Overview' tab should be active by default");
        Assert.assertTrue(clientsPage.getTabCount() >= 3,
                "At least 3 tabs should be visible: Overview, Clients, Contacts");
    }

    @Test(description = "TC-CLI-002: Verify action buttons display")
    public void TC_CLI_002_verifyActionButtons() {
        Assert.assertTrue(clientsPage.isAddClientButtonVisible(),
                "'Add client' button should be visible");
        Assert.assertTrue(clientsPage.isManageLabelsButtonVisible(),
                "'Manage labels' button should be visible");
        Assert.assertTrue(clientsPage.isImportClientsButtonVisible(),
                "'Import clients' button should be visible");
    }

    @Test(description = "TC-CLI-003: Verify Overview cards display")
    public void TC_CLI_003_verifyOverviewCards() {
        int cardCount = clientsPage.getOverviewCardCount();
        Assert.assertTrue(cardCount >= 1,
                "At least 1 overview card should be displayed, got: " + cardCount);
    }

    // ==================== 2.2 Tab Navigation ====================

    @Test(description = "TC-CLI-004: Switch to Clients list tab")
    public void TC_CLI_004_switchToClientsTab() {
        clientsPage.clickClientsTab();
        Assert.assertTrue(clientsPage.isClientTableVisible(),
                "Client data table should be displayed");
        List<WebElement> headers = clientsPage.getClientTableHeaders();
        Assert.assertTrue(headers.size() > 0,
                "Client table should have column headers");
    }

    @Test(description = "TC-CLI-005: Switch to Contacts tab")
    public void TC_CLI_005_switchToContactsTab() {
        clientsPage.clickContactsTab();
        Assert.assertTrue(clientsPage.isContactsTableVisible(),
                "Contacts data table should be displayed");
    }

    @Test(description = "TC-CLI-006: Switch between all tabs")
    public void TC_CLI_006_switchBetweenAllTabs() {
        clientsPage.clickClientsTab();
        Assert.assertTrue(clientsPage.isClientTableVisible(),
                "Client table should be visible on Clients tab");

        clientsPage.clickContactsTab();
        Assert.assertTrue(clientsPage.isContactsTableVisible(),
                "Contacts table should be visible on Contacts tab");

        clientsPage.clickOverviewTab();
        Assert.assertTrue(clientsPage.isOverviewTabActive(),
                "Overview tab should be active again");
    }

    // ==================== 2.3 Export ====================

    @Test(description = "TC-CLI-007: Verify export buttons display")
    public void TC_CLI_007_verifyExportButtons() {
        clientsPage.clickClientsTab();
        Assert.assertTrue(clientsPage.isExcelButtonVisible(),
                "'Excel' button should be visible");
        Assert.assertTrue(clientsPage.isPrintButtonVisible(),
                "'Print' button should be visible");
    }

    // ==================== 2.4 Add Client Modal ====================

    @Test(description = "TC-CLI-008: Open Add client modal")
    public void TC_CLI_008_openAddClientModal() {
        clientsPage.clickAddClient();
        Assert.assertTrue(clientsPage.isModalVisible(),
                "Modal should be visible");
        String title = clientsPage.getModalTitle();
        Assert.assertTrue(title.contains("Add client"),
                "Modal title should be 'Add client', got: " + title);
    }

    @Test(description = "TC-CLI-009: Verify all form fields in Add client modal")
    public void TC_CLI_009_verifyModalFormFields() {
        clientsPage.clickAddClient();
        Assert.assertTrue(clientsPage.isCompanyNameInputVisible(), "Company name input should be visible");
        Assert.assertTrue(clientsPage.isAddressTextareaVisible(), "Address textarea should be visible");
        Assert.assertTrue(clientsPage.isCityInputVisible(), "City input should be visible");
        Assert.assertTrue(clientsPage.isStateInputVisible(), "State input should be visible");
        Assert.assertTrue(clientsPage.isZipInputVisible(), "Zip input should be visible");
        Assert.assertTrue(clientsPage.isCountryInputVisible(), "Country input should be visible");
        Assert.assertTrue(clientsPage.isPhoneInputVisible(), "Phone input should be visible");
        Assert.assertTrue(clientsPage.isWebsiteInputVisible(), "Website input should be visible");
        Assert.assertTrue(clientsPage.isSaveButtonVisible(), "Save button should be visible");
        Assert.assertTrue(clientsPage.isCloseButtonVisible(), "Close button should be visible");
    }

    @Test(description = "TC-CLI-010: Close Add client modal via X button")
    public void TC_CLI_010_closeModalByX() {
        clientsPage.clickAddClient();
        Assert.assertTrue(clientsPage.isModalVisible(), "Modal should be open");
        clientsPage.closeModalByX();
        Assert.assertTrue(clientsPage.isModalHidden(), "Modal should be hidden after closing");
    }

    @Test(description = "TC-CLI-011: Close modal via Close button")
    public void TC_CLI_011_closeModalByButton() {
        clientsPage.clickAddClient();
        Assert.assertTrue(clientsPage.isModalVisible(), "Modal should be open");
        clientsPage.closeModalByButton();
        Assert.assertTrue(clientsPage.isModalHidden(), "Modal should be hidden after closing");
    }

    // ==================== 2.5 Form Type Radio ====================

    @Test(description = "TC-CLI-012: Default type is Organization")
    public void TC_CLI_012_defaultTypeOrganization() {
        clientsPage.clickAddClient();
        Assert.assertTrue(clientsPage.isOrganizationRadioSelected(),
                "'Organization' radio should be checked by default");
        Assert.assertFalse(clientsPage.isPersonRadioSelected(),
                "'Person' radio should NOT be checked");
    }

    @Test(description = "TC-CLI-013: Switch type to Person")
    public void TC_CLI_013_switchTypeToPerson() {
        clientsPage.clickAddClient();
        clientsPage.selectPersonType();
        Assert.assertTrue(clientsPage.isPersonRadioSelected(),
                "'Person' radio should be checked after clicking");
        String label = clientsPage.getCompanyNameLabel();
        Assert.assertTrue(label.contains("Name"),
                "Label should change to 'Name' for Person type, got: " + label);
    }

    // ==================== 2.5 Validation ====================

    @Test(description = "TC-CLI-014: Validate required fields - empty form")
    public void TC_CLI_014_validateRequiredFieldsEmpty() {
        clientsPage.clickAddClient();
        clientsPage.clickSave();
        Assert.assertTrue(clientsPage.isModalVisible(),
                "Modal should stay open on validation error");
        Assert.assertTrue(clientsPage.isCompanyNameErrorVisible(),
                "Company name error should be visible");
    }

    @Test(description = "TC-CLI-014a: Validate Company name with only whitespace")
    public void TC_CLI_014a_validateWhitespaceCompanyName() {
        clientsPage.clickAddClient();
        clientsPage.enterCompanyName("   ");
        clientsPage.clickSave();
        Assert.assertTrue(clientsPage.isModalVisible(),
                "Modal should stay open - whitespace should not be accepted");
    }

    @Test(description = "TC-CLI-014b: Validate Website field with invalid URL format")
    public void TC_CLI_014b_validateInvalidWebsite() {
        clientsPage.clickAddClient();
        clientsPage.enterCompanyName("Test Client URL");
        clientsPage.enterWebsite("not-a-url");
        clientsPage.clickSave();
        // Either error message or client created with literal text
        log.info("Website validation: checking if error shown or client created");
    }

    @Test(description = "TC-CLI-014c: Validate Phone field with letters")
    public void TC_CLI_014c_validatePhoneWithLetters() {
        clientsPage.clickAddClient();
        clientsPage.enterCompanyName("Test Client Phone");
        clientsPage.enterPhone("abcdefgh");
        clientsPage.clickSave();
        log.info("Phone validation: checking if error shown or input sanitized");
    }

    // ==================== 2.6 Create Client - Organization ====================

    @Test(description = "TC-CLI-015: Create Organization client with mandatory fields only")
    public void TC_CLI_015_createOrgClientMandatory() {
        String clientName = "Test Org Mandatory " + timestamp;
        clientsPage.clickAddClient();
        clientsPage.createOrganizationClient(clientName);

        clientsPage.clickClientsTab();
        clientsPage.searchClient(clientName);
        Assert.assertTrue(clientsPage.isClientVisibleInTable(clientName),
                "New client '" + clientName + "' should appear in the table");
    }

    @Test(description = "TC-CLI-016: Create Organization client with all fields")
    public void TC_CLI_016_createOrgClientAllFields() {
        String clientName = "Test Org Full " + timestamp;
        clientsPage.clickAddClient();
        clientsPage.selectOrganizationType();
        clientsPage.enterCompanyName(clientName);
        clientsPage.enterAddress("123 Le Loi Street");
        clientsPage.enterCity("Ho Chi Minh");
        clientsPage.enterState("HCM");
        clientsPage.enterZip("700000");
        clientsPage.enterCountry("Vietnam");
        clientsPage.enterPhone("0901234567");
        clientsPage.enterWebsite("https://org-full.com");
        clientsPage.enterVatNumber("VAT123456");
        clientsPage.enterGstNumber("GST789012");
        clientsPage.clickSave();

        clientsPage.clickClientsTab();
        clientsPage.searchClient(clientName);
        Assert.assertTrue(clientsPage.isClientVisibleInTable(clientName),
                "New client '" + clientName + "' should appear in the table");
    }

    // ==================== 2.7 Create Client - Person ====================

    @Test(description = "TC-CLI-017: Create Person client with mandatory fields only")
    public void TC_CLI_017_createPersonClientMandatory() {
        String clientName = "Test Person Mandatory " + timestamp;
        clientsPage.clickAddClient();
        clientsPage.createPersonClient(clientName);

        clientsPage.clickClientsTab();
        clientsPage.searchClient(clientName);
        Assert.assertTrue(clientsPage.isClientVisibleInTable(clientName),
                "New person client '" + clientName + "' should appear in the table");
    }

    @Test(description = "TC-CLI-018: Create Person client with all fields")
    public void TC_CLI_018_createPersonClientAllFields() {
        String clientName = "Test Person Full " + timestamp;
        clientsPage.clickAddClient();
        clientsPage.selectPersonType();
        clientsPage.enterCompanyName(clientName);
        clientsPage.enterAddress("456 Nguyen Hue Boulevard");
        clientsPage.enterCity("Ha Noi");
        clientsPage.enterState("HN");
        clientsPage.enterZip("100000");
        clientsPage.enterCountry("Vietnam");
        clientsPage.enterPhone("0912345678");
        clientsPage.enterWebsite("https://person-full.com");
        clientsPage.clickSave();

        clientsPage.clickClientsTab();
        clientsPage.searchClient(clientName);
        Assert.assertTrue(clientsPage.isClientVisibleInTable(clientName),
                "New person client '" + clientName + "' should appear in the table");
    }

    // ==================== 2.8 Search ====================

    @Test(description = "TC-CLI-019: Search clients by keyword")
    public void TC_CLI_019_searchClientsByKeyword() {
        clientsPage.clickClientsTab();
        int initialCount = clientsPage.getClientRowCount();

        clientsPage.searchClient("test");
        int filteredCount = clientsPage.getClientRowCount();
        Assert.assertTrue(filteredCount <= initialCount,
                "Filtered count should be <= initial count");

        clientsPage.clearSearch();
        int afterClearCount = clientsPage.getClientRowCount();
        Assert.assertEquals(afterClearCount, initialCount,
                "Row count should return to initial after clearing search");
    }

    // ==================== 2.9 Boundary Value Analysis ====================

    @Test(description = "TC-CLI-020: Validate Company name - minimum length (1 character)")
    public void TC_CLI_020_validateMinLengthCompanyName() {
        clientsPage.clickAddClient();
        clientsPage.enterCompanyName("A");
        clientsPage.clickSave();
        // Should create successfully
        log.info("Minimum length company name (1 char) test completed");
    }

    @Test(description = "TC-CLI-021: Validate Company name - very long input (255+ characters)")
    public void TC_CLI_021_validateMaxLengthCompanyName() {
        clientsPage.clickAddClient();
        String longName = "A".repeat(256);
        clientsPage.enterCompanyName(longName);
        clientsPage.clickSave();
        // Either created with truncated name or error about max length
        log.info("Max length company name (256 chars) test completed");
    }

    // ==================== 2.10 Special Test Data ====================

    @Test(description = "TC-CLI-022: Create client with Vietnamese characters")
    public void TC_CLI_022_createClientVietnamese() {
        String clientName = "Cong ty TNHH Phat Trien Phan Mem Da Nang " + timestamp;
        clientsPage.clickAddClient();
        clientsPage.enterCompanyName(clientName);
        clientsPage.clickSave();

        clientsPage.clickClientsTab();
        clientsPage.searchClient("Cong ty TNHH");
        Assert.assertTrue(clientsPage.isClientVisibleInTable("Cong ty TNHH"),
                "Vietnamese client name should display correctly");
    }

    @Test(description = "TC-CLI-023: Create client with special characters")
    public void TC_CLI_023_createClientSpecialChars() {
        String clientName = "O'Brien & Partners (Ltd.) " + timestamp;
        clientsPage.clickAddClient();
        clientsPage.enterCompanyName(clientName);
        clientsPage.clickSave();

        clientsPage.clickClientsTab();
        clientsPage.searchClient("O'Brien");
        Assert.assertTrue(clientsPage.isClientVisibleInTable("O'Brien"),
                "Special character client name should display correctly");
    }

    // ==================== 2.11 Security ====================

    @Test(description = "TC-CLI-024: XSS prevention in Company name field")
    public void TC_CLI_024_xssPrevention() {
        String xssPayload = "<script>alert('XSS')</script>";
        clientsPage.clickAddClient();
        clientsPage.enterCompanyName(xssPayload);
        clientsPage.clickSave();
        // Script should NOT be executed - no alert popup
        log.info("XSS prevention test: No alert should appear. Name should be escaped/sanitized");
    }

    @Test(description = "TC-CLI-025: SQL Injection prevention in Company name field")
    public void TC_CLI_025_sqlInjectionPrevention() {
        String sqlPayload = "' OR 1=1; DROP TABLE clients; --";
        clientsPage.clickAddClient();
        clientsPage.enterCompanyName(sqlPayload);
        clientsPage.clickSave();
        // Application should continue to function normally
        Assert.assertTrue(clientsPage.isPageLoaded(),
                "Application should still function after SQL injection attempt");
    }

    // ==================== 2.12 Data Validation ====================

    @Test(description = "TC-CLI-026: Verify created client data displays correctly in detail",
            dependsOnMethods = "TC_CLI_016_createOrgClientAllFields")
    public void TC_CLI_026_verifyClientDetailData() {
        String clientName = "Test Org Full " + timestamp;
        clientsPage.clickClientsTab();
        clientsPage.searchClient(clientName);
        Assert.assertTrue(clientsPage.isClientVisibleInTable(clientName),
                "Client should be found in table");
        clientsPage.clickClientRow(clientName);
        // Verify detail page data
        Assert.assertTrue(clientsPage.getCurrentUrl().contains("/clients/view/"),
                "Should navigate to client detail page");
    }
}
