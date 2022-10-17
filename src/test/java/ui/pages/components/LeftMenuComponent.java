package ui.pages.components;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ui.base.BasePage;
import ui.pages.PlayersPage;

public class LeftMenuComponent extends BasePage {

    /**
     * Page url.
     */
    private static final String PAGE_URL = "configurator/dashboard/index";

    @FindBy(xpath = "//a[contains(@data-target, 'users')]")
    private WebElement usersSection;

    @FindBy(xpath = "//li[@class='active']//a[@href='/user/player/admin']")
    private WebElement playersSubsection;

    /**
     * Page object constructor. Checks that page is open when created.
     * Init elements
     * @param driver web driver
     */
    public LeftMenuComponent(WebDriver driver) {
        super(driver);
        checkPageIsPresentByUrl(PAGE_URL);
    }

    @Step("Проверить что авторизация успешна по уникальному элементу на странице")
    public final LeftMenuComponent checkAuthorizationSuccessful() {
        waitElementIsPresent(usersSection);
        return this;
    }

    @Step("Нажать на раздел 'Users'")
    public final LeftMenuComponent clickToUsersSection() {
        usersSection.click();
        return this;
    }

    @Step("Нажать на подраздел 'Players' раздела 'Users'")
    public final PlayersPage clickToPlayersSubsection() {
        waitAndClick(playersSubsection);
        return new PlayersPage(driver);
    }


}
