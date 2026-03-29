package com.saucedemo.tests;

import com.saucedemo.base.BaseTest;
import com.saucedemo.pages.InventoryPage;
import com.saucedemo.pages.LoginPage;
import com.saucedemo.pages.ProductDetailsPage;
import com.saucedemo.utils.TestData;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("UI Automation")
@Feature("Inventory and Products")
public class InventoryTest extends BaseTest {
    private final LoginPage loginPage = new LoginPage();
    private final InventoryPage inventoryPage = new InventoryPage();
    private final ProductDetailsPage productDetailsPage = new ProductDetailsPage();

    @Test
    @Tag("Smoke")
    @Story("Product Visibility")
    @DisplayName("Verify product details page visibility")
    public void testProductDetailsVisibility() {
        loginPage.openPage().login(TestData.STANDARD_USER, TestData.PASSWORD);
        inventoryPage.openProductDetails(TestData.PRODUCT_BACKPACK);
        assertEquals(TestData.PRODUCT_BACKPACK, productDetailsPage.getProductName(), "Product name should match");
        assertTrue(productDetailsPage.getProductPrice().contains(TestData.BACKPACK_PRICE), "Price should be correct");
    }

    @Test
    @Tag("Smoke")
    @Story("Main Page Title")
    @DisplayName("Verify inventory page title")
    public void testInventoryPageTitle() {
        loginPage.openPage().login(TestData.STANDARD_USER, TestData.PASSWORD);
        assertTrue(inventoryPage.isPageTitleVisible(), "Inventory page title 'Products' should be visible");
    }
}
