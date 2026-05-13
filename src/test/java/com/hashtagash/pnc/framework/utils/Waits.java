package com.hashtagash.pnc.framework.utils;

import com.hashtagash.pnc.framework.config.Config;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public final class Waits {
    private Waits() {}
    public static WebElement visible(WebDriver driver, By locator) {
        return explicit(driver).until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    public static WebElement clickable(WebDriver driver, By locator) {
        return explicit(driver).until(ExpectedConditions.elementToBeClickable(locator));
    }
    public static boolean quickVisible(WebDriver driver, By locator) {
        try { quick(driver).until(ExpectedConditions.visibilityOfElementLocated(locator)); return true; }
        catch (TimeoutException e) { return false; }
    }
    private static WebDriverWait explicit(WebDriver driver) {
        return new WebDriverWait(driver, Duration.ofSeconds(Config.getInt("timeout.explicit.seconds")));
    }
    private static WebDriverWait quick(WebDriver driver) {
        return new WebDriverWait(driver, Duration.ofSeconds(Config.getInt("timeout.quick.seconds")));
    }
}
