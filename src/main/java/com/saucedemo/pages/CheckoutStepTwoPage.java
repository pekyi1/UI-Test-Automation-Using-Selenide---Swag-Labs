package com.saucedemo.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class CheckoutStepTwoPage {
    private final SelenideElement subtotalLabel = $(".summary_subtotal_label");
    private final SelenideElement taxLabel = $(".summary_tax_label");
    private final SelenideElement totalLabel = $(".summary_total_label");
    private final SelenideElement finishButton = $(By.id("finish"));
    private final SelenideElement cancelButton = $(By.id("cancel"));

    @Step("Finish checkout")
    public void finish() {
        finishButton.click();
    }

    @Step("Go back")
    public void cancel() {
        cancelButton.click();
    }

    @Step("Get subtotal")
    public String getSubtotal() {
        return subtotalLabel.getText();
    }

    @Step("Get tax")
    public String getTax() {
        return taxLabel.getText();
    }

    @Step("Get total")
    public String getTotal() {
        return totalLabel.getText();
    }
}
