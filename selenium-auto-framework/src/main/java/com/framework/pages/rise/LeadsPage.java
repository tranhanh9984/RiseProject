package com.framework.pages.rise;

import com.framework.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Page Object for RISE CRM Leads page.
 * Covers TC-LEAD-001 through TC-LEAD-023.
 */
public class LeadsPage extends BasePage {

    // ==================== Tabs ====================
    private final By leadsTab = By.xpath("//a[contains(text(),'Leads') and (contains(@href,'leads') or contains(@data-bs-toggle,'tab'))]");
    private final By kanbanTab = By.xpath("//a[contains(text(),'Kanban')]");

    // ==================== Action Buttons ====================
    private final By addLeadButton = By.xpath("//a[contains(text(),'Add lead')] | //button[contains(text(),'Add lead')]");
    private final By manageLabelsButton = By.xpath("//a[contains(text(),'Manage labels')] | //button[contains(text(),'Manage labels')]");
    private final By importLeadsButton = By.xpath("//a[contains(text(),'Import leads')] | //button[contains(text(),'Import leads')]");

    // ==================== Lead Table ====================
    private final By leadTable = By.cssSelector("#lead-table, table.dataTable");
    private final By leadTableHeaders = By.cssSelector("#lead-table thead th, table.dataTable thead th");
    private final By leadTableRows = By.cssSelector("#lead-table tbody tr, table.dataTable tbody tr");
    private final By searchInput = By.cssSelector("input[type='search'], .dataTables_filter input");

    // ==================== Kanban Board ====================
    private final By kanbanBoard = By.cssSelector(".kanban-board, .board-group");
    private final By kanbanColumns = By.cssSelector(".kanban-col, .board-column");

    // ==================== Export Buttons ====================
    private final By excelButton = By.xpath("//a[contains(text(),'Excel')] | //button[contains(text(),'Excel')]");
    private final By printButton = By.xpath("//a[contains(text(),'Print')] | //button[contains(text(),'Print')]");

    // ==================== Add Lead Modal ====================
    private final By modal = By.cssSelector(".modal.show, .modal.in");
    private final By modalTitle = By.cssSelector(".modal.show .modal-title, .modal.in .modal-title");
    private final By modalCloseX = By.cssSelector(".modal.show .close, .modal.show .btn-close, .modal.in .close");
    private final By modalCloseButton = By.xpath("//div[contains(@class,'modal')]//button[contains(text(),'Close')]");
    private final By modalSaveButton = By.xpath("//div[contains(@class,'modal')]//button[contains(text(),'Save') and not(contains(text(),'continue'))]");

    // ==================== Form Fields ====================
    private final By typeOrganizationRadio = By.xpath("//input[@type='radio' and (@value='organization' or @id='organization_type')]");
    private final By typePersonRadio = By.xpath("//input[@type='radio' and (@value='person' or @id='person_type')]");
    private final By companyNameInput = By.cssSelector("input[name='company_name'], input#company_name");
    private final By companyNameLabel = By.xpath("//label[contains(@for,'company_name')]");
    private final By statusSelect = By.cssSelector("select[name='lead_status_id'], #lead_status_id");
    private final By ownerSelect = By.cssSelector("select[name='owner_id'], #owner_id");
    private final By sourceSelect = By.cssSelector("select[name='lead_source_id'], #lead_source_id");
    private final By addressTextarea = By.cssSelector("textarea[name='address'], #address");
    private final By cityInput = By.cssSelector("input[name='city'], #city");
    private final By stateInput = By.cssSelector("input[name='state'], #state");
    private final By zipInput = By.cssSelector("input[name='zip'], #zip");
    private final By countryInput = By.cssSelector("input[name='country'], #country");
    private final By phoneInput = By.cssSelector("input[name='phone'], #phone");
    private final By websiteInput = By.cssSelector("input[name='website'], #website");
    private final By vatNumberInput = By.cssSelector("input[name='vat_number'], #vat_number");
    private final By gstNumberInput = By.cssSelector("input[name='gst_number'], #gst_number");
    private final By currencySelect = By.cssSelector("select[name='currency'], #currency");
    private final By currencySymbolInput = By.cssSelector("input[name='currency_symbol'], #currency_symbol");
    private final By labelsSelect = By.cssSelector("select[name='labels[]'], #labels");

    // ==================== Validation Errors ====================
    private final By companyNameError = By.xpath("//input[@name='company_name' or @id='company_name']/following-sibling::*[contains(@class,'error')] | //input[@name='company_name' or @id='company_name']/parent::*//*[contains(@class,'error')]");

    // ==================== Toast/Notification ====================
    private final By successToast = By.cssSelector(".toast-success, .alert-success");

    public LeadsPage() {
        super();
    }

    public void open(String baseUrl) {
        navigateTo(baseUrl + "/leads");
    }

    // ==================== TC-LEAD-001: Page Display ====================

    public boolean isPageLoaded() {
        return getCurrentUrl().contains("/leads");
    }

    // ==================== TC-LEAD-002: Action Buttons ====================

    public boolean isAddLeadButtonVisible() {
        return isDisplayed(addLeadButton);
    }

    public boolean isManageLabelsButtonVisible() {
        return isDisplayed(manageLabelsButton);
    }

    public boolean isImportLeadsButtonVisible() {
        return isDisplayed(importLeadsButton);
    }

    // ==================== TC-LEAD-003: View Tabs ====================

    public boolean isLeadsTabActive() {
        try {
            WebElement tab = waitForVisible(leadsTab);
            String cls = tab.getAttribute("class");
            return cls != null && (cls.contains("active") || cls.contains("selected"));
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isKanbanTabVisible() {
        return isDisplayed(kanbanTab);
    }

    // ==================== TC-LEAD-004: Table Headers ====================

    public List<WebElement> getLeadTableHeaders() {
        return waitForAllVisible(leadTableHeaders);
    }

    // ==================== TC-LEAD-005: Data Rows ====================

    public int getLeadRowCount() {
        try {
            List<WebElement> rows = waitForAllVisible(leadTableRows);
            return rows.size();
        } catch (Exception e) {
            return 0;
        }
    }

    // ==================== TC-LEAD-006 ~ 007: View Switching ====================

    public void clickKanbanTab() {
        click(kanbanTab);
    }

    public void clickLeadsTab() {
        click(leadsTab);
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

    public boolean isLeadTableVisible() {
        return isDisplayed(leadTable);
    }

    // ==================== TC-LEAD-008: Search ====================

    public void searchLead(String keyword) {
        type(searchInput, keyword);
    }

    public void clearSearch() {
        WebElement input = waitForVisible(searchInput);
        input.clear();
    }

    // ==================== TC-LEAD-009: Export ====================

    public boolean isExcelButtonVisible() {
        return isDisplayed(excelButton);
    }

    public boolean isPrintButtonVisible() {
        return isDisplayed(printButton);
    }

    // ==================== TC-LEAD-010 ~ 012: Modal ====================

    public void clickAddLead() {
        click(addLeadButton);
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

    // ==================== TC-LEAD-013 ~ 014: Form Type Radio ====================

    public boolean isOrganizationRadioSelected() {
        try {
            return waitForVisible(typeOrganizationRadio).isSelected();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isPersonRadioSelected() {
        try {
            return waitForVisible(typePersonRadio).isSelected();
        } catch (Exception e) {
            return false;
        }
    }

    public void selectPersonType() {
        clickByJS(typePersonRadio);
    }

    public void selectOrganizationType() {
        clickByJS(typeOrganizationRadio);
    }

    public String getCompanyNameLabel() {
        return getText(companyNameLabel);
    }

    // ==================== Form Field Visibility ====================

    public boolean isCompanyNameInputVisible() {
        return isDisplayed(companyNameInput);
    }

    public boolean isStatusSelectVisible() {
        return isDisplayed(statusSelect);
    }

    public boolean isOwnerSelectVisible() {
        return isDisplayed(ownerSelect);
    }

    public boolean isSourceSelectVisible() {
        return isDisplayed(sourceSelect);
    }

    public boolean isAddressTextareaVisible() {
        return isDisplayed(addressTextarea);
    }

    public boolean isCityInputVisible() {
        return isDisplayed(cityInput);
    }

    public boolean isStateInputVisible() {
        return isDisplayed(stateInput);
    }

    public boolean isZipInputVisible() {
        return isDisplayed(zipInput);
    }

    public boolean isCountryInputVisible() {
        return isDisplayed(countryInput);
    }

    public boolean isPhoneInputVisible() {
        return isDisplayed(phoneInput);
    }

    public boolean isWebsiteInputVisible() {
        return isDisplayed(websiteInput);
    }

    public boolean isVatNumberInputVisible() {
        return isDisplayed(vatNumberInput);
    }

    public boolean isGstNumberInputVisible() {
        return isDisplayed(gstNumberInput);
    }

    public boolean isSaveButtonVisible() {
        return isDisplayed(modalSaveButton);
    }

    public boolean isCloseButtonVisible() {
        return isDisplayed(modalCloseButton);
    }

    // ==================== Form Input Methods ====================

    public void enterCompanyName(String name) {
        type(companyNameInput, name);
    }

    public void selectStatus(String status) {
        // Use Select2 approach
        By select2 = By.xpath("//select[@name='lead_status_id']/following-sibling::span//span[contains(@class,'select2-selection')]");
        try {
            click(select2);
            By option = By.xpath("//li[contains(@class,'select2-results__option') and contains(text(),'" + status + "')]");
            click(option);
        } catch (Exception e) {
            selectByVisibleText(statusSelect, status);
        }
    }

    public void enterAddress(String address) {
        type(addressTextarea, address);
    }

    public void enterCity(String city) {
        type(cityInput, city);
    }

    public void enterState(String state) {
        type(stateInput, state);
    }

    public void enterZip(String zip) {
        type(zipInput, zip);
    }

    public void enterCountry(String country) {
        type(countryInput, country);
    }

    public void enterPhone(String phone) {
        type(phoneInput, phone);
    }

    public void enterWebsite(String website) {
        type(websiteInput, website);
    }

    public void enterVatNumber(String vat) {
        type(vatNumberInput, vat);
    }

    public void enterGstNumber(String gst) {
        type(gstNumberInput, gst);
    }

    // ==================== TC-LEAD-015: Validation ====================

    public void clickSave() {
        click(modalSaveButton);
    }

    public boolean isCompanyNameErrorVisible() {
        return isDisplayed(companyNameError);
    }

    public String getCompanyNameErrorText() {
        return getText(companyNameError);
    }

    // ==================== TC-LEAD-016 ~ 019: Create Lead ====================

    public void createOrganizationLead(String companyName) {
        selectOrganizationType();
        enterCompanyName(companyName);
        clickSave();
    }

    public void createPersonLead(String name) {
        selectPersonType();
        enterCompanyName(name);
        clickSave();
    }

    public boolean isSuccessToastVisible() {
        return isDisplayed(successToast);
    }

    public boolean isLeadVisibleInTable(String leadName) {
        try {
            By leadLink = By.xpath("//td//a[contains(text(),'" + leadName + "')] | //td[contains(text(),'" + leadName + "')]");
            return isDisplayed(leadLink);
        } catch (Exception e) {
            return false;
        }
    }

    public String getLeadStatusInTable(String leadName) {
        try {
            By statusCell = By.xpath("//td//a[contains(text(),'" + leadName + "')]/ancestor::tr//span[contains(@class,'badge') or contains(@class,'label')]");
            return getText(statusCell);
        } catch (Exception e) {
            return "";
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
