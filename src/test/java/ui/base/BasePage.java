package ui.base;

import io.qameta.allure.Step;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.util.List;

import static ui.data.DefaultTestSetupData.TIMEOUT;

/**
 * Default test data class
 */
public class BasePage {

    /**
     * Web driver
     */
    protected WebDriver driver;

    /**
     * Page object constructor.
     * Init elements
     * @param driver web driver
     */
    public BasePage (WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Check that page is present by url
     * @param url current page's url
     * @return true - page is correct, false - page is wrong
     */
    @Step("Проверка что открылась правильная страница")
    public final void checkPageIsPresentByUrl(String url) {
        try {
            WebDriverWait webDriverWait = new WebDriverWait(driver, Integer.parseInt(TIMEOUT));
            webDriverWait.until(ExpectedConditions.urlContains(url));
        } catch (TimeoutException e) {
            Assert.fail("It's the wrong page");
        }
    }

    /**
     * Wait until element is present
     * @param element which we wait to appeared
     * @return true - if element is present, false - if element wasn't appeared
     */
    @Step("Ожидание пока элемент появится")
    public final void waitElementIsPresent(WebElement element) {
        try {
            WebDriverWait webDriverWait = new WebDriverWait(driver, Integer.parseInt(TIMEOUT));
            webDriverWait.until(ExpectedConditions.visibilityOf(element)).isDisplayed();
        } catch (TimeoutException e) {
            Assert.fail("Элемент не найден");
        }
    }

    /**
     * Дождаться когда элемент появится и кликнуть по нему
     * @param element который ждем
     */
    @Step("Ожидание элемента и клик по нему")
    public final void waitAndClick(WebElement element) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, Integer.parseInt(TIMEOUT));
        webDriverWait.until(ExpectedConditions.visibilityOf(element)).isDisplayed();
        element.click();
    }

    @Step("Ожидание размера коллекции {size}")
    public final void waitCollectionSize(List<WebElement> elements, int size) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, Integer.parseInt(TIMEOUT));
        webDriverWait.until(ExpectedConditions.visibilityOfAllElements(elements));
        Assert.assertEquals(elements.size(), size);
    }
}
