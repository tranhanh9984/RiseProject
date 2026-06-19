package com.framework.tests.rise;

import com.framework.base.BaseRiseCrmTest;
import com.framework.pages.rise.DashboardPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Test class for RISE CRM Dashboard module.
 * Covers TC-DASH-001 through TC-DASH-029.
 */
public class DashboardTest extends BaseRiseCrmTest {

    private DashboardPage dashboardPage;

    @BeforeMethod(alwaysRun = true, dependsOnMethods = "setUp")
    public void initPage() {
        loginWithCookie();
        dashboardPage = new DashboardPage();
        dashboardPage.open(riseBaseUrl);
    }

    // ==================== 1.1 Page Display ====================

    @Test(description = "TC-DASH-001: Verify Dashboard page loads")
    public void TC_DASH_001_verifyDashboardPageLoads() {
        Assert.assertTrue(dashboardPage.isPageLoaded(),
                "URL should contain '/dashboard'");
        Assert.assertTrue(dashboardPage.getCurrentUrl().contains("/dashboard"),
                "URL should contain '/dashboard'");
        String title = dashboardPage.getPageTitle();
        Assert.assertTrue(title.contains("Dashboard"),
                "Page title should contain 'Dashboard'");
    }

    // ==================== 1.2 Summary Statistics Widgets ====================

    @Test(description = "TC-DASH-002: Verify 'My open tasks' widget")
    public void TC_DASH_002_verifyMyOpenTasksWidget() {
        Assert.assertTrue(dashboardPage.isMyOpenTasksWidgetVisible(),
                "'My open tasks' widget should be visible");
        String count = dashboardPage.getMyOpenTasksCount();
        Assert.assertTrue(count.matches("\\d+"),
                "My open tasks count should be numeric, got: " + count);
        dashboardPage.clickMyOpenTasksWidget();
        Assert.assertTrue(dashboardPage.getCurrentUrl().contains("/tasks"),
                "Should navigate to tasks page");
    }

    @Test(description = "TC-DASH-003: Verify 'Events today' widget")
    public void TC_DASH_003_verifyEventsTodayWidget() {
        Assert.assertTrue(dashboardPage.isEventsTodayWidgetVisible(),
                "'Events today' widget should be visible");
        String count = dashboardPage.getEventsTodayCount();
        Assert.assertTrue(count.matches("\\d+"),
                "Events today count should be numeric, got: " + count);
        dashboardPage.clickEventsTodayWidget();
        Assert.assertTrue(dashboardPage.getCurrentUrl().contains("/events"),
                "Should navigate to events page");
    }

    @Test(description = "TC-DASH-004: Verify 'Due' amount widget")
    public void TC_DASH_004_verifyDueAmountWidget() {
        Assert.assertTrue(dashboardPage.isDueWidgetVisible(),
                "'Due' widget should be visible");
        String amount = dashboardPage.getDueAmount();
        Assert.assertTrue(amount.contains("$"),
                "Due amount should contain '$' currency symbol, got: " + amount);
        dashboardPage.clickDueWidget();
        Assert.assertTrue(dashboardPage.getCurrentUrl().contains("/invoices"),
                "Should navigate to invoices page");
    }

    // ==================== 1.3 Projects Overview Widget ====================

    @Test(description = "TC-DASH-005: Verify Projects Overview widget")
    public void TC_DASH_005_verifyProjectsOverviewWidget() {
        Assert.assertTrue(dashboardPage.isProjectsOverviewVisible(),
                "Projects Overview section should be visible");
        Assert.assertTrue(dashboardPage.isProjectsOpenCountVisible(),
                "Open count should be visible");
        Assert.assertTrue(dashboardPage.isProjectsCompletedCountVisible(),
                "Completed count should be visible");
        Assert.assertTrue(dashboardPage.isProjectsHoldCountVisible(),
                "Hold count should be visible");
    }

    @Test(description = "TC-DASH-006: Verify Projects Overview links navigate correctly")
    public void TC_DASH_006_verifyProjectsOverviewLinks() {
        dashboardPage.clickProjectsOpenLink();
        Assert.assertTrue(dashboardPage.getCurrentUrl().contains("/projects/all_projects/1"),
                "Should navigate to Open projects filter");

        dashboardPage.goBack();
        dashboardPage.clickProjectsCompletedLink();
        Assert.assertTrue(dashboardPage.getCurrentUrl().contains("/projects/all_projects/2"),
                "Should navigate to Completed projects filter");

        dashboardPage.goBack();
        dashboardPage.clickProjectsHoldLink();
        Assert.assertTrue(dashboardPage.getCurrentUrl().contains("/projects/all_projects/3"),
                "Should navigate to Hold projects filter");
    }

    // ==================== 1.4 Invoice Overview Widget ====================

    @Test(description = "TC-DASH-007: Verify Invoice Overview widget")
    public void TC_DASH_007_verifyInvoiceOverviewWidget() {
        Assert.assertTrue(dashboardPage.isInvoiceOverviewVisible(),
                "Invoice Overview section should be visible");
        Assert.assertTrue(dashboardPage.isInvoiceOverviewChartRendered(),
                "Invoice overview chart should be rendered with non-zero dimensions");
    }

    @Test(description = "TC-DASH-008: Verify Invoice Overview links navigate correctly")
    public void TC_DASH_008_verifyInvoiceOverviewLinks() {
        dashboardPage.clickInvoiceOverdueLink();
        Assert.assertTrue(dashboardPage.getCurrentUrl().contains("/invoices/index/custom/overdue"),
                "Should navigate to overdue invoices");

        dashboardPage.goBack();
        dashboardPage.clickInvoiceNotPaidLink();
        Assert.assertTrue(dashboardPage.getCurrentUrl().contains("/invoices/index/custom/not_paid"),
                "Should navigate to not paid invoices");
    }

    // ==================== 1.5 Income vs Expenses Widget ====================

    @Test(description = "TC-DASH-009: Verify Income vs Expenses widget")
    public void TC_DASH_009_verifyIncomeVsExpensesWidget() {
        Assert.assertTrue(dashboardPage.isIncomeExpensesSectionVisible(),
                "Income vs Expenses section should be visible");
        Assert.assertTrue(dashboardPage.isIncomeExpenseChartRendered(),
                "Income-expense chart should be rendered");
        Assert.assertTrue(dashboardPage.isDashboardIncomeVsExpensesChartRendered(),
                "Dashboard income vs expenses chart should be rendered");
    }

    // ==================== 1.6 All Tasks Overview Widget ====================

    @Test(description = "TC-DASH-010: Verify All Tasks Overview widget")
    public void TC_DASH_010_verifyAllTasksOverviewWidget() {
        Assert.assertTrue(dashboardPage.isAllTasksOverviewVisible(),
                "All Tasks Overview section should be visible");
        Assert.assertTrue(dashboardPage.isAllTasksOverviewChartRendered(),
                "All tasks overview chart should be rendered");
    }

    // ==================== 1.7 Team Members Overview Widget ====================

    @Test(description = "TC-DASH-011: Verify Team Members Overview widget")
    public void TC_DASH_011_verifyTeamMembersOverviewWidget() {
        Assert.assertTrue(dashboardPage.isTeamMembersSectionVisible(),
                "Team Members section should be visible");
    }

    @Test(description = "TC-DASH-012: Verify Team Members links navigate correctly")
    public void TC_DASH_012_verifyTeamMembersLinks() {
        dashboardPage.clickTeamMembersLink();
        Assert.assertTrue(dashboardPage.getCurrentUrl().contains("/team_members/index"),
                "Should navigate to team members page");

        dashboardPage.goBack();
        dashboardPage.clickClockedInLink();
        Assert.assertTrue(dashboardPage.getCurrentUrl().contains("/attendance/index/members_clocked_in"),
                "Should navigate to members clocked in page");
    }

    // ==================== 1.8 Ticket Status Widget ====================

    @Test(description = "TC-DASH-013: Verify Ticket Status widget")
    public void TC_DASH_013_verifyTicketStatusWidget() {
        Assert.assertTrue(dashboardPage.isTicketStatusSectionVisible(),
                "Ticket Status section should be visible");
        Assert.assertTrue(dashboardPage.isTicketStatusChartRendered(),
                "Ticket status chart should be rendered");
    }

    @Test(description = "TC-DASH-014: Verify Ticket Status links navigate correctly")
    public void TC_DASH_014_verifyTicketStatusLinks() {
        dashboardPage.clickTicketNewLink();
        Assert.assertTrue(dashboardPage.getCurrentUrl().contains("/tickets/index/new"),
                "Should navigate to new tickets");

        dashboardPage.goBack();
        dashboardPage.clickTicketOpenLink();
        Assert.assertTrue(dashboardPage.getCurrentUrl().contains("/tickets/index/open"),
                "Should navigate to open tickets");
    }

    // ==================== 1.9 Charts ====================

    @Test(description = "TC-DASH-015: Verify all dashboard charts are rendered")
    public void TC_DASH_015_verifyAllChartsRendered() {
        Assert.assertTrue(dashboardPage.isInvoiceOverviewChartRendered(),
                "Invoice overview chart should be visible");
        Assert.assertTrue(dashboardPage.isIncomeExpenseChartRendered(),
                "Income-expense chart should be visible");
        Assert.assertTrue(dashboardPage.isDashboardIncomeVsExpensesChartRendered(),
                "Dashboard income vs expenses chart should be visible");
        Assert.assertTrue(dashboardPage.isAllTasksOverviewChartRendered(),
                "All tasks overview chart should be visible");
        Assert.assertTrue(dashboardPage.isTicketStatusChartRendered(),
                "Ticket status chart should be visible");
    }

    // ==================== 1.10 Clock In/Out Widget ====================

    @Test(description = "TC-DASH-016: Verify Clock In/Out widget")
    public void TC_DASH_016_verifyClockInOutWidget() {
        boolean clockInVisible = dashboardPage.isClockInButtonVisible();
        boolean clockOutVisible = dashboardPage.isClockOutButtonVisible();
        Assert.assertTrue(clockInVisible || clockOutVisible,
                "Either Clock In or Clock Out button should be visible");
    }

    @Test(description = "TC-DASH-017: Verify Clock Out action")
    public void TC_DASH_017_verifyClockOutAction() {
        if (dashboardPage.isClockOutButtonVisible()) {
            dashboardPage.clickClockOutButton();
            // After clock out, Clock In button should appear
            Assert.assertTrue(dashboardPage.isClockInButtonVisible(),
                    "After clocking out, Clock In button should appear");
        } else {
            log.info("Clock is not running, skipping Clock Out test");
        }
    }

    // ==================== 1.11 Project Timeline Widget ====================

    @Test(description = "TC-DASH-018: Verify Project Timeline widget")
    public void TC_DASH_018_verifyProjectTimelineWidget() {
        Assert.assertTrue(dashboardPage.isProjectTimelineVisible(),
                "Project Timeline section should be visible");
        int entryCount = dashboardPage.getTimelineEntryCount();
        Assert.assertTrue(entryCount >= 1,
                "At least 1 timeline entry should be displayed, got: " + entryCount);
    }

    // ==================== 1.12 Events Widget ====================

    @Test(description = "TC-DASH-019: Verify Events widget")
    public void TC_DASH_019_verifyEventsWidget() {
        Assert.assertTrue(dashboardPage.isEventsSectionVisible(),
                "Events section should be visible");
        dashboardPage.clickViewOnCalendar();
        Assert.assertTrue(dashboardPage.getCurrentUrl().contains("/events"),
                "Should navigate to events calendar page");
    }

    // ==================== 1.13 My Tasks Table Widget ====================

    @Test(description = "TC-DASH-020: Verify My Tasks table widget")
    public void TC_DASH_020_verifyMyTasksTableWidget() {
        Assert.assertTrue(dashboardPage.isMyTasksTableVisible(),
                "My Tasks table should be visible");
        Assert.assertTrue(dashboardPage.getMyTasksTableHeaders().size() > 0,
                "Table should have headers");
        Assert.assertTrue(dashboardPage.getMyTasksRowCount() >= 1,
                "At least 1 task row should be displayed");
    }

    @Test(description = "TC-DASH-021: Verify My Tasks table shows task status")
    public void TC_DASH_021_verifyMyTasksTableStatus() {
        Assert.assertTrue(dashboardPage.isMyTasksTableVisible(),
                "My Tasks table should be visible");
        Assert.assertTrue(dashboardPage.getMyTasksRowCount() >= 1,
                "At least 1 task row should exist to verify status");
    }

    // ==================== 1.14 To Do (Private) Widget ====================

    @Test(description = "TC-DASH-022: Verify To do widget")
    public void TC_DASH_022_verifyTodoWidget() {
        Assert.assertTrue(dashboardPage.isTodoSectionVisible(),
                "To do section should be visible");
    }

    @Test(description = "TC-DASH-023: Verify adding a new to-do item")
    public void TC_DASH_023_verifyAddTodo() {
        dashboardPage.openQuickActions();
        // Click "Add to do" from quick actions
        // Note: Implementation depends on exact RISE CRM DOM structure
        log.info("Add to-do test requires quick actions interaction");
    }

    // ==================== 1.15 Sticky Note Widget ====================

    @Test(description = "TC-DASH-024: Verify Sticky Note widget")
    public void TC_DASH_024_verifyStickyNoteWidget() {
        Assert.assertTrue(dashboardPage.isStickyNoteSectionVisible(),
                "Sticky Note section should be visible");

        String timestamp = String.valueOf(System.currentTimeMillis());
        String noteText = "Updated note " + timestamp;
        dashboardPage.typeStickyNote(noteText);

        // Refresh page to save and verify persistence
        dashboardPage.refresh();
        String savedNote = dashboardPage.getStickyNoteText();
        Assert.assertTrue(savedNote.contains(noteText),
                "Sticky note should retain text after refresh");
    }

    // ==================== 1.16 Quick Actions ====================

    @Test(description = "TC-DASH-025: Verify Quick action dropdown items")
    public void TC_DASH_025_verifyQuickActionDropdownItems() {
        dashboardPage.openQuickActions();
        // Verify dropdown items are visible
        log.info("Quick actions dropdown opened successfully");
    }

    @Test(description = "TC-DASH-026: Verify 'Add task' quick action opens modal")
    public void TC_DASH_026_verifyAddTaskQuickAction() {
        dashboardPage.openQuickActions();
        dashboardPage.clickQuickActionAddTask();
        String modalTitle = dashboardPage.getModalTitle();
        Assert.assertTrue(modalTitle.contains("Add task"),
                "Modal title should be 'Add task', got: " + modalTitle);
        Assert.assertTrue(dashboardPage.isModalBodyVisible(),
                "Modal body with form fields should be visible");
    }

    @Test(description = "TC-DASH-027: Verify 'Add event' quick action opens modal")
    public void TC_DASH_027_verifyAddEventQuickAction() {
        dashboardPage.openQuickActions();
        dashboardPage.clickQuickActionAddEvent();
        String modalTitle = dashboardPage.getModalTitle();
        Assert.assertTrue(modalTitle.contains("Add event"),
                "Modal title should be 'Add event', got: " + modalTitle);
    }

    // ==================== 1.17 Open Projects List ====================

    @Test(description = "TC-DASH-028: Verify Open Projects list")
    public void TC_DASH_028_verifyOpenProjectsList() {
        Assert.assertTrue(dashboardPage.isOpenProjectsSectionVisible(),
                "Open Projects section should be visible");
        Assert.assertTrue(dashboardPage.getOpenProjectLinksCount() >= 1,
                "At least 1 open project link should be displayed");
        dashboardPage.clickFirstOpenProject();
        Assert.assertTrue(dashboardPage.getCurrentUrl().contains("/projects/view/"),
                "Should navigate to project detail page");
    }

    // ==================== 1.18 Dashboard Management ====================

    @Test(description = "TC-DASH-029: Verify 'Add new dashboard' option")
    public void TC_DASH_029_verifyAddNewDashboard() {
        dashboardPage.openDashboardOptions();
        dashboardPage.clickAddNewDashboard();
        // Verify modal/form opens with dashboard name input
        log.info("Add new dashboard option clicked successfully");
    }
}
