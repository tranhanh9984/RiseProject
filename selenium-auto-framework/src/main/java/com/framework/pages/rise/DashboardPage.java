package com.framework.pages.rise;

import com.framework.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Page Object for RISE CRM Dashboard page.
 * Covers TC-DASH-001 through TC-DASH-029.
 */
public class DashboardPage extends BasePage {

    // ==================== Page Heading ====================
    private final By pageHeading = By.cssSelector(".page-title h1, .page-title span");

    // ==================== Summary Statistics Widgets ====================
    private final By myOpenTasksWidget = By.xpath("//span[contains(text(),'My open tasks')]/ancestor::a | //span[contains(text(),'My open tasks')]/ancestor::div[contains(@class,'widget')]");
    private final By myOpenTasksCount = By.xpath("//span[contains(text(),'My open tasks')]/preceding-sibling::*[contains(@class,'count')] | //span[contains(text(),'My open tasks')]/..//span[contains(@class,'widget-icon-value')]");
    private final By eventsTodayWidget = By.xpath("//span[contains(text(),'Events today')]/ancestor::a | //span[contains(text(),'Events today')]/ancestor::div[contains(@class,'widget')]");
    private final By eventsTodayCount = By.xpath("//span[contains(text(),'Events today')]/..//span[contains(@class,'widget-icon-value')]");
    private final By dueWidget = By.xpath("//span[contains(text(),'Due')]/ancestor::a | //span[contains(text(),'Due')]/ancestor::div[contains(@class,'widget')]");
    private final By dueAmount = By.xpath("//span[contains(text(),'Due')]/..//span[contains(@class,'widget-icon-value')]");

    // ==================== Projects Overview Widget ====================
    private final By projectsOverviewSection = By.xpath("//*[contains(text(),'Projects Overview')]");
    private final By projectsOpenCount = By.xpath("//*[contains(text(),'Projects Overview')]/ancestor::div[contains(@class,'card')]//*[contains(text(),'Open')]/preceding-sibling::* | //*[contains(text(),'Projects Overview')]/ancestor::div[contains(@class,'card')]//a[contains(@href,'all_projects/1')]");
    private final By projectsCompletedCount = By.xpath("//*[contains(text(),'Projects Overview')]/ancestor::div[contains(@class,'card')]//a[contains(@href,'all_projects/2')]");
    private final By projectsHoldCount = By.xpath("//*[contains(text(),'Projects Overview')]/ancestor::div[contains(@class,'card')]//a[contains(@href,'all_projects/3')]");
    private final By projectsProgression = By.xpath("//*[contains(text(),'Progression')]/..//*[contains(text(),'%')]");

    // ==================== Invoice Overview Widget ====================
    private final By invoiceOverviewSection = By.xpath("//*[contains(text(),'Invoice Overview')]");
    private final By invoiceOverdueLink = By.xpath("//a[contains(@href,'invoices/index/custom/overdue')]");
    private final By invoiceNotPaidLink = By.xpath("//a[contains(@href,'invoices/index/custom/not_paid')]");
    private final By invoiceOverviewChart = By.cssSelector("#invoice-overview-chart");

    // ==================== Income vs Expenses Widget ====================
    private final By incomeExpensesSection = By.xpath("//*[contains(text(),'Income vs Expenses')]");
    private final By incomeExpenseChart = By.cssSelector("#income-expense-chart");
    private final By dashboardIncomeVsExpensesChart = By.cssSelector("#dashboard-income-vs-expenses-chart");

    // ==================== All Tasks Overview Widget ====================
    private final By allTasksOverviewSection = By.xpath("//*[contains(text(),'All Tasks Overview')]");
    private final By allTasksOverviewChart = By.cssSelector("#all-tasks-overview-chart-all_tasks_overview");

    // ==================== Team Members Overview Widget ====================
    private final By teamMembersSection = By.xpath("//*[contains(text(),'Team Members') or contains(text(),'Team members')]");
    private final By teamMembersLink = By.xpath("//a[contains(@href,'team_members/index')]");
    private final By clockedInLink = By.xpath("//a[contains(@href,'attendance/index/members_clocked_in')]");

    // ==================== Ticket Status Widget ====================
    private final By ticketStatusSection = By.xpath("//*[contains(text(),'Ticket Status')]");
    private final By ticketNewLink = By.xpath("//a[contains(@href,'tickets/index/new')]");
    private final By ticketOpenLink = By.xpath("//a[contains(@href,'tickets/index/open')]");
    private final By ticketStatusChart = By.cssSelector("#ticket-status-chart");

    // ==================== Charts ====================
    private final By allChartCanvases = By.cssSelector("canvas");

    // ==================== Clock In/Out Widget ====================
    private final By clockInButton = By.xpath("//*[contains(text(),'Clock In') and (self::button or self::a)]");
    private final By clockOutButton = By.xpath("//*[contains(text(),'Clock Out') and (self::button or self::a)]");
    private final By clockStatus = By.xpath("//*[contains(text(),'Clock started at')]");

    // ==================== Project Timeline Widget ====================
    private final By projectTimelineSection = By.xpath("//*[contains(text(),'Project Timeline')]");
    private final By timelineEntries = By.cssSelector(".timeline .timeline-item, .activity-feed .feed-item");
    private final By loadMoreButton = By.xpath("//a[contains(text(),'Load more')] | //button[contains(text(),'Load more')]");

    // ==================== Events Widget ====================
    private final By eventsSection = By.xpath("//*[contains(text(),'Events')]");
    private final By viewOnCalendarLink = By.xpath("//a[contains(text(),'View on calendar') or contains(@href,'/events')]");

    // ==================== My Tasks Table ====================
    private final By myTasksTable = By.cssSelector("#task-table");
    private final By myTasksTableHeaders = By.cssSelector("#task-table thead th");
    private final By myTasksTableRows = By.cssSelector("#task-table tbody tr");

    // ==================== To Do Widget ====================
    private final By todoSection = By.xpath("//*[contains(text(),'To do')]");
    private final By todoItems = By.cssSelector(".todo-list li, .to-do-list .list-group-item");

    // ==================== Sticky Note Widget ====================
    private final By stickyNoteSection = By.xpath("//*[contains(text(),'Sticky Note')]");
    private final By stickyNoteTextarea = By.cssSelector(".sticky-note textarea, #sticky-note");

    // ==================== Quick Actions ====================
    private final By quickActionsDropdown = By.xpath("//*[contains(@class,'quick-actions')] | //button[contains(text(),'Quick actions')] | //a[contains(@class,'dropdown-toggle') and .//text()[contains(.,'Quick')]]");
    private final By quickActionAddTask = By.xpath("//a[contains(text(),'Add task')]");
    private final By quickActionAddEvent = By.xpath("//a[contains(text(),'Add event')]");

    // ==================== Modal ====================
    private final By modalTitle = By.cssSelector(".modal.show .modal-title, .modal.in .modal-title");
    private final By modalBody = By.cssSelector(".modal.show .modal-body, .modal.in .modal-body");

    // ==================== Open Projects List ====================
    private final By openProjectsSection = By.xpath("//*[contains(text(),'Open Projects')]");
    private final By openProjectLinks = By.xpath("//*[contains(text(),'Open Projects')]/ancestor::div[contains(@class,'card')]//a[contains(@href,'projects/view')]");

    // ==================== Dashboard Management ====================
    private final By dashboardOptionsDropdown = By.cssSelector(".dashboard-options .dropdown-toggle, [data-bs-toggle='dropdown']");
    private final By addNewDashboardOption = By.xpath("//a[contains(text(),'Add new dashboard')]");

    public DashboardPage() {
        super();
    }

    public void open(String baseUrl) {
        navigateTo(baseUrl + "/dashboard");
    }

    // ==================== TC-DASH-001: Page Display ====================

    public boolean isPageLoaded() {
        return getCurrentUrl().contains("/dashboard");
    }

    public String getHeading() {
        return getText(pageHeading);
    }

    // ==================== TC-DASH-002 ~ 004: Summary Statistics ====================

    public boolean isMyOpenTasksWidgetVisible() {
        return isDisplayed(myOpenTasksWidget);
    }

    public String getMyOpenTasksCount() {
        return getText(myOpenTasksCount).trim();
    }

    public void clickMyOpenTasksWidget() {
        click(myOpenTasksWidget);
    }

    public boolean isEventsTodayWidgetVisible() {
        return isDisplayed(eventsTodayWidget);
    }

    public String getEventsTodayCount() {
        return getText(eventsTodayCount).trim();
    }

    public void clickEventsTodayWidget() {
        click(eventsTodayWidget);
    }

    public boolean isDueWidgetVisible() {
        return isDisplayed(dueWidget);
    }

    public String getDueAmount() {
        return getText(dueAmount).trim();
    }

    public void clickDueWidget() {
        click(dueWidget);
    }

    // ==================== TC-DASH-005 ~ 006: Projects Overview ====================

    public boolean isProjectsOverviewVisible() {
        return isDisplayed(projectsOverviewSection);
    }

    public boolean isProjectsOpenCountVisible() {
        return isDisplayed(projectsOpenCount);
    }

    public boolean isProjectsCompletedCountVisible() {
        return isDisplayed(projectsCompletedCount);
    }

    public boolean isProjectsHoldCountVisible() {
        return isDisplayed(projectsHoldCount);
    }

    public void clickProjectsOpenLink() {
        click(projectsOpenCount);
    }

    public void clickProjectsCompletedLink() {
        click(projectsCompletedCount);
    }

    public void clickProjectsHoldLink() {
        click(projectsHoldCount);
    }

    // ==================== TC-DASH-007 ~ 008: Invoice Overview ====================

    public boolean isInvoiceOverviewVisible() {
        return isDisplayed(invoiceOverviewSection);
    }

    public boolean isInvoiceOverviewChartRendered() {
        try {
            WebElement canvas = waitForVisible(invoiceOverviewChart);
            return canvas.getSize().getWidth() > 0 && canvas.getSize().getHeight() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public void clickInvoiceOverdueLink() {
        click(invoiceOverdueLink);
    }

    public void clickInvoiceNotPaidLink() {
        click(invoiceNotPaidLink);
    }

    // ==================== TC-DASH-009: Income vs Expenses ====================

    public boolean isIncomeExpensesSectionVisible() {
        return isDisplayed(incomeExpensesSection);
    }

    public boolean isIncomeExpenseChartRendered() {
        try {
            WebElement canvas = waitForVisible(incomeExpenseChart);
            return canvas.getSize().getWidth() > 0 && canvas.getSize().getHeight() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isDashboardIncomeVsExpensesChartRendered() {
        try {
            WebElement canvas = waitForVisible(dashboardIncomeVsExpensesChart);
            return canvas.getSize().getWidth() > 0 && canvas.getSize().getHeight() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    // ==================== TC-DASH-010: All Tasks Overview ====================

    public boolean isAllTasksOverviewVisible() {
        return isDisplayed(allTasksOverviewSection);
    }

    public boolean isAllTasksOverviewChartRendered() {
        try {
            WebElement canvas = waitForVisible(allTasksOverviewChart);
            return canvas.getSize().getWidth() > 0 && canvas.getSize().getHeight() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    // ==================== TC-DASH-011 ~ 012: Team Members ====================

    public boolean isTeamMembersSectionVisible() {
        return isDisplayed(teamMembersSection);
    }

    public void clickTeamMembersLink() {
        click(teamMembersLink);
    }

    public void clickClockedInLink() {
        click(clockedInLink);
    }

    // ==================== TC-DASH-013 ~ 014: Ticket Status ====================

    public boolean isTicketStatusSectionVisible() {
        return isDisplayed(ticketStatusSection);
    }

    public boolean isTicketStatusChartRendered() {
        try {
            WebElement canvas = waitForVisible(ticketStatusChart);
            return canvas.getSize().getWidth() > 0 && canvas.getSize().getHeight() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public void clickTicketNewLink() {
        click(ticketNewLink);
    }

    public void clickTicketOpenLink() {
        click(ticketOpenLink);
    }

    // ==================== TC-DASH-015: All Charts ====================

    public int getRenderedChartCount() {
        try {
            List<WebElement> canvases = waitForAllVisible(allChartCanvases);
            return (int) canvases.stream()
                    .filter(c -> c.getSize().getWidth() > 0 && c.getSize().getHeight() > 0)
                    .count();
        } catch (Exception e) {
            return 0;
        }
    }

    // ==================== TC-DASH-016 ~ 017: Clock In/Out ====================

    public boolean isClockInButtonVisible() {
        return isDisplayed(clockInButton);
    }

    public boolean isClockOutButtonVisible() {
        return isDisplayed(clockOutButton);
    }

    public void clickClockOutButton() {
        click(clockOutButton);
    }

    public boolean isClockStatusVisible() {
        return isDisplayed(clockStatus);
    }

    // ==================== TC-DASH-018: Project Timeline ====================

    public boolean isProjectTimelineVisible() {
        return isDisplayed(projectTimelineSection);
    }

    public int getTimelineEntryCount() {
        try {
            List<WebElement> entries = waitForAllVisible(timelineEntries);
            return entries.size();
        } catch (Exception e) {
            return 0;
        }
    }

    public void clickLoadMore() {
        scrollToElement(loadMoreButton);
        click(loadMoreButton);
    }

    // ==================== TC-DASH-019: Events ====================

    public boolean isEventsSectionVisible() {
        return isDisplayed(eventsSection);
    }

    public void clickViewOnCalendar() {
        click(viewOnCalendarLink);
    }

    // ==================== TC-DASH-020 ~ 021: My Tasks Table ====================

    public boolean isMyTasksTableVisible() {
        return isDisplayed(myTasksTable);
    }

    public List<WebElement> getMyTasksTableHeaders() {
        return waitForAllVisible(myTasksTableHeaders);
    }

    public int getMyTasksRowCount() {
        try {
            List<WebElement> rows = waitForAllVisible(myTasksTableRows);
            return rows.size();
        } catch (Exception e) {
            return 0;
        }
    }

    // ==================== TC-DASH-022 ~ 023: To Do ====================

    public boolean isTodoSectionVisible() {
        return isDisplayed(todoSection);
    }

    public int getTodoItemCount() {
        try {
            List<WebElement> items = waitForAllVisible(todoItems);
            return items.size();
        } catch (Exception e) {
            return 0;
        }
    }

    // ==================== TC-DASH-024: Sticky Note ====================

    public boolean isStickyNoteSectionVisible() {
        return isDisplayed(stickyNoteSection);
    }

    public void typeStickyNote(String text) {
        WebElement note = waitForClickable(stickyNoteTextarea);
        note.click();
        note.clear();
        note.sendKeys(text);
    }

    public String getStickyNoteText() {
        return getAttribute(stickyNoteTextarea, "value");
    }

    // ==================== TC-DASH-025 ~ 027: Quick Actions ====================

    public void openQuickActions() {
        click(quickActionsDropdown);
    }

    public void clickQuickActionAddTask() {
        click(quickActionAddTask);
    }

    public void clickQuickActionAddEvent() {
        click(quickActionAddEvent);
    }

    public String getModalTitle() {
        return getText(modalTitle);
    }

    public boolean isModalBodyVisible() {
        return isDisplayed(modalBody);
    }

    // ==================== TC-DASH-028: Open Projects List ====================

    public boolean isOpenProjectsSectionVisible() {
        return isDisplayed(openProjectsSection);
    }

    public int getOpenProjectLinksCount() {
        try {
            List<WebElement> links = waitForAllVisible(openProjectLinks);
            return links.size();
        } catch (Exception e) {
            return 0;
        }
    }

    public void clickFirstOpenProject() {
        List<WebElement> links = waitForAllVisible(openProjectLinks);
        if (!links.isEmpty()) {
            links.get(0).click();
        }
    }

    // ==================== TC-DASH-029: Dashboard Management ====================

    public void openDashboardOptions() {
        click(dashboardOptionsDropdown);
    }

    public void clickAddNewDashboard() {
        click(addNewDashboardOption);
    }

    // ==================== Utility Methods ====================

    public void goBack() {
        driver.navigate().back();
    }

    public void refresh() {
        driver.navigate().refresh();
    }
}
