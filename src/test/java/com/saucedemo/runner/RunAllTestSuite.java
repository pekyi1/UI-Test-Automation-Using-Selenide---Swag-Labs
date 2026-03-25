package com.saucedemo.runner;

import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SuiteDisplayName("Swag Labs UI Test Suite")
@SelectPackages("com.saucedemo.tests")
public class RunAllTestSuite {
}
