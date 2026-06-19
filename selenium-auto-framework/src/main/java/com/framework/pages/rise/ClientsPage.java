package com.framework.pages.rise;

import com.framework.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Page Object for RISE CRM Clients page.
 * Covers TC-CLI-001 through TC-CLI-026.
 */
public class ClientsPage extends BasePage {

    // ==================== Page Elements ====================
    private final By overviewTab = By.xpath("//a[contains(@href,'clients') and contains(text(),'Overview')]");
    private final By clientsTab = By.xpath("//a[contains(text(),'Clients') and (contains(@href,'tab') or contains(@data-bs-toggle,'tab') or contains(@data-toggle,'tab'))]");
    private final By contactsTab = By.xpath("//a[contains(text(),'Contacts')]");
    private final By allTabs = By.cssSelector("ul.nav-tabs li a, ul.nav li a.nav-link");

    // ==================== Action Buttons ====================
    private final By addClientButton = By.xpath("//a[contains(text(),'Add client')] | //button[contains(text(),'Add client')]");
    private final By manageLabelsButton = By.xpath("//a[contains(text(),'Manage labels')] | //button[contains(text(),'Manage labels')]");
    private final By importClientsButton = By.xpath("//a[contains(text(),'Import clients')] | //button[contains(text(),'Import clients')]");

    // ==================== Overview Cards ====================
    private final By overviewCards = By.cssSelector(".widget-container .card, .overview-card, .info-card");
    private final By overviewCardValues = By.cssSelector(".widget-container .card .card-body span, .overview-card .value");

    // ==================== Client Table ====================
    private final By clientTable = By.cssSelector("#client-table, table.dataTable");
    private final By clientTableHeaders = By.cssSelector("#client-table thead th, table.dataTable thead th");
    private final By clientTableRows = By.cssSelector("#client-table tbody tr, table.dataTable tbody tr");
    private final By searchInput = By.cssSelector("input[type='search'], .dataTables_filter input");

    // ==================== Contacts Table ====================
    private final By contactsTable = By.cssSelector("#contact-table, .contacts-table");
    private final By contactsTableHeaders = By.cssSelector("#contact-table thead th, .contacts-table thead th");

    // ==================== Export Buttons ====================
    private final By excelButton = By.xpath("//a[contains(text(),'Excel')] | //button[contains(text(),'Excel')]");
    private final By printButton = By.xpath("//a[contains(text(),'Print')] | //button[contains(text(),'Print')]");

    // ==================== Add Client Modal ====================
    private final By modal = By.cssSelector(".modal.show, .modal.in");
    private final By modalTitle = By.cssSelector(".modal.show .modal-title, .modal.in .modal-title");
    private final By modalCloseX = By.cssSelector(".modal.show .close, .modal.show .btn-close, .modal.in .close");
    private final By modalCloseButton = By.xpath("//div[contains(@class,'modal')]//button[contains(text(),'Close')]");
    private final By modalSaveButton = By.xpath("//div[contains(@class,'modal')]//button[contains(text(),'Save') and not(contains(text(),'continue'))]");
    private final By modalSaveContinueButton = By.xpath("//div[contains(@class,'modal')]//button[contains(text(),'Save & continue')]");

    // ==================== Form Fields ====================
    private final By typeOrganizationRadio = By.xpath("//input[@type='radio' and (@value='organization' or @id='organization_type')]");
    private final By typePersonRadio = By.xpath("//input[@type='radio' and (@value='person' or @id='person_type')]");
    private final By companyNameInput = By.cssSelector("input[name='company_name'], input#company_name");
    private final By companyNameLabel = By.xpath("//label[contains(@for,'company_name')]");
    private final By ownerSelect = By.cssSelector("select[name='owner_id'], #owner_id");
    private final By addressTextarea = By.cssSelector("textarea[name='address'], #address");
    private final By cityInput = By.cssSelector("input[name='city'], #city");
    private final By stateInput = By.cssSelector("input[name='state'], #state");
    private final By zipInput = By.cssSelector("input[name='zip'], #zip");
    private final By countryInput = By.cssSelector("input[name='country'], #country");
    private final By phoneInput = By.cssSelector("input[name='phone'], #phone");
    private final By websiteInput = By.cssSelector("input[name='website'], #website");
    private final By vatNumberInput = By.cssSelector("input[name='vat_number'], #vat_number");
    private final By gstNumberInput = By.cssSelector("input[name='gst_number'], #gst_number");
    private final By clientGroupsSelect = By.cssSelector("select[name='group_ids[]'], #group_ids");
    private final By currencySelect = By.cssSelector("select[name='currency'], #currency");
    private final By currencySymbolInput = By.cssSelector("input[name='currency_symbol'], #currency_symbol");
    private final By labelsSelect = By.cssSelector("select[name='labels[]'], #labels");
    private final By disableOnlinePaymentCheckbox = By.cssSelector("input[name='disable_online_payment'], #disable_online_payment");

    // ==================== Validation Errors ====================
    private final By companyNameError = By.xpath("//input[@name='company_name' or @id='company_name']/following-sibling::*[contains(@class,'error')] | //input[@name='company_name' or @id='company_name']/parent::*//*[contains(@class,'error')]");
    private final By formErrors = By.cssSelector(".help-block.text-danger, .invalid-feedback, .field-error");

    // ==================== Toast/Notification ====================
    private final By successToast = By.cssSelector(".toast-success, .alert-success, #ajaxModal .toast-success");

    public ClientsPage() {
        super();
    }

    public void open(String baseUrl) {
        navigateTo(baseUrl + "/clients");
    }

    // ==================== TC-CLI-001: Page Display ====================

    public boolean isPageLoaded() {
        return getCurrentUrl().contains("/clients");
    }

    public boolean isOverviewTabActive() {
        try {
            WebElement tab = waitForVisible(overviewTab);
            String cls = tab.getAttribute("class");
            return cls != null && (cls.contains("active") || cls.contains("selected"));
        } catch (Exception e) {
            return false;
        }
    }

    public int getTabCount() {
        try {
            return waitForAllVisible(allTabs).size();
        } catch (Exception e) {
            return 0;
        }
    }

    // ==================== TC-CLI-002: Action Buttons ====================

    public boolean isAddClientButtonVisible() {
        return isDisplayed(addClientButton);
    }

    public boolean isManageLabelsButtonVisible() {
        return isDisplayed(manageLabelsButton);
    }

    public boolean isImportClientsButtonVisible() {
        return isDisplayed(importClientsButton);
    }

    // ==================== TC-CLI-003: Overview Cards ====================

    public int getOverviewCardCount() {
        try {
            return waitForAllVisible(overviewCards).size();
        } catch (Exception e) {
            return 0;
        }
    }

    // ==================== TC-CLI-004: Tab Navigation ====================

    public void clickClientsTab() {
        click(clientsTab);
    }

    public void clickContactsTab() {
        click(contactsTab);
    }

    public void clickOverviewTab() {
        click(overviewTab);
    }

    public boolean isClientTableVisible() {
        return isDisplayed(clientTable);
    }

    public List<WebElement> getClientTableHeaders() {
        return waitForAllVisible(clientTableHeaders);
    }

    // ==================== TC-CLI-005: Contacts Tab ====================

    public boolean isContactsTableVisible() {
        return isDisplayed(contactsTable);
    }

    public List<WebElement> getContactsTableHeaders() {
        return waitForAllVisible(contactsTableHeaders);
    }

    // ==================== TC-CLI-007: Export Buttons ====================

    public boolean isExcelButtonVisible() {
        return isDisplayed(excelButton);
    }

    public boolean isPrintButtonVisible() {
        return isDisplayed(printButton);
    }

    // ==================== TC-CLI-008 ~ 011: Add Client Modal ====================

    public void clickAddClient() {
        click(addClientButton);
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

    public void closeModalByButton() {
        click(modalCloseButton);
    }

    public boolean isModalHidden() {
        return waitForInvisible(modal);
    }

    // ==================== TC-CLI-012 ~ 013: Form Type Radio ====================

    public boolean isOrganizationRadioSelected() {
        try {
            WebElement radio = waitForVisible(typeOrganizationRadio);
            return radio.isSelected();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isPersonRadioSelected() {
        try {
            WebElement radio = waitForVisible(typePersonRadio);
            return radio.isSelected();
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

    // ==================== Form Input Methods ====================

    public void enterCompanyName(String name) {
        type(companyNameInput, name);
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

    // ==================== Form Visibility Checks ====================

    public boolean isCompanyNameInputVisible() {
        return isDisplayed(companyNameInput);
    }

    public boolean isOwnerSelectVisible() {
        return isDisplayed(ownerSelect);
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

    public boolean isSaveContinueButtonVisible() {
        return isDisplayed(modalSaveContinueButton);
    }

    public boolean isCloseButtonVisible() {
        return isDisplayed(modalCloseButton);
    }

    // ==================== TC-CLI-014: Validation ====================

    public void clickSave() {
        click(modalSaveButton);
    }

    public boolean isCompanyNameErrorVisible() {
        return isDisplayed(companyNameError);
    }

    public String getCompanyNameErrorText() {
        return getText(companyNameError);
    }

    // ==================== TC-CLI-015 ~ 018: Create Client ====================

    public void createOrganizationClient(String companyName) {
        selectOrganizationType();
        enterCompanyName(companyName);
        clickSave();
    }

    public void createPersonClient(String name) {
        selectPersonType();
        enterCompanyName(name);
        clickSave();
    }

    public boolean isSuccessToastVisible() {
        return isDisplayed(successToast);
    }

    // ==================== TC-CLI-019: Search ====================

    public void searchClient(String keyword) {
        type(searchInput, keyword);
    }

    public void clearSearch() {
        WebElement input = waitForVisible(searchInput);
        input.clear();
    }

    public int getClientRowCount() {
        try {
            List<WebElement> rows = waitForAllVisible(clientTableRows);
            return rows.size();
        } catch (Exception e) {
            return 0;
        }
    }

    // ==================== TC-CLI-026: Data Validation ====================

    public void clickClientRow(String clientName) {
        By clientLink = By.xpath("//td//a[contains(text(),'" + clientName + "')]");
        click(clientLink);
    }

    public boolean isClientVisibleInTable(String clientName) {
        try {
            By clientLink = By.xpath("//td//a[contains(text(),'" + clientName + "')] | //td[contains(text(),'" + clientName + "')]");
            return isDisplayed(clientLink);
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
