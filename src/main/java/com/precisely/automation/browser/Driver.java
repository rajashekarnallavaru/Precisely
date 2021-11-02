package com.precisely.automation.browser;

import com.precisely.automation.helpers.Util;
import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import net.lightbody.bmp.BrowserMobProxy;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import static com.precisely.automation.helpers.Util.takeSnapShot;

public class Driver extends EventFiringWebDriver {
    Util util = new Util();
    public static String headless = System.getProperty("headlessBrowser");
    static String browserName = System.getProperty("browserName").toLowerCase();
    public static boolean screenShotFlag = false;
    public static boolean fullPageScreenShot = false;
    public static boolean currentUrl = false;
    public static boolean firstDriverInstance = true;
    public static String exceptionMsg = "";

    public static BrowserMobProxy proxy;

    public static WebDriver driver = setBrowser();
    public static final Logger LOGGER = Logger.getLogger(Driver.class.getName());
    public static final Thread CLOSE_THREAD = new Thread() {
        @Override
        public void run() {
            driver.quit();
        }
    };

    static {
        Runtime.getRuntime().addShutdownHook(CLOSE_THREAD);
    }

    public Driver() {
        super(driver);
    }

    public static WebDriver setBrowser() {

        DesiredCapabilities capability;
            switch (browserName) {
                case "chrome":
                    System.out.println("Tests are running on Chrome in PC mode.");
                    driver = new ChromeDriver(chromeOptions());
                    break;

                case "firefox":
                    System.out.println("Tests are running on Firefox in PC mode.");
                    driver = new FirefoxDriver(firefoxOptions());
                    break;

                default:
                    System.out.println("Tests are running on Chrome in PC mode.");
                    driver = new ChromeDriver(chromeOptions());
                    break;
            }
        driver.manage().timeouts().pageLoadTimeout(420, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(420, TimeUnit.SECONDS);
        return driver;
    }

    @Before()
    public void launchBrowser() {
        if (firstDriverInstance) {
            firstDriverInstance = false;
            driver.manage().deleteAllCookies();
        } else {
            driver.quit();
            driver = setBrowser();
            driver.manage().deleteAllCookies();
        }
    }

    @After()
    public void screenCapture(Scenario scenario) throws Exception {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Instant now = timestamp.toInstant();
        Instant twoMinsBefore = timestamp.toInstant().minusSeconds(120);
        String fileName = scenario.getId();
        String dest = "";

        if (scenario.isFailed()) {
            LOGGER.info("TEST FAILED");

            //Check if there is locator not found exception, if yes, then capture full screen shot
            if (Driver.exceptionMsg.contains("waiting for condition")) {
                if (System.getenv("BUILD_URL") != null) {
                    scenario.embed(util.takeFullPageScreenShotAsByte(driver, false), "image/jpeg");
                } else {
                    scenario.embed(util.takeFullPageScreenShotAsByte(driver, true), "image/jpeg");
                }
                scenario.embed(driver.getCurrentUrl().getBytes(), "text/plain");
                Driver.exceptionMsg = "";
            }
            //Assertion failure, then normal screenshot (No full page screenshot)
            takeSnapShot(driver, "target/testresults/" + scenario.getStatus() + "/", fileName.replace(" ", ""));
            //Writes the file name here so it shows up in the logs
            scenario.write(fileName + ".jpg");
            scenario.embed(driver.getCurrentUrl().getBytes(), "text/plain");
            scenario.embed(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES), "image/png");
        } else {
            LOGGER.info("TEST PASSED");
        }

    }


    @AfterStep()
    public void afterEveryStep(Scenario scenario) throws Exception {
        String fileName = scenario.getId();
        if (currentUrl == true) {
            scenario.embed(driver.getCurrentUrl().getBytes(), "text/plain");
        }
        currentUrl = false;
        if (screenShotFlag == true) {
            takeSnapShot(driver, "target/testresults/" + scenario.getStatus() + "/", fileName.replace(" ", ""));
            scenario.embed(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES), "image/png");
        }
        screenShotFlag = false;
        if (fullPageScreenShot == true){
            if (System.getenv("BUILD_URL") != null) {
                scenario.embed(util.takeFullPageScreenShotAsByte(driver, false), "image/jpeg");
            } else {
                scenario.embed(util.takeFullPageScreenShotAsByte(driver, true), "image/jpeg");
            }
        }
        fullPageScreenShot = false;
    }

    @Override
    public void close() {
        if (Thread.currentThread() != CLOSE_THREAD) {
            throw new UnsupportedOperationException(
                    "You shouldn't close this WebDriver. " + "It would close when the JVM exits");
        }
        super.quit();
    }

    public static ChromeOptions chromeOptions() {
        ChromeOptions options = new ChromeOptions();
        if (headless.contentEquals("True")) {
            options.addArguments("--headless");
            options.addArguments("--window-size=1920,1080");
            options.addArguments("--disable-extensions");
            options.addArguments("--no-sandbox");
            options.addArguments("--ignore-certificate-errors");
            options.addArguments("--disable-popup-blocking");
            options.setCapability("acceptSslCerts", "true");
            options.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.DISMISS);
            options.setCapability(CapabilityType.UNHANDLED_PROMPT_BEHAVIOUR, UnexpectedAlertBehaviour.DISMISS);
        }
        return options;
    }

    public static FirefoxOptions firefoxOptions() {
        FirefoxOptions options = new FirefoxOptions();
        if (headless.contentEquals("True")) {
            options.addArguments("--headless");
            options.addArguments("--window-size=1920,1080");
            options.addArguments("--disable-extensions");
            options.addArguments("--no-sandbox");
            options.setCapability("acceptSslCerts", "true");
            options.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.DISMISS);
            options.setCapability(CapabilityType.UNHANDLED_PROMPT_BEHAVIOUR, UnexpectedAlertBehaviour.DISMISS);
        } else {
            System.out.println("Firefox is not headless.");
        }
        return options;
    }


}