package com.saucedemo.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CartPage {
    private final ElementsCollection cartItems = $$(".cart_item");
    private final SelenideElement checkoutButton = $("#checkout");
    private final SelenideElement continueShoppingButton = $("#continue-shopping");

    @Step("Check if product {productName} is in cart")
    public boolean isProductInCart(String productName) {
        return cartItems.find(com.codeborne.selenide.Condition.text(productName)).isDisplayed();
    }

    @Step("Remove product {productName} from cart")
    public void removeProduct(String productName) {
        cartItems.find(com.codeborne.selenide.Condition.text(productName)).$(".btn_secondary").click();
    }

    @Step("Got to checkout")
    public void checkout() {
        checkoutButton.shouldBe(com.codeborne.selenide.Condition.visible).click();
    }

    @Step("Go back to shopping")
    public void continueShopping() {
        continueShoppingButton.click();
    }

    @Step("Get total cart items count")
    public int getCartItemsCount() {
        return cartItems.size();
    }
}
