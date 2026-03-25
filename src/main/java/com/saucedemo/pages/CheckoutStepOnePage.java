package com.saucedemo.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class CheckoutStepOnePage {
    private final SelenideElement firstNameField = $(By.id("first-name"));
    private final SelenideElement lastNameField = $(By.id("last-name"));
    private final SelenideElement postalCodeField = $(By.id("postal-code"));
    private final SelenideElement continueButton = $(By.id("continue"));
    private final SelenideElement cancelButton = $(By.id("cancel"));

    @Step("Fill checkout customer information: {firstName}, {lastName}, {postalCode}")
    public void fillCustomerInfo(String firstName, String lastName, String postalCode) {
        firstNameField.setValue(firstName);
        lastNameField.setValue(lastName);
        postalCodeField.setValue(postalCode);
        continueButton.click();
    }

    @Step("Cancel checkout")
    public void cancel() {
        cancelButton.click();
    }
}
