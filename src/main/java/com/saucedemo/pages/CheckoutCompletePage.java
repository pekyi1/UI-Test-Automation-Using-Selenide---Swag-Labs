package com.saucedemo.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class CheckoutCompletePage {
    private final SelenideElement completeHeader = $(".complete-header");
    private final SelenideElement backToProductsButton = $(By.id("back-to-products"));

    @Step("Get success header text")
    public String getSuccessHeaderText() {
        return completeHeader.getText();
    }

    @Step("Check if order complete message is displayed")
    public boolean isCompleteHeaderDisplayed() {
        return completeHeader.isDisplayed();
    }

    @Step("Go back to products page")
    public void backToProducts() {
        backToProductsButton.click();
    }
}
