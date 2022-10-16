package ui.helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.safari.SafariDriver;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static java.lang.Integer.parseInt;
import static ui.data.DefaultTestSetupData.PAGE_LOAD_TIMEOUT;
import static ui.data.DefaultTestSetupData.TIMEOUT;

public class DriverFactory {

    public static WebDriver createDriver(String browserName) {
        Properties properties = ParametersProvider.getPropertiesXml();
        int pageLoadTimeout = parseInt(properties.getProperty("pageLoadTimeout", PAGE_LOAD_TIMEOUT));
        int timeout = parseInt(properties.getProperty("timeout", TIMEOUT));
        WebDriver driver = getDriver(browserName);
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(pageLoadTimeout, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
        return driver;
    }

    /**
     * Get driver
     * @param browserName browser name
     */
    private static WebDriver getDriver(String browserName) {
        switch (browserName){
            case "chrome":
                return new ChromeDriver();
            case "opera":
                return new OperaDriver();
            case "firefox":
                return new FirefoxDriver();
            case "edge":
                return new EdgeDriver();
            case "ie":
                return new InternetExplorerDriver();
            case "safari":
                return new SafariDriver();
            default:
                throw new IllegalStateException("Chosen browser not supported");
        }
    }
}
