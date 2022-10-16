package ui.listeners;

import io.qameta.allure.Attachment;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;

import javax.imageio.ImageIO;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class Listener implements ITestListener {

    /**
     * Browser driver.
     */
    private WebDriver driver;

    /**
     * Take screenshot of the page
     */
    @Attachment(value = "{}", type = "image/png")
    public byte[] screenshot(ITestResult res) {
        driver = (WebDriver) res.getTestContext().getAttribute("WebDriver");
        try {
            Screenshot screenshot = new AShot().takeScreenshot(driver);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(screenshot.getImage(), "png", baos);
            return baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "error screenshot".getBytes();
    }

    /**
     * If test skip or fail
     */
    @Override
    public void onTestFailure(ITestResult res) {
        if (res.getStatus() == 2 || res.getStatus() == 3){
            screenshot(res);
        }
    }
}
