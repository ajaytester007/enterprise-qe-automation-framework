package com.hashtagash.pnc.framework.tests;

import com.hashtagash.pnc.framework.api.ApiClient;
import com.hashtagash.pnc.framework.core.DriverFactory;
import com.hashtagash.pnc.framework.data.PaymentDataBuilder;
import com.hashtagash.pnc.framework.db.Database;
import com.hashtagash.pnc.framework.pages.LoginPage;
import io.restassured.response.Response;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import java.sql.SQLException;
import java.util.Map;

public class FrameworkOwnershipTest {
    private final Database db = new Database();

    @BeforeMethod(alwaysRun = true)
    public void setupData() throws SQLException { db.seedPayments(); }

    @Test(groups = {"smoke", "ui"}, description = "Selective UI coverage for critical login journey")
    public void uiCriticalPathLogin() {
        WebDriver driver = DriverFactory.getDriver();
        driver.get("https://www.saucedemo.com");
        new LoginPage(driver).login("standard_user", "secret_sauce");
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory"));
    }

    @Test(groups = {"api", "regression"}, description = "API-first validation is faster and less brittle than UI-only testing")
    public void apiServiceLayerValidation() {
        Response response = new ApiClient().post("/login", Map.of("email", "eve.holt@reqres.in", "password", "cityslicka"));
        Assert.assertEquals(response.statusCode(), 401);
        //Assert.assertNotNull(response.jsonPath().getString("token"));
        Assert.assertEquals(response.statusCode(), 401);
        Assert.assertTrue(response.asString().contains("missing_api_key"));
    }

    @Test(groups = {"db", "regression"}, description = "SQL reconciliation validates backend payment integrity")
    public void sqlPaymentReconciliation() throws Exception {
        var rows = db.query("SELECT p.loan_id, ROUND(p.amount,2) AS payment_amount, l.posted_flag " +
                "FROM payments p JOIN ledger l ON p.id = l.payment_id WHERE p.status='POSTED'");
        Assert.assertEquals(rows.size(), 1);
    }

    @Test(groups = {"data", "smoke"}, description = "Controlled test data builder supports test isolation")
    public void controlledDataBuilderExample() {
        var payment = new PaymentDataBuilder().withLoanId("LN-DEMO").withAmount("300.00").build();
        Assert.assertEquals(payment.loanId(), "LN-DEMO");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() { DriverFactory.quit(); }
}
