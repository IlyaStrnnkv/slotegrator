package ui.cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/resources/login.feature",
    glue = {"steps"})
public class LoginRunnerTest extends AbstractTestNGCucumberTests {
}
