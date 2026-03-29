package com.saucedemo.tests;

import com.codeborne.selenide.junit5.SoftAssertsExtension;
import com.saucedemo.base.BaseTest;
import com.saucedemo.pages.CartPage;
import com.saucedemo.pages.InventoryPage;
import com.saucedemo.pages.LoginPage;
import com.saucedemo.utils.TestData;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("UI Automation")
@Feature("Cart Operations")
@ExtendWith({ SoftAssertsExtension.class })
public class CartTest extends BaseTest {
    private final LoginPage loginPage = new LoginPage();
    private final InventoryPage inventoryPage = new InventoryPage();
    private final CartPage cartPage = new CartPage();

    @Test
    @Tag("Regression")
    @Story("Cart Functionality")
    @DisplayName("Verify adding/removing products from cart")
    public void testCartOperations() {
        loginPage.openPage().login(TestData.STANDARD_USER, TestData.PASSWORD);

        inventoryPage.addProductToCart(TestData.PRODUCT_BACKPACK);
        inventoryPage.addProductToCart(TestData.PRODUCT_BIKE_LIGHT);
        assertEquals(TestData.CART_BADGE_TWO, inventoryPage.getCartBadgeCount(), "Cart badge should show 2");

        inventoryPage.goToCart();
        assertTrue(cartPage.isProductInCart(TestData.PRODUCT_BACKPACK), "Backpack should be in cart");
        assertTrue(cartPage.isProductInCart(TestData.PRODUCT_BIKE_LIGHT), "Bike Light should be in cart");

        cartPage.removeProduct(TestData.PRODUCT_BACKPACK);
        assertEquals(TestData.REMAINING_ITEMS_COUNT, cartPage.getCartItemsCount(), "One item should remain in cart");
    }
}
