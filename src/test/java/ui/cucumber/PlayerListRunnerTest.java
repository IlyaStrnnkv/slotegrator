package ui.cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/resources/playerList.feature",
    glue = {"steps"})
public class PlayerListRunnerTest extends AbstractTestNGCucumberTests {
}
