package com.hashtagash.pnc.framework.core;

import com.hashtagash.pnc.framework.config.Config;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public final class DriverFactory {
    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();
    private DriverFactory() {}
    public static WebDriver getDriver() {
        if (DRIVER.get() == null) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            if (Config.getBool("headless")) options.addArguments("--headless=new");
            options.addArguments("--window-size=1440,1000", "--disable-gpu", "--no-sandbox");
            DRIVER.set(new ChromeDriver(options));
        }
        return DRIVER.get();
    }
    public static void quit() {
        WebDriver driver = DRIVER.get();
        if (driver != null) { driver.quit(); DRIVER.remove(); }
    }
}
