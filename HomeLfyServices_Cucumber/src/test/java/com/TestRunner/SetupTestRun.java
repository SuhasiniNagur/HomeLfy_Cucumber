package com.TestRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		
    features = "src//test//resources//Features//setup.feature", // path to your feature files
    glue = "com.StepDefination",      // package where your step definitions are located
    plugin = {"pretty", "html:test-output"},
    monochrome = true,
    dryRun = false
//    extraGlue = "path.to.CucumberTypeRegistry" // Register the parameter type class
)

public class SetupTestRun {

}
