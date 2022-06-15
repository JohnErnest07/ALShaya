package TestRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/Regression/",
        glue = "StepDefinitions",
        monochrome = true,
        publish=false
)
public class TestRunner {

}
