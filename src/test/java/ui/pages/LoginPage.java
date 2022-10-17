package ui.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ui.base.BasePage;
import ui.pages.components.LeftMenuComponent;

public class LoginPage extends BasePage {

    /**
     * Page url.
     */
    private static final String PAGE_URL = "admin/login";

    @FindBy(xpath = "//input[@placeholder='Login']")
    private WebElement loginInput;

    @FindBy(xpath = "//input[@placeholder='Password']")
    private WebElement passwordInput;

    @FindBy(xpath = "//input[@type='submit']")
    private WebElement signInButton;

    /**
     * Page object constructor. Checks that page is open when created.
     * Init elements
     * @param driver web driver
     */
    public LoginPage(WebDriver driver) {
        super(driver);
        checkPageIsPresentByUrl(PAGE_URL);
    }

    @Step("Ввести {login} в поле 'Login'")
    public final LoginPage inputLogin(String login) {
        loginInput.sendKeys(login);
        return this;
    }

    @Step("Ввести пароль в поле 'Password'")
    public final LoginPage inputPassword(String pass) {
        passwordInput.sendKeys(pass);
        return this;
    }

    @Step("Нажать на кнопку 'Sign in'")
    public final LeftMenuComponent clickToSignInButton() {
        signInButton.click();
        return new LeftMenuComponent(driver);
    }

    @Step("Войти под логином {login}")
    public final LeftMenuComponent login(String login, String pass) {
        loginInput.sendKeys(login);
        passwordInput.sendKeys(pass);
        signInButton.click();
        return new LeftMenuComponent(driver);
    }
}
