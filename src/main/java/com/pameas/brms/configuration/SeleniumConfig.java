package com.pameas.brms.configuration;


import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.concurrent.TimeUnit;

public class SeleniumConfig {

    private final WebDriver driver;

    public SeleniumConfig(String driverPath) {
        System.setProperty("webdriver.chrome.driver", driverPath);
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("use-fake-ui-for-media-stream");
        chromeOptions.addArguments(("--start-maximized"));
        chromeOptions.addArguments("--no-sandbox");
        capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        driver = new ChromeDriver(capabilities);
        driver.manage()
                .timeouts()
                .implicitlyWait(5, TimeUnit.SECONDS);
    }

    public void close() {
        driver.close();
    }

    public void navigateTo(String url) {
        driver.navigate()
                .to(url);
    }

    public void clickElement(WebElement element) {
        element.click();
    }

    public WebDriver getDriver() {
        return driver;
    }
}
