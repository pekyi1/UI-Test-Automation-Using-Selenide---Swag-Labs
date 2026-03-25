package com.saucedemo.tests;

import com.codeborne.selenide.junit5.SoftAssertsExtension;
import com.saucedemo.pages.*;
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
@Feature("Regression Tests")
@ExtendWith({ SoftAssertsExtension.class })
public class RegressionTests extends BaseTest {
    private final LoginPage loginPage = new LoginPage();
    private final InventoryPage inventoryPage = new InventoryPage();
    private final CartPage cartPage = new CartPage();
    private final CheckoutStepOnePage checkoutStepOnePage = new CheckoutStepOnePage();
    private final CheckoutStepTwoPage checkoutStepTwoPage = new CheckoutStepTwoPage();
    private final CheckoutCompletePage checkoutCompletePage = new CheckoutCompletePage();

    @Test
    @Tag("Regression")
    @Story("Cart Functionality")
    @DisplayName("Verify adding/removing products from cart")
    public void testCartOperations() {
        loginPage.openPage().login(TestData.STANDARD_USER, TestData.PASSWORD);

        inventoryPage.addProductToCart(TestData.PRODUCT_BACKPACK);
        inventoryPage.addProductToCart(TestData.PRODUCT_BIKE_LIGHT);
        assertEquals("2", inventoryPage.getCartBadgeCount(), "Cart badge should show 2");

        inventoryPage.goToCart();
        assertTrue(cartPage.isProductInCart(TestData.PRODUCT_BACKPACK), "Backpack should be in cart");
        assertTrue(cartPage.isProductInCart(TestData.PRODUCT_BIKE_LIGHT), "Bike Light should be in cart");

        cartPage.removeProduct(TestData.PRODUCT_BACKPACK);
        assertEquals(1, cartPage.getCartItemsCount(), "One item should remain in cart");
    }

    @Test
    @Tag("Regression")
    @Story("End-to-End Checkout")
    @DisplayName("Verify complete checkout process")
    public void testSuccessfulCheckout() {
        loginPage.openPage().login(TestData.STANDARD_USER, TestData.PASSWORD);

        String productName = TestData.PRODUCT_BACKPACK;
        inventoryPage.addProductToCart(productName);
        inventoryPage.goToCart();
        cartPage.checkout();

        checkoutStepOnePage.fillCustomerInfo("John", "Doe", "12345");

        // Example of checking multiple values (Soft Assertions will allow both to be
        // checked even if one fails)
        assertEquals("Total: $32.39", checkoutStepTwoPage.getTotal(), "Total price should be correct");
        checkoutStepTwoPage.finish();

        assertTrue(checkoutCompletePage.isCompleteHeaderDisplayed(), "Successful checkout header should be displayed");
        assertEquals("Thank you for your order!", checkoutCompletePage.getSuccessHeaderText(),
                "Success message should match");
    }
}
