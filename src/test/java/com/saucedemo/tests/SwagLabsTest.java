package com.saucedemo.tests;

import com.saucedemo.base.BaseTest;
import com.saucedemo.pages.LoginPage;
import com.saucedemo.utils.TestData;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.title;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Epic("UI Automation")
@Feature("General Application Tests")
public class SwagLabsTest extends BaseTest {
    private final LoginPage loginPage = new LoginPage();

    @Test
    @Tag("Smoke")
    @Story("Application Identity")
    @DisplayName("Verify page title is Swag Labs")
    public void testPageTitle() {
        loginPage.openPage();
        assertEquals("Swag Labs", title(), "Page title should be Swag Labs");
    }

    @Test
    @Tag("Smoke")
    @Story("Logout")
    @DisplayName("Verify successful logout")
    public void testLogout() {
        // This is a placeholder for logout functionality if implemented in POM
        loginPage.openPage().login(TestData.STANDARD_USER, TestData.PASSWORD);
        // Note: Logout is usually in a sidebar, not yet fully implemented in current
        // POM
    }
}
