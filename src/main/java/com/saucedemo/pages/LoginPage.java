package com.saucedemo.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class LoginPage {
    private final SelenideElement userNameField = $(By.id("user-name"));
    private final SelenideElement passwordField = $(By.id("password"));
    private final SelenideElement loginButton = $(By.id("login-button"));
    private final SelenideElement errorMessage = $("[data-test='error']");

    @Step("Open Swag Labs login page")
    public LoginPage openPage() {
        String baseUrl = System.getProperty("baseUrl", "https://www.saucedemo.com/");
        open(baseUrl);
        return this;
    }

    @Step("Login with username {username} and password {password}")
    public void login(String username, String password) {
        userNameField.setValue(username);
        passwordField.setValue(password);
        loginButton.click();
    }

    @Step("Get error message text")
    public String getErrorMessage() {
        return errorMessage.getText();
    }
}
