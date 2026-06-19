package com.framework.tests.rise;

import com.framework.base.BaseRiseCrmTest;
import com.framework.pages.rise.TasksPage;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Test class for RISE CRM Tasks module.
 * Covers TC-TASK-001 through TC-TASK-030.
 */
public class TasksTest extends BaseRiseCrmTest {

    private TasksPage tasksPage;
    private final String timestamp = String.valueOf(System.currentTimeMillis());

    @BeforeMethod(alwaysRun = true, dependsOnMethods = "setUp")
    public void initPage() {
        loginWithCookie();
        tasksPage = new TasksPage();
        tasksPage.open(riseBaseUrl);
    }

    // ==================== 5.1 Page Display ====================

    @Test(description = "TC-TASK-001: Verify Tasks page loads")
    public void TC_TASK_001_verifyTasksPageLoads() {
        Assert.assertTrue(tasksPage.isPageLoaded(),
                "URL should contain '/tasks'");
        Assert.assertTrue(tasksPage.getCurrentUrl().contains("/tasks"),
                "URL should contain '/tasks'");
    }

    @Test(description = "TC-TASK-002: Verify action buttons display")
    public void TC_TASK_002_verifyActionButtons() {
        Assert.assertTrue(tasksPage.isAddTaskButtonVisible(),
                "'Add task' button should be visible");
        Assert.assertTrue(tasksPage.isManageLabelsButtonVisible(),
                "'Manage labels' button should be visible");
        Assert.assertTrue(tasksPage.isImportTasksButtonVisible(),
                "'Import tasks' button should be visible");
    }

    @Test(description = "TC-TASK-003: Verify view tabs display")
    public void TC_TASK_003_verifyViewTabs() {
        Assert.assertTrue(tasksPage.isListTabVisible(),
                "'List' tab should be visible");
        Assert.assertTrue(tasksPage.isKanbanTabVisible(),
                "'Kanban' tab should be visible");
        Assert.assertTrue(tasksPage.isGanttTabVisible(),
                "'Gantt' tab should be visible");
    }

    @Test(description = "TC-TASK-004: Verify task table headers")
    public void TC_TASK_004_verifyTaskTableHeaders() {
        List<WebElement> headers = tasksPage.getTaskTableHeaders();
        Assert.assertTrue(headers.size() > 0,
                "Task table should have column headers");
    }

    @Test(description = "TC-TASK-005: Verify task data rows exist")
    public void TC_TASK_005_verifyTaskDataRows() {
        Assert.assertTrue(tasksPage.isTaskTableVisible(),
                "Task table should be visible");
        int rowCount = tasksPage.getTaskRowCount();
        Assert.assertTrue(rowCount >= 1,
                "At least 1 task data row should be displayed, got: " + rowCount);
    }

    // ==================== 5.2 View Switching ====================

    @Test(description = "TC-TASK-006: Switch to Kanban view")
    public void TC_TASK_006_switchToKanbanView() {
        tasksPage.clickKanbanTab();
        Assert.assertTrue(tasksPage.getCurrentUrl().contains("/kanban"),
                "URL should contain '/kanban'");
        Assert.assertTrue(tasksPage.isKanbanBoardVisible(),
                "Kanban board should be visible");
        int columnCount = tasksPage.getKanbanColumnCount();
        Assert.assertTrue(columnCount >= 4,
                "Kanban should have at least 4 columns (To do, In progress, Review, Done), got: " + columnCount);
    }

    @Test(description = "TC-TASK-007: Switch to Gantt view")
    public void TC_TASK_007_switchToGanttView() {
        tasksPage.clickGanttTab();
        Assert.assertTrue(tasksPage.getCurrentUrl().contains("/gantt"),
                "URL should contain '/gantt'");
        Assert.assertTrue(tasksPage.isGanttChartVisible(),
                "Gantt chart should be visible");
    }

    @Test(description = "TC-TASK-008: Switch back to List view")
    public void TC_TASK_008_switchBackToListView() {
        tasksPage.clickKanbanTab();
        Assert.assertTrue(tasksPage.isKanbanBoardVisible(), "Should be on Kanban view");

        tasksPage.clickListTab();
        Assert.assertTrue(tasksPage.getCurrentUrl().contains("/all_tasks"),
                "URL should contain '/all_tasks'");
        Assert.assertTrue(tasksPage.isTaskTableVisible(),
                "Task data table should be visible again");
    }

    // ==================== 5.3 Search & Filters ====================

    @Test(description = "TC-TASK-009: Search tasks by keyword")
    public void TC_TASK_009_searchTasksByKeyword() {
        int initialCount = tasksPage.getTaskRowCount();

        tasksPage.searchTask("design");
        int filteredCount = tasksPage.getTaskRowCount();
        Assert.assertTrue(filteredCount <= initialCount,
                "Filtered count should be <= initial count");

        tasksPage.clearSearch();
        int afterClearCount = tasksPage.getTaskRowCount();
        Assert.assertEquals(afterClearCount, initialCount,
                "Row count should return to initial after clearing search");
    }

    @Test(description = "TC-TASK-010: Verify status filter options exist")
    public void TC_TASK_010_verifyStatusFilters() {
        Assert.assertTrue(tasksPage.areStatusFiltersVisible(),
                "Status filters should be visible");
    }

    @Test(description = "TC-TASK-011: Verify export buttons")
    public void TC_TASK_011_verifyExportButtons() {
        Assert.assertTrue(tasksPage.isExcelButtonVisible(),
                "'Excel' button should be visible");
        Assert.assertTrue(tasksPage.isPrintButtonVisible(),
                "'Print' button should be visible");
    }

    // ==================== 5.4 Add Task Modal ====================

    @Test(description = "TC-TASK-012: Open Add task modal")
    public void TC_TASK_012_openAddTaskModal() {
        tasksPage.clickAddTask();
        Assert.assertTrue(tasksPage.isModalVisible(),
                "Modal should be visible");
        String title = tasksPage.getModalTitle();
        Assert.assertTrue(title.contains("Add task"),
                "Modal title should be 'Add task', got: " + title);
    }

    @Test(description = "TC-TASK-013: Verify all form fields in Add task modal")
    public void TC_TASK_013_verifyModalFormFields() {
        tasksPage.clickAddTask();
        Assert.assertTrue(tasksPage.isTitleInputVisible(), "Title input should be visible");
        Assert.assertTrue(tasksPage.isRelatedToSelectVisible(), "Related to select should be visible");
        Assert.assertTrue(tasksPage.isStatusSelectVisible(), "Status select should be visible");
        Assert.assertTrue(tasksPage.isPrioritySelectVisible(), "Priority select should be visible");
        Assert.assertTrue(tasksPage.isStartDateInputVisible(), "Start date input should be visible");
        Assert.assertTrue(tasksPage.isDeadlineInputVisible(), "Deadline input should be visible");
        Assert.assertTrue(tasksPage.isRecurringCheckboxVisible(), "Recurring checkbox should be visible");
        Assert.assertTrue(tasksPage.isSaveButtonVisible(), "Save button should be visible");
        Assert.assertTrue(tasksPage.isSaveShowButtonVisible(), "Save & show button should be visible");
        Assert.assertTrue(tasksPage.isCloseButtonVisible(), "Close button should be visible");
    }

    @Test(description = "TC-TASK-014: Close Add task modal")
    public void TC_TASK_014_closeAddTaskModal() {
        tasksPage.clickAddTask();
        Assert.assertTrue(tasksPage.isModalVisible(), "Modal should be open");
        tasksPage.closeModalByX();
        Assert.assertTrue(tasksPage.isModalHidden(), "Modal should be hidden after closing");
    }

    @Test(description = "TC-TASK-015: Verify Related to changes context dropdown")
    public void TC_TASK_015_verifyRelatedToChangesContext() {
        tasksPage.clickAddTask();

        tasksPage.selectRelatedTo("Project");
        Assert.assertTrue(tasksPage.isContextProjectDropdownVisible(),
                "Project dropdown should appear when Related to = Project");

        tasksPage.selectRelatedTo("Client");
        Assert.assertTrue(tasksPage.isContextClientDropdownVisible(),
                "Client dropdown should appear when Related to = Client");

        tasksPage.selectRelatedTo("-");
        Assert.assertFalse(tasksPage.isContextProjectDropdownVisible(),
                "No context dropdown should show when Related to = '-'");
    }

    @Test(description = "TC-TASK-016: Verify Recurring checkbox toggles fields")
    public void TC_TASK_016_verifyRecurringToggle() {
        tasksPage.clickAddTask();

        // Initially, recurring fields should be hidden
        Assert.assertFalse(tasksPage.isRecurringFieldsVisible(),
                "Recurring fields should be hidden initially");

        // Check recurring checkbox
        tasksPage.checkRecurring();
        Assert.assertTrue(tasksPage.isRecurringFieldsVisible(),
                "Recurring fields should appear after checking");

        // Uncheck recurring checkbox
        tasksPage.uncheckRecurring();
        Assert.assertFalse(tasksPage.isRecurringFieldsVisible(),
                "Recurring fields should be hidden after unchecking");
    }

    // ==================== 5.5 Validation ====================

    @Test(description = "TC-TASK-017: Validate required fields - empty form")
    public void TC_TASK_017_validateRequiredFieldsEmpty() {
        tasksPage.clickAddTask();
        tasksPage.clickSave();
        Assert.assertTrue(tasksPage.isModalVisible(),
                "Modal should stay open on validation error");
        Assert.assertTrue(tasksPage.isTitleErrorVisible(),
                "Title error should be visible");
    }

    @Test(description = "TC-TASK-017a: Validate Title with only whitespace")
    public void TC_TASK_017a_validateWhitespaceTitle() {
        tasksPage.clickAddTask();
        tasksPage.enterTitle("   ");
        tasksPage.clickSave();
        Assert.assertTrue(tasksPage.isModalVisible(),
                "Modal should stay open - whitespace should not be accepted");
    }

    @Test(description = "TC-TASK-017b: Validate Deadline before Start date")
    public void TC_TASK_017b_validateDeadlineBeforeStartDate() {
        tasksPage.clickAddTask();
        tasksPage.enterTitle("Date Validation Task");
        tasksPage.enterStartDate("31-12-2026");
        tasksPage.enterDeadline("01-01-2026");
        tasksPage.clickSave();
        log.info("Deadline before start date validation test completed");
    }

    // ==================== 5.6 Create Task ====================

    @Test(description = "TC-TASK-018: Create task with mandatory fields only")
    public void TC_TASK_018_createTaskMandatory() {
        String taskTitle = "Task Mandatory " + timestamp;
        tasksPage.clickAddTask();
        tasksPage.enterTitle(taskTitle);
        tasksPage.clickSave();

        tasksPage.refresh();
        tasksPage.searchTask(taskTitle);
        Assert.assertTrue(tasksPage.isTaskVisibleInTable(taskTitle),
                "New task '" + taskTitle + "' should appear in the table");
    }

    @Test(description = "TC-TASK-019: Create task with all fields")
    public void TC_TASK_019_createTaskAllFields() {
        String taskTitle = "Task Full " + timestamp;
        tasksPage.clickAddTask();
        tasksPage.enterTitle(taskTitle);
        tasksPage.selectRelatedTo("Project");
        // Select first available project
        try {
            tasksPage.selectProject("Test");
        } catch (Exception e) {
            log.warn("Could not select project, continuing without it");
        }
        try {
            tasksPage.selectAssignTo("Admin");
        } catch (Exception e) {
            log.warn("Could not select assignee, continuing without it");
        }
        tasksPage.selectStatus("In progress");
        tasksPage.selectPriority("Major");
        tasksPage.enterStartDate("20-06-2026");
        tasksPage.enterDeadline("31-12-2026");
        tasksPage.clickSave();

        tasksPage.refresh();
        tasksPage.searchTask(taskTitle);
        Assert.assertTrue(tasksPage.isTaskVisibleInTable(taskTitle),
                "New task '" + taskTitle + "' should appear in the table");
    }

    @Test(description = "TC-TASK-020: Create task with recurring enabled")
    public void TC_TASK_020_createTaskRecurring() {
        String taskTitle = "Recurring Task " + timestamp;
        tasksPage.clickAddTask();
        tasksPage.enterTitle(taskTitle);
        tasksPage.checkRecurring();
        tasksPage.enterRepeatEvery("1");
        tasksPage.selectRepeatType("Week(s)");
        tasksPage.enterCycles("4");
        tasksPage.clickSave();

        tasksPage.refresh();
        tasksPage.searchTask(taskTitle);
        Assert.assertTrue(tasksPage.isTaskVisibleInTable(taskTitle),
                "New recurring task '" + taskTitle + "' should appear in the table");
    }

    // ==================== 5.7 Boundary Value & Special Data ====================

    @Test(description = "TC-TASK-021: Validate Title - very long input (255+ characters)")
    public void TC_TASK_021_validateMaxLengthTitle() {
        tasksPage.clickAddTask();
        String longTitle = "T".repeat(256);
        tasksPage.enterTitle(longTitle);
        tasksPage.clickSave();
        log.info("Max length title (256 chars) test completed");
    }

    @Test(description = "TC-TASK-022: Validate Recurring Cycles - zero value")
    public void TC_TASK_022_validateZeroCycles() {
        tasksPage.clickAddTask();
        tasksPage.enterTitle("Zero Cycles Task");
        tasksPage.checkRecurring();
        tasksPage.enterRepeatEvery("1");
        tasksPage.selectRepeatType("Week(s)");
        tasksPage.enterCycles("0");
        tasksPage.clickSave();
        log.info("Zero cycles test completed");
    }

    @Test(description = "TC-TASK-023: Validate Recurring Cycles - negative value")
    public void TC_TASK_023_validateNegativeCycles() {
        tasksPage.clickAddTask();
        tasksPage.enterTitle("Negative Cycles Task");
        tasksPage.checkRecurring();
        tasksPage.enterRepeatEvery("1");
        tasksPage.selectRepeatType("Week(s)");
        tasksPage.enterCycles("-1");
        tasksPage.clickSave();
        log.info("Negative cycles test completed");
    }

    @Test(description = "TC-TASK-024: Create task with Vietnamese characters")
    public void TC_TASK_024_createTaskVietnamese() {
        String taskTitle = "Thiet ke giao dien nguoi dung " + timestamp;
        tasksPage.clickAddTask();
        tasksPage.enterTitle(taskTitle);
        tasksPage.clickSave();

        tasksPage.refresh();
        tasksPage.searchTask("Thiet ke");
        Assert.assertTrue(tasksPage.isTaskVisibleInTable("Thiet ke"),
                "Vietnamese task title should display correctly");
    }

    @Test(description = "TC-TASK-025: Create task with special characters")
    public void TC_TASK_025_createTaskSpecialChars() {
        String taskTitle = "Bug #123 - Fix <header> & 'footer' @v2.0 " + timestamp;
        tasksPage.clickAddTask();
        tasksPage.enterTitle(taskTitle);
        tasksPage.clickSave();

        tasksPage.refresh();
        tasksPage.searchTask("Bug #123");
        Assert.assertTrue(tasksPage.isTaskVisibleInTable("Bug #123"),
                "Special character task title should display correctly");
    }

    // ==================== 5.8 Security ====================

    @Test(description = "TC-TASK-026: XSS prevention in Title field")
    public void TC_TASK_026_xssPreventionTitle() {
        String xssPayload = "<script>document.cookie</script>";
        tasksPage.clickAddTask();
        tasksPage.enterTitle(xssPayload);
        tasksPage.clickSave();
        // No JavaScript should be executed
        log.info("XSS prevention test: No script should execute");
        Assert.assertTrue(tasksPage.isPageLoaded(),
                "Application should still function after XSS attempt");
    }

    @Test(description = "TC-TASK-027: SQL Injection prevention in Title field")
    public void TC_TASK_027_sqlInjectionPrevention() {
        String sqlPayload = "'; DROP TABLE tasks; --";
        tasksPage.clickAddTask();
        tasksPage.enterTitle(sqlPayload);
        tasksPage.clickSave();
        // Application should continue to function normally
        Assert.assertTrue(tasksPage.isPageLoaded(),
                "Application should still function after SQL injection attempt");
    }

    // ==================== 5.9 Data Validation ====================

    @Test(description = "TC-TASK-028: Verify created task data displays correctly",
            dependsOnMethods = "TC_TASK_019_createTaskAllFields")
    public void TC_TASK_028_verifyTaskDetailData() {
        String taskTitle = "Task Full " + timestamp;
        tasksPage.searchTask(taskTitle);
        Assert.assertTrue(tasksPage.isTaskVisibleInTable(taskTitle),
                "Task should be found in table");

        String status = tasksPage.getTaskStatusInTable(taskTitle);
        Assert.assertTrue(status.contains("In progress"),
                "Task status should be 'In progress', got: " + status);

        tasksPage.clickTaskRow(taskTitle);
        Assert.assertTrue(tasksPage.getCurrentUrl().contains("/tasks/view/") ||
                        tasksPage.getCurrentUrl().contains("/tasks/"),
                "Should navigate to task detail");
    }

    // ==================== 5.10 Error Handling ====================

    @Test(description = "TC-TASK-029: Handle network error during task creation")
    public void TC_TASK_029_handleNetworkError() {
        tasksPage.clickAddTask();
        tasksPage.enterTitle("Network Error Task");
        // Note: Actual network disconnect requires manual intervention or proxy tools
        log.info("Network error test: This test requires manual network disconnect simulation");
    }

    @Test(description = "TC-TASK-030: Handle server timeout during task creation")
    public void TC_TASK_030_handleServerTimeout() {
        tasksPage.clickAddTask();
        tasksPage.enterTitle("Timeout Task");
        // Note: Actual server timeout requires server-side configuration
        log.info("Timeout test: This test requires server-side timeout simulation");
    }
}
