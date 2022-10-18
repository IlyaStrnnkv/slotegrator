package ui.tests;

import io.qameta.allure.*;
import org.testng.annotations.Test;
import ui.base.BaseTest;
import ui.pages.LoginPage;

import static ui.data.AuthorizationData.CORRECT_ADMIN_LOGIN;
import static ui.data.AuthorizationData.CORRECT_ADMIN_PASSWORD;

@Epic("Casino")
@Feature("Список игроков")
public class PlayersListTest extends BaseTest {

    @Story("Проверить открытие страницы 'Список игроков'")
    @Test(description = "Проверить открытие страницы 'Список игроков'")
    @Severity(SeverityLevel.CRITICAL)
    public final void checkOpenPlayersList() {
        new LoginPage(driver)
            .login(CORRECT_ADMIN_LOGIN, CORRECT_ADMIN_PASSWORD)
            .checkAuthorizationSuccessful()
            .clickToUsersSection()
            .clickToPlayersSubsection()
            .checkPlayersPageIsOpen();
    }

    @Story("Проверка сортировки по столбцу 'Статус'")
    @Test(description = "Проверка сортировки по столбцу 'Статус'")
    @Severity(SeverityLevel.NORMAL)
    public final void checkSort() {
        new LoginPage(driver)
            .login(CORRECT_ADMIN_LOGIN, CORRECT_ADMIN_PASSWORD)
            .checkAuthorizationSuccessful()
            .clickToUsersSection()
            .clickToPlayersSubsection()
            .checkSortStatusColumn();
    }
}
