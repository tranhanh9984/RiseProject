package com.framework.pages.rise;

import com.framework.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Page Object for RISE CRM Projects page.
 * Covers TC-PRJ-001 through TC-PRJ-019.
 */
public class ProjectsPage extends BasePage {

    // ==================== Action Buttons ====================
    private final By addProjectButton = By.xpath("//a[contains(text(),'Add project')] | //button[contains(text(),'Add project')]");
    private final By manageLabelsButton = By.xpath("//a[contains(text(),'Manage labels')] | //button[contains(text(),'Manage labels')]");
    private final By importProjectsButton = By.xpath("//a[contains(text(),'Import projects')] | //button[contains(text(),'Import projects')]");

    // ==================== Project Table ====================
    private final By projectTable = By.cssSelector("#project-table, table.dataTable");
    private final By projectTableHeaders = By.cssSelector("#project-table thead th, table.dataTable thead th");
    private final By projectTableRows = By.cssSelector("#project-table tbody tr, table.dataTable tbody tr");
    private final By searchInput = By.cssSelector("input[type='search'], .dataTables_filter input");

    // ==================== Filters ====================
    private final By statusFilters = By.cssSelector(".filter-section .status-filter, .project-status-filter a, [data-filter='status']");

    // ==================== Export Buttons ====================
    private final By excelButton = By.xpath("//a[contains(text(),'Excel')] | //button[contains(text(),'Excel')]");
    private final By printButton = By.xpath("//a[contains(text(),'Print')] | //button[contains(text(),'Print')]");

    // ==================== Add Project Modal ====================
    private final By modal = By.cssSelector(".modal.show, .modal.in");
    private final By modalTitle = By.cssSelector(".modal.show .modal-title, .modal.in .modal-title");
    private final By modalCloseX = By.cssSelector(".modal.show .close, .modal.show .btn-close, .modal.in .close");
    private final By modalCloseButton = By.xpath("//div[contains(@class,'modal')]//button[contains(text(),'Close')]");
    private final By modalSaveButton = By.xpath("//div[contains(@class,'modal')]//button[contains(text(),'Save') and not(contains(text(),'continue'))]");
    private final By modalSaveContinueButton = By.xpath("//div[contains(@class,'modal')]//button[contains(text(),'Save & continue')]");

    // ==================== Form Fields ====================
    private final By titleInput = By.cssSelector("input[name='title'], input#title");
    private final By projectTypeSelect = By.cssSelector("select[name='project_type'], #project_type");
    private final By clientSelect = By.cssSelector("select[name='client_id'], #client_id");
    private final By clientField = By.xpath("//*[contains(@id,'client_id') or contains(@name,'client_id')]/ancestor::div[contains(@class,'form-group') or contains(@class,'mb-')]");
    private final By descriptionTextarea = By.cssSelector("textarea[name='description'], #description");
    private final By startDateInput = By.cssSelector("input[name='start_date'], #start_date");
    private final By deadlineInput = By.cssSelector("input[name='deadline'], #deadline");
    private final By priceInput = By.cssSelector("input[name='price'], #price");
    private final By labelsSelect = By.cssSelector("select[name='labels[]'], #labels");

    // ==================== Validation Errors ====================
    private final By titleError = By.xpath("//input[@name='title' or @id='title']/following-sibling::*[contains(@class,'error')] | //input[@name='title' or @id='title']/parent::*//*[contains(@class,'error')]");
    private final By clientError = By.xpath("//*[contains(@id,'client_id') or contains(@name,'client_id')]/ancestor::div[contains(@class,'form-group')]//*[contains(@class,'error')]");

    // ==================== Toast/Notification ====================
    private final By successToast = By.cssSelector(".toast-success, .alert-success");

    public ProjectsPage() {
        super();
    }

    public void open(String baseUrl) {
        navigateTo(baseUrl + "/projects/all_projects");
    }

    // ==================== TC-PRJ-001: Page Display ====================

    public boolean isPageLoaded() {
        return getCurrentUrl().contains("/projects");
    }

    // ==================== TC-PRJ-002: Action Buttons ====================

    public boolean isAddProjectButtonVisible() {
        return isDisplayed(addProjectButton);
    }

    public boolean isManageLabelsButtonVisible() {
        return isDisplayed(manageLabelsButton);
    }

    public boolean isImportProjectsButtonVisible() {
        return isDisplayed(importProjectsButton);
    }

    // ==================== TC-PRJ-003: Table Headers ====================

    public List<WebElement> getProjectTableHeaders() {
        return waitForAllVisible(projectTableHeaders);
    }

    // ==================== TC-PRJ-004: Data Rows ====================

    public int getProjectRowCount() {
        try {
            List<WebElement> rows = waitForAllVisible(projectTableRows);
            return rows.size();
        } catch (Exception e) {
            return 0;
        }
    }

    public boolean isProjectTableVisible() {
        return isDisplayed(projectTable);
    }

    // ==================== TC-PRJ-005: Filters ====================

    public boolean areStatusFiltersVisible() {
        return isDisplayed(statusFilters);
    }

    // ==================== TC-PRJ-006: Export ====================

    public boolean isExcelButtonVisible() {
        return isDisplayed(excelButton);
    }

    public boolean isPrintButtonVisible() {
        return isDisplayed(printButton);
    }

    // ==================== TC-PRJ-007: Search ====================

    public void searchProject(String keyword) {
        type(searchInput, keyword);
    }

    public void clearSearch() {
        WebElement input = waitForVisible(searchInput);
        input.clear();
    }

    // ==================== TC-PRJ-008 ~ 010: Modal ====================

    public void clickAddProject() {
        click(addProjectButton);
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

    // ==================== TC-PRJ-009: Form Field Visibility ====================

    public boolean isTitleInputVisible() {
        return isDisplayed(titleInput);
    }

    public boolean isProjectTypeSelectVisible() {
        return isDisplayed(projectTypeSelect);
    }

    public boolean isClientFieldVisible() {
        return isDisplayed(clientField);
    }

    public boolean isDescriptionTextareaVisible() {
        return isDisplayed(descriptionTextarea);
    }

    public boolean isStartDateInputVisible() {
        return isDisplayed(startDateInput);
    }

    public boolean isDeadlineInputVisible() {
        return isDisplayed(deadlineInput);
    }

    public boolean isPriceInputVisible() {
        return isDisplayed(priceInput);
    }

    public boolean isSaveButtonVisible() {
        return isDisplayed(modalSaveButton);
    }

    public boolean isSaveContinueButtonVisible() {
        return isDisplayed(modalSaveContinueButton);
    }

    public boolean isCloseButtonVisible() {
        return isDisplayed(modalCloseButton);
    }

    // ==================== TC-PRJ-011: Project Type Toggle ====================

    public void selectProjectType(String type) {
        By select2 = By.xpath("//select[contains(@name,'project_type') or @id='project_type']/following-sibling::span//span[contains(@class,'select2-selection')]");
        try {
            click(select2);
            By option = By.xpath("//li[contains(@class,'select2-results__option') and contains(text(),'" + type + "')]");
            click(option);
        } catch (Exception e) {
            selectByVisibleText(projectTypeSelect, type);
        }
    }

    public void selectClient(String clientName) {
        By select2 = By.xpath("//select[contains(@name,'client_id') or @id='client_id']/following-sibling::span//span[contains(@class,'select2-selection')]");
        try {
            click(select2);
            By searchBox = By.cssSelector(".select2-search__field");
            type(searchBox, clientName);
            By option = By.xpath("//li[contains(@class,'select2-results__option') and contains(text(),'" + clientName + "')]");
            click(option);
        } catch (Exception e) {
            selectByVisibleText(clientSelect, clientName);
        }
    }

    // ==================== Form Input Methods ====================

    public void enterTitle(String title) {
        type(titleInput, title);
    }

    public void enterDescription(String description) {
        type(descriptionTextarea, description);
    }

    public void enterStartDate(String date) {
        type(startDateInput, date);
    }

    public void enterDeadline(String date) {
        type(deadlineInput, date);
    }

    public void enterPrice(String price) {
        type(priceInput, price);
    }

    // ==================== TC-PRJ-012: Validation ====================

    public void clickSave() {
        click(modalSaveButton);
    }

    public boolean isTitleErrorVisible() {
        return isDisplayed(titleError);
    }

    public String getTitleErrorText() {
        return getText(titleError);
    }

    public boolean isClientErrorVisible() {
        return isDisplayed(clientError);
    }

    // ==================== Create Project ====================

    public boolean isSuccessToastVisible() {
        return isDisplayed(successToast);
    }

    public boolean isProjectVisibleInTable(String projectTitle) {
        try {
            By projectLink = By.xpath("//td//a[contains(text(),'" + projectTitle + "')] | //td[contains(text(),'" + projectTitle + "')]");
            return isDisplayed(projectLink);
        } catch (Exception e) {
            return false;
        }
    }

    // ==================== Utility Methods ====================

    public void goBack() {
        driver.navigate().back();
    }

    public void refresh() {
        driver.navigate().refresh();
    }
}
