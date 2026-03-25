package com.saucedemo.tests;

import com.saucedemo.pages.InventoryPage;
import com.saucedemo.pages.LoginPage;
import com.saucedemo.pages.ProductDetailsPage;
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
@Feature("Smoke Tests")
public class SmokeTests extends BaseTest {
    private final LoginPage loginPage = new LoginPage();
    private final InventoryPage inventoryPage = new InventoryPage();
    private final ProductDetailsPage productDetailsPage = new ProductDetailsPage();

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
        loginPage.openPage().login("wrong_user", "wrong_password");
        assertEquals("Epic sadface: Username and password do not match any user in this service",
                loginPage.getErrorMessage(), "Error message should match");
    }

    @Test
    @Tag("Smoke")
    @Story("Locked Out User Login")
    @DisplayName("Verify error message for locked out user")
    public void testLockedOutUserLogin() {
        loginPage.openPage().login(TestData.LOCKED_OUT_USER, TestData.PASSWORD);
        assertEquals("Epic sadface: Sorry, this user has been locked out.",
                loginPage.getErrorMessage(), "Locked out error message should match");
    }

    @Test
    @Tag("Smoke")
    @Story("Product Page")
    @DisplayName("Verify product details page")
    public void testProductDetailsVisibility() {
        loginPage.openPage().login(TestData.STANDARD_USER, TestData.PASSWORD);
        inventoryPage.openProductDetails(TestData.PRODUCT_BACKPACK);
        assertEquals(TestData.PRODUCT_BACKPACK, productDetailsPage.getProductName(), "Product name should match");
        assertTrue(productDetailsPage.getProductPrice().contains("29.99"), "Price should be correct");
    }
}
