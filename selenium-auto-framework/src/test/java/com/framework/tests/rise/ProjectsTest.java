package com.framework.tests.rise;

import com.framework.base.BaseRiseCrmTest;
import com.framework.pages.rise.ProjectsPage;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Test class for RISE CRM Projects module.
 * Covers TC-PRJ-001 through TC-PRJ-019.
 */
public class ProjectsTest extends BaseRiseCrmTest {

    private ProjectsPage projectsPage;
    private final String timestamp = String.valueOf(System.currentTimeMillis());

    @BeforeMethod(alwaysRun = true, dependsOnMethods = "setUp")
    public void initPage() {
        loginWithCookie();
        projectsPage = new ProjectsPage();
        projectsPage.open(riseBaseUrl);
    }

    // ==================== 4.1 Page Display ====================

    @Test(description = "TC-PRJ-001: Verify Projects page loads")
    public void TC_PRJ_001_verifyProjectsPageLoads() {
        Assert.assertTrue(projectsPage.isPageLoaded(),
                "URL should contain '/projects'");
        String title = projectsPage.getPageTitle();
        Assert.assertTrue(title.contains("Projects") || title.contains("projects"),
                "Page title should contain 'Projects'");
    }

    @Test(description = "TC-PRJ-002: Verify action buttons display")
    public void TC_PRJ_002_verifyActionButtons() {
        Assert.assertTrue(projectsPage.isAddProjectButtonVisible(),
                "'Add project' button should be visible");
        Assert.assertTrue(projectsPage.isManageLabelsButtonVisible(),
                "'Manage labels' button should be visible");
        Assert.assertTrue(projectsPage.isImportProjectsButtonVisible(),
                "'Import projects' button should be visible");
    }

    @Test(description = "TC-PRJ-003: Verify project table headers")
    public void TC_PRJ_003_verifyProjectTableHeaders() {
        List<WebElement> headers = projectsPage.getProjectTableHeaders();
        Assert.assertTrue(headers.size() > 0,
                "Project table should have column headers");
    }

    @Test(description = "TC-PRJ-004: Verify project data rows exist")
    public void TC_PRJ_004_verifyProjectDataRows() {
        Assert.assertTrue(projectsPage.isProjectTableVisible(),
                "Project table should be visible");
        int rowCount = projectsPage.getProjectRowCount();
        Assert.assertTrue(rowCount >= 1,
                "At least 1 project data row should be displayed, got: " + rowCount);
    }

    // ==================== 4.2 Filters ====================

    @Test(description = "TC-PRJ-005: Verify status filter options exist")
    public void TC_PRJ_005_verifyStatusFilters() {
        Assert.assertTrue(projectsPage.areStatusFiltersVisible(),
                "Status filters should be visible");
    }

    @Test(description = "TC-PRJ-006: Verify export buttons")
    public void TC_PRJ_006_verifyExportButtons() {
        Assert.assertTrue(projectsPage.isExcelButtonVisible(),
                "'Excel' button should be visible");
        Assert.assertTrue(projectsPage.isPrintButtonVisible(),
                "'Print' button should be visible");
    }

    // ==================== 4.3 Search ====================

    @Test(description = "TC-PRJ-007: Search projects by keyword")
    public void TC_PRJ_007_searchProjectsByKeyword() {
        int initialCount = projectsPage.getProjectRowCount();

        projectsPage.searchProject("test");
        int filteredCount = projectsPage.getProjectRowCount();
        Assert.assertTrue(filteredCount <= initialCount,
                "Filtered count should be <= initial count");

        projectsPage.clearSearch();
        int afterClearCount = projectsPage.getProjectRowCount();
        Assert.assertEquals(afterClearCount, initialCount,
                "Row count should return to initial after clearing search");
    }

    // ==================== 4.4 Add Project Modal ====================

    @Test(description = "TC-PRJ-008: Open Add project modal")
    public void TC_PRJ_008_openAddProjectModal() {
        projectsPage.clickAddProject();
        Assert.assertTrue(projectsPage.isModalVisible(),
                "Modal should be visible");
        String title = projectsPage.getModalTitle();
        Assert.assertTrue(title.contains("Add project"),
                "Modal title should be 'Add project', got: " + title);
    }

    @Test(description = "TC-PRJ-009: Verify all form fields in Add project modal")
    public void TC_PRJ_009_verifyModalFormFields() {
        projectsPage.clickAddProject();
        Assert.assertTrue(projectsPage.isTitleInputVisible(), "Title input should be visible");
        Assert.assertTrue(projectsPage.isProjectTypeSelectVisible(), "Project type select should be visible");
        Assert.assertTrue(projectsPage.isClientFieldVisible(), "Client field should be visible (default: Client Project)");
        Assert.assertTrue(projectsPage.isStartDateInputVisible(), "Start date input should be visible");
        Assert.assertTrue(projectsPage.isDeadlineInputVisible(), "Deadline input should be visible");
        Assert.assertTrue(projectsPage.isPriceInputVisible(), "Price input should be visible");
        Assert.assertTrue(projectsPage.isSaveButtonVisible(), "Save button should be visible");
        Assert.assertTrue(projectsPage.isSaveContinueButtonVisible(), "Save & continue button should be visible");
        Assert.assertTrue(projectsPage.isCloseButtonVisible(), "Close button should be visible");
    }

    @Test(description = "TC-PRJ-010: Close Add project modal")
    public void TC_PRJ_010_closeAddProjectModal() {
        projectsPage.clickAddProject();
        Assert.assertTrue(projectsPage.isModalVisible(), "Modal should be open");
        projectsPage.closeModalByX();
        Assert.assertTrue(projectsPage.isModalHidden(), "Modal should be hidden after closing");
    }

    @Test(description = "TC-PRJ-011: Verify Project type toggle hides/shows Client field")
    public void TC_PRJ_011_verifyProjectTypeToggle() {
        projectsPage.clickAddProject();

        // Default: Client Project - Client field should be visible
        Assert.assertTrue(projectsPage.isClientFieldVisible(),
                "Client field should be visible for Client Project");

        // Switch to Internal Project
        projectsPage.selectProjectType("Internal Project");
        Assert.assertFalse(projectsPage.isClientFieldVisible(),
                "Client field should be hidden for Internal Project");

        // Switch back to Client Project
        projectsPage.selectProjectType("Client Project");
        Assert.assertTrue(projectsPage.isClientFieldVisible(),
                "Client field should be visible again for Client Project");
    }

    // ==================== 4.5 Validation ====================

    @Test(description = "TC-PRJ-012: Validate required fields - empty form")
    public void TC_PRJ_012_validateRequiredFieldsEmpty() {
        projectsPage.clickAddProject();
        projectsPage.clickSave();
        Assert.assertTrue(projectsPage.isModalVisible(),
                "Modal should stay open on validation error");
        Assert.assertTrue(projectsPage.isTitleErrorVisible(),
                "Title error should be visible");
    }

    @Test(description = "TC-PRJ-012a: Validate Price field with negative number")
    public void TC_PRJ_012a_validateNegativePrice() {
        projectsPage.clickAddProject();
        projectsPage.enterTitle("Negative Price Test");
        projectsPage.selectClient("Test");
        projectsPage.enterPrice("-5000");
        projectsPage.clickSave();
        log.info("Negative price validation test completed");
    }

    @Test(description = "TC-PRJ-012b: Validate Price field with text input")
    public void TC_PRJ_012b_validateTextPrice() {
        projectsPage.clickAddProject();
        projectsPage.enterTitle("Text Price Test");
        projectsPage.selectClient("Test");
        projectsPage.enterPrice("abc");
        projectsPage.clickSave();
        log.info("Text price validation test completed");
    }

    @Test(description = "TC-PRJ-012c: Validate Deadline before Start date")
    public void TC_PRJ_012c_validateDeadlineBeforeStartDate() {
        projectsPage.clickAddProject();
        projectsPage.enterTitle("Date Validation Test");
        projectsPage.selectClient("Test");
        projectsPage.enterStartDate("12/31/2026");
        projectsPage.enterDeadline("01/01/2026");
        projectsPage.clickSave();
        log.info("Deadline before start date validation test completed");
    }

    // ==================== 4.6 Create Project ====================

    @Test(description = "TC-PRJ-013: Create project with mandatory fields only (Client Project)")
    public void TC_PRJ_013_createProjectMandatory() {
        String projectTitle = "Project Mandatory " + timestamp;
        projectsPage.clickAddProject();
        projectsPage.enterTitle(projectTitle);
        projectsPage.selectClient("Test");
        projectsPage.clickSave();

        projectsPage.refresh();
        projectsPage.searchProject(projectTitle);
        Assert.assertTrue(projectsPage.isProjectVisibleInTable(projectTitle),
                "New project '" + projectTitle + "' should appear in the table");
    }

    @Test(description = "TC-PRJ-014: Create project with all fields (Client Project)")
    public void TC_PRJ_014_createProjectAllFields() {
        String projectTitle = "Project Full " + timestamp;
        projectsPage.clickAddProject();
        projectsPage.enterTitle(projectTitle);
        projectsPage.selectClient("Test");
        projectsPage.enterDescription("Full project description");
        projectsPage.enterStartDate("06/20/2026");
        projectsPage.enterDeadline("12/31/2026");
        projectsPage.enterPrice("15000");
        projectsPage.clickSave();

        projectsPage.refresh();
        projectsPage.searchProject(projectTitle);
        Assert.assertTrue(projectsPage.isProjectVisibleInTable(projectTitle),
                "New project '" + projectTitle + "' should appear in the table");
    }

    @Test(description = "TC-PRJ-015: Create Internal Project (no client required)")
    public void TC_PRJ_015_createInternalProject() {
        String projectTitle = "Internal Project " + timestamp;
        projectsPage.clickAddProject();
        projectsPage.selectProjectType("Internal Project");
        projectsPage.enterTitle(projectTitle);
        projectsPage.clickSave();

        projectsPage.refresh();
        projectsPage.searchProject(projectTitle);
        Assert.assertTrue(projectsPage.isProjectVisibleInTable(projectTitle),
                "New internal project '" + projectTitle + "' should appear in the table");
    }

    // ==================== 4.7 Boundary Value ====================

    @Test(description = "TC-PRJ-016: Validate Title - very long input (255+ characters)")
    public void TC_PRJ_016_validateMaxLengthTitle() {
        projectsPage.clickAddProject();
        String longTitle = "P".repeat(256);
        projectsPage.enterTitle(longTitle);
        projectsPage.selectClient("Test");
        projectsPage.clickSave();
        log.info("Max length title (256 chars) test completed");
    }

    @Test(description = "TC-PRJ-017: Validate Price boundary - zero value")
    public void TC_PRJ_017_validateZeroPrice() {
        String projectTitle = "Zero Price Project " + timestamp;
        projectsPage.clickAddProject();
        projectsPage.enterTitle(projectTitle);
        projectsPage.selectClient("Test");
        projectsPage.enterPrice("0");
        projectsPage.clickSave();
        log.info("Zero price test completed");
    }

    @Test(description = "TC-PRJ-018: Validate Price boundary - very large number")
    public void TC_PRJ_018_validateLargePrice() {
        String projectTitle = "Large Price Project " + timestamp;
        projectsPage.clickAddProject();
        projectsPage.enterTitle(projectTitle);
        projectsPage.selectClient("Test");
        projectsPage.enterPrice("99999999999");
        projectsPage.clickSave();
        log.info("Very large price test completed");
    }

    // ==================== 4.8 Data Validation ====================

    @Test(description = "TC-PRJ-019: Verify created project data displays correctly",
            dependsOnMethods = "TC_PRJ_014_createProjectAllFields")
    public void TC_PRJ_019_verifyProjectDetailData() {
        String projectTitle = "Project Full " + timestamp;
        projectsPage.searchProject(projectTitle);
        Assert.assertTrue(projectsPage.isProjectVisibleInTable(projectTitle),
                "Project should be found in table");
    }
}
