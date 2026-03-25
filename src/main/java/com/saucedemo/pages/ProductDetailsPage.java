package com.saucedemo.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class ProductDetailsPage {
    private final SelenideElement productName = $(".inventory_details_name");
    private final SelenideElement productPrice = $(".inventory_details_price");
    private final SelenideElement addToCartButton = $(".btn_inventory");
    private final SelenideElement backToProductsButton = $("#back-to-products");

    @Step("Get product name from details page")
    public String getProductName() {
        return productName.getText();
    }

    @Step("Get product price from details page")
    public String getProductPrice() {
        return productPrice.getText();
    }

    @Step("Add product to cart from details page")
    public void addProductToCart() {
        addToCartButton.click();
    }

    @Step("Go back to products page")
    public void goBackToProducts() {
        backToProductsButton.click();
    }
}
