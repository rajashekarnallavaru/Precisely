package com.precisely.step_definitions.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = {
        "src/main/resources/features/PreciselyTests.feature" }, glue = {
                "com.precisely.automation" }, plugin = { "pretty", "html:target/cucumber-reports",
                        "json:target/cucumber-reports/PreciselyTests.json",
                        "junit:target/cucumber-reports/PreciselyTests.xml",
                        "rerun:target/rerun/PreciselyTests.txt" }, monochrome = true)

public class Test_PreciselyTests {
}
