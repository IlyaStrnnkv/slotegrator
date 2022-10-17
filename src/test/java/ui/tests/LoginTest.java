package ui.tests;

import io.qameta.allure.*;
import org.testng.annotations.Test;
import ui.base.BaseTest;
import ui.pages.LoginPage;

@Epic("Casino")
@Feature("Авторизация")
public class LoginTest extends BaseTest {

    @Story("Успешная авторизация под администратором")
    @Test(description = "Успешная авторизация под администратором")
    @Severity(SeverityLevel.BLOCKER)
    public final void checkSuccessfulAuthByAdmin() {
        new LoginPage(driver)
            .inputLogin("admin1")
            .inputPassword("[9k<k8^z!+$$GkuP")
            .clickToSignInButton()
            .checkAuthorizationSuccessful();
    }
}
