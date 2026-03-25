package com.saucedemo.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class InventoryPage {
    private final SelenideElement title = $(".title");
    private final ElementsCollection inventoryItems = $$(".inventory_item");
    private final SelenideElement shoppingCartLink = $(".shopping_cart_link");
    private final SelenideElement shoppingCartBadge = $(".shopping_cart_badge");

    @Step("Verify inventory page is visible")
    public boolean isPageTitleVisible() {
        return title.shouldBe(visible).isDisplayed() && title.getText().equals("Products");
    }

    @Step("Get product element by name {productName}")
    private SelenideElement getProductByName(String productName) {
        return inventoryItems.find(com.codeborne.selenide.Condition.text(productName));
    }

    @Step("Add product {productName} to cart")
    public void addProductToCart(String productName) {
        getProductByName(productName).$(".btn_inventory").click();
    }

    @Step("Remove product {productName} from cart")
    public void removeProductFromCart(String productName) {
        getProductByName(productName).$(".btn_inventory").click();
    }

    @Step("Click on product name {productName}")
    public void openProductDetails(String productName) {
        getProductByName(productName).$(".inventory_item_name").click();
    }

    @Step("Go to shopping cart")
    public void goToCart() {
        shoppingCartLink.click();
    }

    @Step("Get shopping cart badge count")
    public String getCartBadgeCount() {
        return shoppingCartBadge.getText();
    }

    @Step("Check if cart badge is visible")
    public boolean isCartBadgeVisible() {
        return shoppingCartBadge.isDisplayed();
    }
}
