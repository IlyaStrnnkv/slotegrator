package ui.base;

import io.qameta.allure.Step;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

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
    public final boolean checkPageIsPresentByUrl(String url) {
        try {
            WebDriverWait webDriverWait = new WebDriverWait(driver, 15);
            webDriverWait.until(ExpectedConditions.urlContains(url));
        } catch (TimeoutException e) {
            Assert.fail("It's the wrong page");
            return false;
        }
        return true;
    }
}
