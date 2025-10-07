package com.booking.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

/*
@Suite
@IncludeEngines("cucumber")
@SelectPackages("com.booking")
@SelectClasspathResource("features")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "com.booking")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty, html:target/cucumber-reports.html, json:target/cucumber.json")
public class TestRunner {
}*/

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features", // Path to your feature files
        glue = {"com.booking.stepdefinitions"}, // Package where your step definitions are located
        plugin = {"pretty", "html:target/cucumber-reports/html-report.html", "json:target/cucumber-reports/cucumber.json"},
        monochrome = true // Makes console output more readable
)
public class TestRunner {
}