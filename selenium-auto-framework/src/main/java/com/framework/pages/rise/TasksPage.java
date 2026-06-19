package com.framework.pages.rise;

import com.framework.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Page Object for RISE CRM Tasks page.
 * Covers TC-TASK-001 through TC-TASK-030.
 */
public class TasksPage extends BasePage {

    // ==================== Tabs ====================
    private final By listTab = By.xpath("//a[contains(text(),'List')]");
    private final By kanbanTab = By.xpath("//a[contains(text(),'Kanban')]");
    private final By ganttTab = By.xpath("//a[contains(text(),'Gantt')]");

    // ==================== Action Buttons ====================
    private final By addTaskButton = By.xpath("//a[contains(text(),'Add task')] | //button[contains(text(),'Add task')]");
    private final By manageLabelsButton = By.xpath("//a[contains(text(),'Manage labels')] | //button[contains(text(),'Manage labels')]");
    private final By importTasksButton = By.xpath("//a[contains(text(),'Import tasks')] | //button[contains(text(),'Import tasks')]");

    // ==================== Task Table ====================
    private final By taskTable = By.cssSelector("#task-table, table.dataTable");
    private final By taskTableHeaders = By.cssSelector("#task-table thead th, table.dataTable thead th");
    private final By taskTableRows = By.cssSelector("#task-table tbody tr, table.dataTable tbody tr");
    private final By searchInput = By.cssSelector("input[type='search'], .dataTables_filter input");

    // ==================== Kanban Board ====================
    private final By kanbanBoard = By.cssSelector(".kanban-board, .board-group");
    private final By kanbanColumns = By.cssSelector(".kanban-col, .board-column");

    // ==================== Gantt Chart ====================
    private final By ganttChart = By.cssSelector(".gantt-chart, .gantt, #gantt");

    // ==================== Filters ====================
    private final By statusFilters = By.cssSelector(".filter-section .status-filter, .task-status-filter a, [data-filter='status']");

    // ==================== Export Buttons ====================
    private final By excelButton = By.xpath("//a[contains(text(),'Excel')] | //button[contains(text(),'Excel')]");
    private final By printButton = By.xpath("//a[contains(text(),'Print')] | //button[contains(text(),'Print')]");

    // ==================== Add Task Modal ====================
    private final By modal = By.cssSelector(".modal.show, .modal.in");
    private final By modalTitle = By.cssSelector(".modal.show .modal-title, .modal.in .modal-title");
    private final By modalCloseX = By.cssSelector(".modal.show .close, .modal.show .btn-close, .modal.in .close");
    private final By modalCloseButton = By.xpath("//div[contains(@class,'modal')]//button[contains(text(),'Close')]");
    private final By modalSaveButton = By.xpath("//div[contains(@class,'modal')]//button[contains(text(),'Save') and not(contains(text(),'show'))]");
    private final By modalSaveShowButton = By.xpath("//div[contains(@class,'modal')]//button[contains(text(),'Save & show')]");

    // ==================== Form Fields ====================
    private final By titleInput = By.cssSelector("input[name='title'], input#title");
    private final By descriptionTextarea = By.cssSelector("textarea[name='description'], #description");
    private final By relatedToSelect = By.cssSelector("select[name='related_to'], #related_to, select[name='context']");
    private final By contextProjectSelect = By.cssSelector("select[name='project_id'], #project_id");
    private final By contextClientSelect = By.cssSelector("select[name='client_id'], #task_client_id");
    private final By pointsSelect = By.cssSelector("select[name='points'], #points");
    private final By milestoneSelect = By.cssSelector("select[name='milestone_id'], #milestone_id");
    private final By assignToSelect = By.cssSelector("select[name='assigned_to'], #assigned_to");
    private final By collaboratorsSelect = By.cssSelector("select[name='collaborators[]'], #collaborators");
    private final By statusSelect = By.cssSelector("select[name='status_id'], #status_id, select[name='status']");
    private final By prioritySelect = By.cssSelector("select[name='priority_id'], #priority_id, select[name='priority']");
    private final By labelsSelect = By.cssSelector("select[name='labels[]'], #labels");
    private final By startDateInput = By.cssSelector("input[name='start_date'], #start_date");
    private final By deadlineInput = By.cssSelector("input[name='deadline'], #deadline");
    private final By recurringCheckbox = By.cssSelector("input[name='recurring'], #recurring, input[type='checkbox'][id*='recurring']");
    private final By recurringFields = By.cssSelector(".recurring-fields, #recurring-fields, [data-recurring-fields]");
    private final By repeatEveryInput = By.cssSelector("input[name='repeat_every'], #repeat_every");
    private final By repeatTypeSelect = By.cssSelector("select[name='repeat_type'], #repeat_type");
    private final By cyclesInput = By.cssSelector("input[name='no_of_cycles'], #no_of_cycles, input[name='cycles']");

    // ==================== Validation Errors ====================
    private final By titleError = By.xpath("//input[@name='title' or @id='title']/following-sibling::*[contains(@class,'error')] | //input[@name='title' or @id='title']/parent::*//*[contains(@class,'error')]");

    // ==================== Toast/Notification ====================
    private final By successToast = By.cssSelector(".toast-success, .alert-success");

    public TasksPage() {
        super();
    }

    public void open(String baseUrl) {
        navigateTo(baseUrl + "/tasks/all_tasks");
    }

    // ==================== TC-TASK-001: Page Display ====================

    public boolean isPageLoaded() {
        return getCurrentUrl().contains("/tasks");
    }

    // ==================== TC-TASK-002: Action Buttons ====================

    public boolean isAddTaskButtonVisible() {
        return isDisplayed(addTaskButton);
    }

    public boolean isManageLabelsButtonVisible() {
        return isDisplayed(manageLabelsButton);
    }

    public boolean isImportTasksButtonVisible() {
        return isDisplayed(importTasksButton);
    }

    // ==================== TC-TASK-003: View Tabs ====================

    public boolean isListTabVisible() {
        return isDisplayed(listTab);
    }

    public boolean isKanbanTabVisible() {
        return isDisplayed(kanbanTab);
    }

    public boolean isGanttTabVisible() {
        return isDisplayed(ganttTab);
    }

    // ==================== TC-TASK-004: Table Headers ====================

    public List<WebElement> getTaskTableHeaders() {
        return waitForAllVisible(taskTableHeaders);
    }

    // ==================== TC-TASK-005: Data Rows ====================

    public int getTaskRowCount() {
        try {
            List<WebElement> rows = waitForAllVisible(taskTableRows);
            return rows.size();
        } catch (Exception e) {
            return 0;
        }
    }

    public boolean isTaskTableVisible() {
        return isDisplayed(taskTable);
    }

    // ==================== TC-TASK-006 ~ 008: View Switching ====================

    public void clickKanbanTab() {
        click(kanbanTab);
    }

    public void clickGanttTab() {
        click(ganttTab);
    }

    public void clickListTab() {
        click(listTab);
    }

    public boolean isKanbanBoardVisible() {
        return isDisplayed(kanbanBoard);
    }

    public int getKanbanColumnCount() {
        try {
            return waitForAllVisible(kanbanColumns).size();
        } catch (Exception e) {
            return 0;
        }
    }

    public boolean isGanttChartVisible() {
        return isDisplayed(ganttChart);
    }

    // ==================== TC-TASK-009: Search ====================

    public void searchTask(String keyword) {
        type(searchInput, keyword);
    }

    public void clearSearch() {
        WebElement input = waitForVisible(searchInput);
        input.clear();
    }

    // ==================== TC-TASK-010: Filters ====================

    public boolean areStatusFiltersVisible() {
        return isDisplayed(statusFilters);
    }

    // ==================== TC-TASK-011: Export ====================

    public boolean isExcelButtonVisible() {
        return isDisplayed(excelButton);
    }

    public boolean isPrintButtonVisible() {
        return isDisplayed(printButton);
    }

    // ==================== TC-TASK-012 ~ 014: Modal ====================

    public void clickAddTask() {
        click(addTaskButton);
    }

    public String getModalTitle() {
        return getText(modalTitle);
    }

    public boolean isModalVisible() {
        return isDisplayed(modal);
    }

    public void closeModalByX() {
        click(modalCloseX);
    }

    public boolean isModalHidden() {
        return waitForInvisible(modal);
    }

    // ==================== TC-TASK-013: Form Field Visibility ====================

    public boolean isTitleInputVisible() {
        return isDisplayed(titleInput);
    }

    public boolean isDescriptionTextareaVisible() {
        return isDisplayed(descriptionTextarea);
    }

    public boolean isRelatedToSelectVisible() {
        return isDisplayed(relatedToSelect);
    }

    public boolean isPointsSelectVisible() {
        return isDisplayed(pointsSelect);
    }

    public boolean isMilestoneSelectVisible() {
        return isDisplayed(milestoneSelect);
    }

    public boolean isAssignToSelectVisible() {
        return isDisplayed(assignToSelect);
    }

    public boolean isCollaboratorsSelectVisible() {
        return isDisplayed(collaboratorsSelect);
    }

    public boolean isStatusSelectVisible() {
        return isDisplayed(statusSelect);
    }

    public boolean isPrioritySelectVisible() {
        return isDisplayed(prioritySelect);
    }

    public boolean isStartDateInputVisible() {
        return isDisplayed(startDateInput);
    }

    public boolean isDeadlineInputVisible() {
        return isDisplayed(deadlineInput);
    }

    public boolean isRecurringCheckboxVisible() {
        return isDisplayed(recurringCheckbox);
    }

    public boolean isSaveButtonVisible() {
        return isDisplayed(modalSaveButton);
    }

    public boolean isSaveShowButtonVisible() {
        return isDisplayed(modalSaveShowButton);
    }

    public boolean isCloseButtonVisible() {
        return isDisplayed(modalCloseButton);
    }

    // ==================== TC-TASK-015: Related To Context ====================

    public void selectRelatedTo(String context) {
        By select2 = By.xpath("//select[contains(@name,'related_to') or contains(@name,'context')]/following-sibling::span//span[contains(@class,'select2-selection')]");
        try {
            click(select2);
            By option = By.xpath("//li[contains(@class,'select2-results__option') and contains(text(),'" + context + "')]");
            click(option);
        } catch (Exception e) {
            selectByVisibleText(relatedToSelect, context);
        }
    }

    public boolean isContextProjectDropdownVisible() {
        return isDisplayed(contextProjectSelect);
    }

    public boolean isContextClientDropdownVisible() {
        return isDisplayed(contextClientSelect);
    }

    public void selectProject(String projectName) {
        By select2 = By.xpath("//select[contains(@name,'project_id') or @id='project_id']/following-sibling::span//span[contains(@class,'select2-selection')]");
        try {
            click(select2);
            By searchBox = By.cssSelector(".select2-search__field");
            type(searchBox, projectName);
            By option = By.xpath("//li[contains(@class,'select2-results__option') and contains(text(),'" + projectName + "')]");
            click(option);
        } catch (Exception e) {
            selectByVisibleText(contextProjectSelect, projectName);
        }
    }

    // ==================== TC-TASK-016: Recurring Checkbox ====================

    public void checkRecurring() {
        WebElement checkbox = waitForClickable(recurringCheckbox);
        if (!checkbox.isSelected()) {
            clickByJS(recurringCheckbox);
        }
    }

    public void uncheckRecurring() {
        WebElement checkbox = waitForClickable(recurringCheckbox);
        if (checkbox.isSelected()) {
            clickByJS(recurringCheckbox);
        }
    }

    public boolean isRecurringFieldsVisible() {
        return isDisplayed(recurringFields);
    }

    // ==================== Form Input Methods ====================

    public void enterTitle(String title) {
        type(titleInput, title);
    }

    public void enterDescription(String description) {
        type(descriptionTextarea, description);
    }

    public void selectAssignTo(String memberName) {
        By select2 = By.xpath("//select[contains(@name,'assigned_to') or @id='assigned_to']/following-sibling::span//span[contains(@class,'select2-selection')]");
        try {
            click(select2);
            By searchBox = By.cssSelector(".select2-search__field");
            type(searchBox, memberName);
            By option = By.xpath("//li[contains(@class,'select2-results__option') and contains(text(),'" + memberName + "')]");
            click(option);
        } catch (Exception e) {
            selectByVisibleText(assignToSelect, memberName);
        }
    }

    public void selectStatus(String status) {
        By select2 = By.xpath("//select[contains(@name,'status') or @id='status_id']/following-sibling::span//span[contains(@class,'select2-selection')]");
        try {
            click(select2);
            By option = By.xpath("//li[contains(@class,'select2-results__option') and contains(text(),'" + status + "')]");
            click(option);
        } catch (Exception e) {
            selectByVisibleText(statusSelect, status);
        }
    }

    public void selectPriority(String priority) {
        By select2 = By.xpath("//select[contains(@name,'priority') or @id='priority_id']/following-sibling::span//span[contains(@class,'select2-selection')]");
        try {
            click(select2);
            By option = By.xpath("//li[contains(@class,'select2-results__option') and contains(text(),'" + priority + "')]");
            click(option);
        } catch (Exception e) {
            selectByVisibleText(prioritySelect, priority);
        }
    }

    public void enterStartDate(String date) {
        type(startDateInput, date);
    }

    public void enterDeadline(String date) {
        type(deadlineInput, date);
    }

    public void enterRepeatEvery(String value) {
        type(repeatEveryInput, value);
    }

    public void selectRepeatType(String type) {
        By select2 = By.xpath("//select[contains(@name,'repeat_type') or @id='repeat_type']/following-sibling::span//span[contains(@class,'select2-selection')]");
        try {
            click(select2);
            By option = By.xpath("//li[contains(@class,'select2-results__option') and contains(text(),'" + type + "')]");
            click(option);
        } catch (Exception e) {
            selectByVisibleText(repeatTypeSelect, type);
        }
    }

    public void enterCycles(String cycles) {
        type(cyclesInput, cycles);
    }

    // ==================== TC-TASK-017: Validation ====================

    public void clickSave() {
        click(modalSaveButton);
    }

    public boolean isTitleErrorVisible() {
        return isDisplayed(titleError);
    }

    public String getTitleErrorText() {
        return getText(titleError);
    }

    // ==================== Create Task ====================

    public boolean isSuccessToastVisible() {
        return isDisplayed(successToast);
    }

    public boolean isTaskVisibleInTable(String taskTitle) {
        try {
            By taskLink = By.xpath("//td//a[contains(text(),'" + taskTitle + "')] | //td[contains(text(),'" + taskTitle + "')]");
            return isDisplayed(taskLink);
        } catch (Exception e) {
            return false;
        }
    }

    public String getTaskStatusInTable(String taskTitle) {
        try {
            By statusCell = By.xpath("//td//a[contains(text(),'" + taskTitle + "')]/ancestor::tr//span[contains(@class,'badge') or contains(@class,'label')]");
            return getText(statusCell);
        } catch (Exception e) {
            return "";
        }
    }

    public void clickTaskRow(String taskTitle) {
        By taskLink = By.xpath("//td//a[contains(text(),'" + taskTitle + "')]");
        click(taskLink);
    }

    // ==================== Utility Methods ====================

    public void goBack() {
        driver.navigate().back();
    }

    public void refresh() {
        driver.navigate().refresh();
    }
}
