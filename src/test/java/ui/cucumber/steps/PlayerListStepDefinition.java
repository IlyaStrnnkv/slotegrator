package ui.cucumber.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import ui.pages.LoginPage;
import ui.pages.PlayersPage;
import ui.pages.components.LeftMenuComponent;

import static ui.data.AuthorizationData.CORRECT_ADMIN_LOGIN;
import static ui.data.AuthorizationData.CORRECT_ADMIN_PASSWORD;
import static ui.data.DefaultTestSetupData.BASE_URL;
import static ui.helpers.DriverFactory.createDriverForCucumber;

public class PlayerListStepDefinition {

    /**
     * Browser driver.
     */
    private WebDriver driver;

    @Before
    public final void setUp() {
        driver = createDriverForCucumber("chrome");
        driver.get(BASE_URL);
    }

    @Given("Login as admin")
    public final void loginAsAdmin() {
        new LoginPage(driver)
            .login(CORRECT_ADMIN_LOGIN, CORRECT_ADMIN_PASSWORD);
    }

    @When("Open player list")
    public final void openPlayerList() {
        new LeftMenuComponent(driver)
            .clickToUsersSection()
            .clickToPlayersSubsection();
    }

    @And("Open list of Players")
    public final void openListOfPlayers() {
        new LeftMenuComponent(driver)
            .clickToUsersSection()
            .clickToPlayersSubsection();
    }

    @Then("Check that player list is open")
    public final void checkThatPlayerListIsOpen() {
        new PlayersPage(driver)
            .checkPlayersPageIsOpen();
    }

    @Then("Check sort by status column")
    public final void checkSortByStatusColumn() {
        new PlayersPage(driver)
            .checkSortStatusColumn();
    }

    /**
     * Suite teardown.
     */
    @After
    public final void tearDown() {
        driver.quit();
    }
}
