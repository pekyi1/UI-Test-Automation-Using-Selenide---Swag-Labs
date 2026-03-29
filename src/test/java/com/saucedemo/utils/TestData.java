package com.saucedemo.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;

public class TestData {
    private static JsonNode root;

    static {
        try {
            ObjectMapper mapper = new ObjectMapper();
            InputStream inputStream = TestData.class.getClassLoader().getResourceAsStream("testdata.json");
            if (inputStream == null) {
                throw new RuntimeException("Could not find testdata.json in resources");
            }
            root = mapper.readTree(inputStream);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load test data from JSON", e);
        }
    }

    private static String get(String path) {
        String[] keys = path.split("\\.");
        JsonNode node = root;
        for (String key : keys) {
            node = node.path(key);
        }
        return node.asText();
    }

    // Credentials
    public static final String STANDARD_USER = get("users.standard.username");
    public static final String LOCKED_OUT_USER = get("users.lockedOut.username");
    public static final String PASSWORD = get("users.standard.password");
    public static final String WRONG_USER = get("users.invalid.username");
    public static final String WRONG_PASSWORD = get("users.invalid.password");

    // Products
    public static final String PRODUCT_BACKPACK = get("products.backpack");
    public static final String PRODUCT_BIKE_LIGHT = get("products.bikeLight");

    // Customer Info
    public static final String FIRST_NAME = get("customer.firstName");
    public static final String LAST_NAME = get("customer.lastName");
    public static final String ZIP_CODE = get("customer.zipCode");

    // Messages
    public static final String ERROR_MSG_LOGIN = get("messages.loginError");
    public static final String ERROR_MSG_LOCKED_OUT = get("messages.lockedOutError");
    public static final String CHECKOUT_COMPLETE_MSG = get("messages.checkoutComplete");

    // Expected Values
    public static final String TOTAL_PRICE = get("values.totalPrice");
    public static final String BACKPACK_PRICE = get("values.backpackPrice");
    public static final String CART_BADGE_TWO = get("values.cartBadgeTwo");
    public static final int REMAINING_ITEMS_COUNT = Integer.parseInt(get("values.remainingItemsCount"));
    public static final String APP_TITLE = get("values.appTitle");
}
