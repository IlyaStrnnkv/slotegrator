package ui.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import ui.base.BasePage;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class PlayersPage extends BasePage {

    /**
     * Page url.
     */
    private static final String PAGE_URL = "/user/player/admin";

    @FindBy(xpath = "//option[@selected='selected']")
    private WebElement numberOfTableLines;

    @FindBy(xpath = "//a[text()='Status']")
    private WebElement statusHeader;

    @FindBy(xpath = "//a[@href='/user/player/create']")
    private WebElement createPlayerButton;

    @FindBy(xpath = "//tbody/tr/td[15]/span")
    private List<WebElement> statusList;

    public PlayersPage(WebDriver driver) {
        super(driver);
        checkPageIsPresentByUrl(PAGE_URL);
    }

    @Step("Проверить открытие страницы 'Список игроков'")
    public final PlayersPage checkPlayersPageIsOpen() {
        waitElementIsPresent(createPlayerButton);
        return this;
    }

    @Step("Проверить сортировку в столбце статус")
    public final PlayersPage checkSortStatusColumn() {
        sortAndCheckSuccessful(statusList, statusHeader, (String status1, String status2) -> {
            if (status1.equals("Not active") || status2.equals("Not active")) {
                return -1;
            }
            return status1.compareTo(status2);
        });
        return this;
    }

    @Step("Сортировать по столбцу")
    private final PlayersPage sortAndCheckSuccessful(List<WebElement> elements,
                                                       WebElement header, Comparator<? super String> comparator) {
        waitElementIsPresent(numberOfTableLines);
        waitCollectionSize(elements, Integer.parseInt(numberOfTableLines.getText()));
        List<String> nativeSort = elements.stream()
            .map(WebElement::getText)
            .sorted(comparator)
            .collect(Collectors.toList());

        header.click();

        List<String> uiSort = elements
            .stream()
            .map(WebElement::getText)
            .collect(Collectors.toList());

        Assert.assertEquals(nativeSort, uiSort);
        return this;
    }

    @Step("Нажать на хедер 'Status'")
    public final PlayersPage clickToStatusHeader() {
        waitAndClick(statusHeader);
        return this;
    }

//    @Step("Проверить сортировку столбца 'Status'")
//    public final PlayersPage checkSortByStatusColumn() {
//        Comparator comparator = new Comparator() {
//            @Override
//            public int compare(Object status1, Object status2) {
//                if (status1.toString().equals("Not active") || status2.toString().equals("Not active")) {
//                    return -1;
//                }
//                return 0;
//            }
//        };
//        List<String> nativeSort = (List<String>) statusList.stream()
//            .map(WebElement::getText)
//            .sorted(comparator)
//            .collect(Collectors.toList());
//        List<String> uiSort = statusList
//            .stream()
//            .map(WebElement::getText)
//            .collect(Collectors.toList());
//        Assert.assertEquals(uiSort, nativeSort);
//        return this;
//    }
}
