package com.precisely.automation.helpers;

import com.precisely.automation.browser.Driver;
import com.precisely.automation.constants.Constants;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public class Util {

    private WebDriver webDriver = Driver.driver;

    /**
     * Take a Snapshot using WebDriver and save to file
     *
     * @param fileWithPath
     *            Where to same file with .jpg extension
     *
     * @param featureFileName
     *            Name of file
     */
    public static void takeSnapShot(WebDriver webdriver, String fileWithPath, String featureFileName) throws Exception {
        TakesScreenshot scrShot = ((TakesScreenshot) webdriver);
        File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
        File DestFile = new File(fileWithPath + featureFileName + ".jpg");
        FileUtils.copyFile(SrcFile, DestFile);

    }

    /**
     * Returns byte array of full page screen shot
     *
     * @param webDriver WebDriver
     * @param local boolean - if running on local machine then true else false
     * @return byte array
     * @throws IOException
     */
    public byte[] takeFullPageScreenShotAsByte(WebDriver webDriver, boolean local) throws IOException {
        Screenshot fpScreenshot;
        if (local) {
            // if local and no scaling captures only left half of page
            fpScreenshot = new AShot()
                    .shootingStrategy(ShootingStrategies.viewportPasting(ShootingStrategies.scaling(2f), 1000))
                    .takeScreenshot(webDriver);
        } else {
            // if jenkins and we try using local instance (with scaling) then every second capture is blank screen
            fpScreenshot = new AShot()
                    .shootingStrategy(ShootingStrategies.viewportPasting(1000))
                    .takeScreenshot(webDriver);
        }

        BufferedImage originalImage = fpScreenshot.getImage();

        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            ImageIO.write(originalImage, "jpg", baos);
            baos.flush();
            return baos.toByteArray();
        }
    }

    public WebDriver getDriver() {
        return this.webDriver;
    }

    public void navigateToUrl(final String url) {
        getDriver().navigate().to(url);
    }

    private static void waitFor(final int waitTime) {
        try {
            Thread.sleep(waitTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param waitTime
     *            How many seconds should pause
     */
    public static void pause(final Constants.WaitingTime waitTime) {

        switch (waitTime) {
            case QUARTER_SECOND:
                waitFor(Constants.WAIT_FOR_250MS);
                break;
            case HALF_SECOND:
                waitFor(Constants.WAIT_FOR_500MS);
                break;
            case SECOND:
                waitFor(Constants.WAIT_FOR_1000MS);
                break;
            case SHORT:
                waitFor(Constants.WAIT_FOR_2000MS);
                break;
            case MEDIUM:
                waitFor(Constants.WAIT_FOR_4000MS);
                break;
            case LONG:
                waitFor(Constants.WAIT_FOR_7000MS);
                break;
            default:
                System.out.println("ERROR: invalid waiting time(" + waitTime + "). Please enter one of SHORT, MEDIUM, or LONG");
                break;
        }

    }
}
