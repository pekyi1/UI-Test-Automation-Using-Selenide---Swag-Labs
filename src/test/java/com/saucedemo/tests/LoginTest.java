package com.saucedemo.tests;

import com.saucedemo.base.BaseTest;
import com.saucedemo.pages.InventoryPage;
import com.saucedemo.pages.LoginPage;
import com.saucedemo.utils.TestData;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("UI Automation")
@Feature("Login Tests")
public class LoginTest extends BaseTest {
    private final LoginPage loginPage = new LoginPage();
    private final InventoryPage inventoryPage = new InventoryPage();

    @Test
    @Tag("Smoke")
    @Story("Standard User Login")
    @DisplayName("Verify successful login with standard_user")
    @Description("Logging in with standard_user credentials should redirect to the inventory page.")
    public void testStandardUserLogin() {
        loginPage.openPage().login(TestData.STANDARD_USER, TestData.PASSWORD);
        assertTrue(inventoryPage.isPageTitleVisible(), "Inventory page title 'Products' should be visible");
    }

    @Test
    @Tag("Smoke")
    @Story("Invalid User Login")
    @DisplayName("Verify error message for invalid credentials")
    public void testInvalidLogin() {
        loginPage.openPage().login(TestData.WRONG_USER, TestData.WRONG_PASSWORD);
        assertEquals(TestData.ERROR_MSG_LOGIN,
                loginPage.getErrorMessage(), "Error message should match");
    }

    @Test
    @Tag("Smoke")
    @Story("Locked Out User Login")
    @DisplayName("Verify error message for locked out user")
    public void testLockedOutUserLogin() {
        loginPage.openPage().login(TestData.LOCKED_OUT_USER, TestData.PASSWORD);
        assertEquals(TestData.ERROR_MSG_LOCKED_OUT,
                loginPage.getErrorMessage(), "Locked out error message should match");
    }
}
