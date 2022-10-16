package ui.cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/resources/slotegrator.feature",
    glue = {"steps"})
public class RunnerTest extends AbstractTestNGCucumberTests {
}
