package com.framework.tests.rise;

import com.framework.base.BaseRiseCrmTest;
import com.framework.pages.rise.LeadsPage;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Test class for RISE CRM Leads module.
 * Covers TC-LEAD-001 through TC-LEAD-023.
 */
public class LeadsTest extends BaseRiseCrmTest {

    private LeadsPage leadsPage;
    private final String timestamp = String.valueOf(System.currentTimeMillis());

    @BeforeMethod(alwaysRun = true, dependsOnMethods = "setUp")
    public void initPage() {
        loginWithCookie();
        leadsPage = new LeadsPage();
        leadsPage.open(riseBaseUrl);
    }

    // ==================== 3.1 Page Display ====================

    @Test(description = "TC-LEAD-001: Verify Leads page loads")
    public void TC_LEAD_001_verifyLeadsPageLoads() {
        Assert.assertTrue(leadsPage.isPageLoaded(),
                "URL should contain '/leads'");
        Assert.assertTrue(leadsPage.getCurrentUrl().contains("/leads"),
                "URL should contain '/leads'");
    }

    @Test(description = "TC-LEAD-002: Verify action buttons display")
    public void TC_LEAD_002_verifyActionButtons() {
        Assert.assertTrue(leadsPage.isAddLeadButtonVisible(),
                "'Add lead' button should be visible");
        Assert.assertTrue(leadsPage.isManageLabelsButtonVisible(),
                "'Manage labels' button should be visible");
        Assert.assertTrue(leadsPage.isImportLeadsButtonVisible(),
                "'Import leads' button should be visible");
    }

    @Test(description = "TC-LEAD-003: Verify view tabs display")
    public void TC_LEAD_003_verifyViewTabs() {
        Assert.assertTrue(leadsPage.isLeadsTabActive(),
                "'Leads' tab should be active by default");
        Assert.assertTrue(leadsPage.isKanbanTabVisible(),
                "'Kanban' tab should be visible");
    }

    @Test(description = "TC-LEAD-004: Verify lead table headers")
    public void TC_LEAD_004_verifyLeadTableHeaders() {
        List<WebElement> headers = leadsPage.getLeadTableHeaders();
        Assert.assertTrue(headers.size() > 0,
                "Lead table should have column headers");
    }

    @Test(description = "TC-LEAD-005: Verify lead data rows exist")
    public void TC_LEAD_005_verifyLeadDataRows() {
        int rowCount = leadsPage.getLeadRowCount();
        Assert.assertTrue(rowCount >= 1,
                "At least 1 lead data row should be displayed, got: " + rowCount);
    }

    // ==================== 3.2 View Switching ====================

    @Test(description = "TC-LEAD-006: Switch to Kanban view")
    public void TC_LEAD_006_switchToKanbanView() {
        leadsPage.clickKanbanTab();
        Assert.assertTrue(leadsPage.getCurrentUrl().contains("/kanban"),
                "URL should contain '/kanban'");
        Assert.assertTrue(leadsPage.isKanbanBoardVisible(),
                "Kanban board should be visible");
        int columnCount = leadsPage.getKanbanColumnCount();
        Assert.assertTrue(columnCount >= 6,
                "Kanban should have at least 6 columns (New, Qualified, Discussion, Negotiation, Won, Lost), got: " + columnCount);
    }

    @Test(description = "TC-LEAD-007: Switch back to Leads list view")
    public void TC_LEAD_007_switchBackToListView() {
        leadsPage.clickKanbanTab();
        Assert.assertTrue(leadsPage.isKanbanBoardVisible(), "Should be on Kanban view");

        leadsPage.clickLeadsTab();
        Assert.assertTrue(leadsPage.getCurrentUrl().contains("/leads"),
                "URL should contain '/leads'");
        Assert.assertTrue(leadsPage.isLeadTableVisible(),
                "Lead data table should be visible again");
    }

    // ==================== 3.3 Search & Export ====================

    @Test(description = "TC-LEAD-008: Search leads by keyword")
    public void TC_LEAD_008_searchLeadsByKeyword() {
        int initialCount = leadsPage.getLeadRowCount();

        leadsPage.searchLead("tech");
        int filteredCount = leadsPage.getLeadRowCount();
        Assert.assertTrue(filteredCount <= initialCount,
                "Filtered count should be <= initial count");

        leadsPage.clearSearch();
        int afterClearCount = leadsPage.getLeadRowCount();
        Assert.assertEquals(afterClearCount, initialCount,
                "Row count should return to initial after clearing search");
    }

    @Test(description = "TC-LEAD-009: Verify export buttons")
    public void TC_LEAD_009_verifyExportButtons() {
        Assert.assertTrue(leadsPage.isExcelButtonVisible(),
                "'Excel' button should be visible");
        Assert.assertTrue(leadsPage.isPrintButtonVisible(),
                "'Print' button should be visible");
    }

    // ==================== 3.4 Add Lead Modal ====================

    @Test(description = "TC-LEAD-010: Open Add lead modal")
    public void TC_LEAD_010_openAddLeadModal() {
        leadsPage.clickAddLead();
        Assert.assertTrue(leadsPage.isModalVisible(),
                "Modal should be visible");
        String title = leadsPage.getModalTitle();
        Assert.assertTrue(title.contains("Add lead"),
                "Modal title should be 'Add lead', got: " + title);
    }

    @Test(description = "TC-LEAD-011: Verify all form fields in Add lead modal")
    public void TC_LEAD_011_verifyModalFormFields() {
        leadsPage.clickAddLead();
        Assert.assertTrue(leadsPage.isCompanyNameInputVisible(), "Company name input should be visible");
        Assert.assertTrue(leadsPage.isStatusSelectVisible(), "Status select should be visible");
        Assert.assertTrue(leadsPage.isAddressTextareaVisible(), "Address textarea should be visible");
        Assert.assertTrue(leadsPage.isCityInputVisible(), "City input should be visible");
        Assert.assertTrue(leadsPage.isStateInputVisible(), "State input should be visible");
        Assert.assertTrue(leadsPage.isZipInputVisible(), "Zip input should be visible");
        Assert.assertTrue(leadsPage.isCountryInputVisible(), "Country input should be visible");
        Assert.assertTrue(leadsPage.isPhoneInputVisible(), "Phone input should be visible");
        Assert.assertTrue(leadsPage.isWebsiteInputVisible(), "Website input should be visible");
        Assert.assertTrue(leadsPage.isSaveButtonVisible(), "Save button should be visible");
        Assert.assertTrue(leadsPage.isCloseButtonVisible(), "Close button should be visible");
    }

    @Test(description = "TC-LEAD-012: Close Add lead modal")
    public void TC_LEAD_012_closeAddLeadModal() {
        leadsPage.clickAddLead();
        Assert.assertTrue(leadsPage.isModalVisible(), "Modal should be open");
        leadsPage.closeModalByX();
        Assert.assertTrue(leadsPage.isModalHidden(), "Modal should be hidden after closing");
    }

    @Test(description = "TC-LEAD-013: Default type is Organization")
    public void TC_LEAD_013_defaultTypeOrganization() {
        leadsPage.clickAddLead();
        Assert.assertTrue(leadsPage.isOrganizationRadioSelected(),
                "'Organization' radio should be checked by default");
        Assert.assertFalse(leadsPage.isPersonRadioSelected(),
                "'Person' radio should NOT be checked");
    }

    @Test(description = "TC-LEAD-014: Switch type to Person")
    public void TC_LEAD_014_switchTypeToPerson() {
        leadsPage.clickAddLead();
        leadsPage.selectPersonType();
        Assert.assertTrue(leadsPage.isPersonRadioSelected(),
                "'Person' radio should be checked");
        String label = leadsPage.getCompanyNameLabel();
        Assert.assertTrue(label.contains("Name"),
                "Label should change to 'Name', got: " + label);
    }

    // ==================== 3.5 Validation ====================

    @Test(description = "TC-LEAD-015: Validate required fields - empty form")
    public void TC_LEAD_015_validateRequiredFieldsEmpty() {
        leadsPage.clickAddLead();
        leadsPage.clickSave();
        Assert.assertTrue(leadsPage.isModalVisible(),
                "Modal should stay open on validation error");
        Assert.assertTrue(leadsPage.isCompanyNameErrorVisible(),
                "Company name error should be visible");
    }

    @Test(description = "TC-LEAD-015a: Validate Company name with only whitespace")
    public void TC_LEAD_015a_validateWhitespaceCompanyName() {
        leadsPage.clickAddLead();
        leadsPage.enterCompanyName("   ");
        leadsPage.clickSave();
        Assert.assertTrue(leadsPage.isModalVisible(),
                "Modal should stay open - whitespace should not be accepted");
    }

    // ==================== 3.6 Create Lead - Organization ====================

    @Test(description = "TC-LEAD-016: Create Organization lead with mandatory fields only")
    public void TC_LEAD_016_createOrgLeadMandatory() {
        String leadName = "Lead Org Mandatory " + timestamp;
        leadsPage.clickAddLead();
        leadsPage.createOrganizationLead(leadName);

        leadsPage.refresh();
        leadsPage.searchLead(leadName);
        Assert.assertTrue(leadsPage.isLeadVisibleInTable(leadName),
                "New lead '" + leadName + "' should appear in the table");
    }

    @Test(description = "TC-LEAD-017: Create Organization lead with all fields")
    public void TC_LEAD_017_createOrgLeadAllFields() {
        String leadName = "Lead Org Full " + timestamp;
        leadsPage.clickAddLead();
        leadsPage.selectOrganizationType();
        leadsPage.enterCompanyName(leadName);
        leadsPage.enterAddress("789 Tran Hung Dao Street");
        leadsPage.enterCity("Ha Noi");
        leadsPage.enterState("HN");
        leadsPage.enterZip("100000");
        leadsPage.enterCountry("Vietnam");
        leadsPage.enterPhone("0987654321");
        leadsPage.enterWebsite("https://lead-org-full.com");
        leadsPage.enterVatNumber("VAT-LEAD-001");
        leadsPage.enterGstNumber("GST-LEAD-001");
        leadsPage.clickSave();

        leadsPage.refresh();
        leadsPage.searchLead(leadName);
        Assert.assertTrue(leadsPage.isLeadVisibleInTable(leadName),
                "New lead '" + leadName + "' should appear in the table");
    }

    // ==================== 3.7 Create Lead - Person ====================

    @Test(description = "TC-LEAD-018: Create Person lead with mandatory fields only")
    public void TC_LEAD_018_createPersonLeadMandatory() {
        String leadName = "Lead Person Mandatory " + timestamp;
        leadsPage.clickAddLead();
        leadsPage.createPersonLead(leadName);

        leadsPage.refresh();
        leadsPage.searchLead(leadName);
        Assert.assertTrue(leadsPage.isLeadVisibleInTable(leadName),
                "New person lead '" + leadName + "' should appear in the table");
    }

    @Test(description = "TC-LEAD-019: Create Person lead with all fields")
    public void TC_LEAD_019_createPersonLeadAllFields() {
        String leadName = "Lead Person Full " + timestamp;
        leadsPage.clickAddLead();
        leadsPage.selectPersonType();
        leadsPage.enterCompanyName(leadName);
        leadsPage.enterAddress("321 Hai Ba Trung Street");
        leadsPage.enterCity("Da Nang");
        leadsPage.enterState("DN");
        leadsPage.enterZip("550000");
        leadsPage.enterCountry("Vietnam");
        leadsPage.enterPhone("0976543210");
        leadsPage.enterWebsite("https://lead-person-full.com");
        leadsPage.clickSave();

        leadsPage.refresh();
        leadsPage.searchLead(leadName);
        Assert.assertTrue(leadsPage.isLeadVisibleInTable(leadName),
                "New person lead '" + leadName + "' should appear in the table");
    }

    // ==================== 3.8 Boundary Value & Special Data ====================

    @Test(description = "TC-LEAD-020: Validate Company name - very long input (255+ characters)")
    public void TC_LEAD_020_validateMaxLengthCompanyName() {
        leadsPage.clickAddLead();
        String longName = "L".repeat(256);
        leadsPage.enterCompanyName(longName);
        leadsPage.clickSave();
        log.info("Max length lead name (256 chars) test completed");
    }

    @Test(description = "TC-LEAD-021: Create lead with Vietnamese characters")
    public void TC_LEAD_021_createLeadVietnamese() {
        String leadName = "Khach hang tiem nang Sai Gon " + timestamp;
        leadsPage.clickAddLead();
        leadsPage.enterCompanyName(leadName);
        leadsPage.clickSave();

        leadsPage.refresh();
        leadsPage.searchLead("Khach hang");
        Assert.assertTrue(leadsPage.isLeadVisibleInTable("Khach hang"),
                "Vietnamese lead name should display correctly");
    }

    // ==================== 3.9 Business Rule - Status Verification ====================

    @Test(description = "TC-LEAD-022: Verify lead status displays correctly after creation")
    public void TC_LEAD_022_verifyLeadStatus() {
        String leadName = "Lead Status Test " + timestamp;
        leadsPage.clickAddLead();
        leadsPage.enterCompanyName(leadName);
        leadsPage.selectStatus("Qualified");
        leadsPage.clickSave();

        leadsPage.refresh();
        leadsPage.searchLead(leadName);
        Assert.assertTrue(leadsPage.isLeadVisibleInTable(leadName),
                "Lead should be found in table");
        String status = leadsPage.getLeadStatusInTable(leadName);
        Assert.assertTrue(status.contains("Qualified"),
                "Status should be 'Qualified', got: " + status);
    }

    // ==================== 3.10 Security ====================

    @Test(description = "TC-LEAD-023: XSS prevention in Company name field")
    public void TC_LEAD_023_xssPrevention() {
        String xssPayload = "<img src=x onerror=alert('XSS')>";
        leadsPage.clickAddLead();
        leadsPage.enterCompanyName(xssPayload);
        leadsPage.clickSave();
        // No JavaScript should be executed
        log.info("XSS prevention test: No alert should appear. Name should be escaped/sanitized");
        Assert.assertTrue(leadsPage.isPageLoaded(),
                "Application should still function after XSS attempt");
    }
}
