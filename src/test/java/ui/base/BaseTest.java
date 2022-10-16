package ui.base;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import java.util.Properties;

import static ui.helpers.DriverFactory.createDriver;
import static ui.helpers.ParametersProvider.getPropertiesXml;
import static ui.data.DefaultTestSetupData.BASE_URL;

@Listeners(ui.listeners.Listener.class)
public class BaseTest {

    /**
     * Web driver
     */
    protected WebDriver driver;

    @BeforeMethod
    public final void setUp(ITestContext context) {
        Properties properties = getPropertiesXml();
        driver = createDriver("chrome");
        driver.get(properties.getProperty("baseUrl", BASE_URL));
        context.setAttribute("WebDriver", driver);
    }

    @AfterMethod
    public final void tearDown() {
        driver.quit();
    }
}
