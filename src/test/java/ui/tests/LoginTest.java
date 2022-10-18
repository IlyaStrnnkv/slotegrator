package ui.tests;

import io.qameta.allure.*;
import org.testng.annotations.Test;
import ui.base.BaseTest;
import ui.pages.LoginPage;

import static ui.data.AuthorizationData.CORRECT_ADMIN_LOGIN;
import static ui.data.AuthorizationData.CORRECT_ADMIN_PASSWORD;

@Epic("Casino")
@Feature("Авторизация")
public class LoginTest extends BaseTest {

    @Story("Успешная авторизация под администратором")
    @Test(description = "Успешная авторизация под администратором")
    @Severity(SeverityLevel.BLOCKER)
    public final void checkSuccessfulAuthByAdmin() {
        new LoginPage(driver)
            .inputLogin(CORRECT_ADMIN_LOGIN)
            .inputPassword(CORRECT_ADMIN_PASSWORD)
            .clickToSignInButton()
            .checkAuthorizationSuccessful();
    }
}
