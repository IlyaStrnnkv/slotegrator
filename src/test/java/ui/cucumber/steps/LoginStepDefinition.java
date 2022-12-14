package ui.cucumber.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import ui.pages.LoginPage;
import ui.pages.components.LeftMenuComponent;

import static ui.data.AuthorizationData.CORRECT_ADMIN_LOGIN;
import static ui.data.AuthorizationData.CORRECT_ADMIN_PASSWORD;
import static ui.data.DefaultTestSetupData.BASE_URL;
import static ui.helpers.DriverFactory.createDriverForCucumber;

public class LoginStepDefinition {

    /**
     * Browser driver.
     */
    private WebDriver driver;

    @Before
    public final void setUp() {
        driver = createDriverForCucumber("chrome");
        driver.get(BASE_URL);
    }

    @When("Login as admin with correct data")
    public void loginAsAdminWithCorrectData() {
        new LoginPage(driver)
            .inputLogin(CORRECT_ADMIN_LOGIN)
            .inputPassword(CORRECT_ADMIN_PASSWORD)
            .clickToSignInButton();
    }

    @Then("Check authorization is successful")
    public void checkAuthorizationIsSuccessful() {
        new LeftMenuComponent(driver)
            .checkAuthorizationSuccessful();
    }

    /**
     * Suite teardown.
     */
    @After
    public final void tearDown() {
        driver.quit();
    }
}
