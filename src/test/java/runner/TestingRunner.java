package runner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin={"html:target/cucumberHTML-report",
                "json:target/cucumber.json"},
        features = "src/test/resources/TestFeatures",
        glue="stepdefinitions",
        tags = "@apiUi",
        dryRun = false
)

public class TestingRunner {

}
