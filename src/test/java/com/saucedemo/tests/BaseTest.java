package com.saucedemo.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

public class BaseTest {
    @BeforeAll
    public static void setUp() {
        // Basic configuration
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 5000;
        Configuration.screenshots = true;
        Configuration.savePageSource = false;

        // Headless mode configuration via system property or default
        Configuration.headless = Boolean.parseBoolean(System.getProperty("headless", "false"));

        // Browser selection
        Configuration.browser = System.getProperty("browser", "chrome");

        // Add Allure Selenide listener
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(false));
    }

    @AfterEach
    public void tearDown() {
        Selenide.closeWebDriver();
    }
}
