package com.hashtagash.pnc.framework.pages;

import com.hashtagash.pnc.framework.utils.Waits;
import org.openqa.selenium.*;

public abstract class BasePage {
    protected final WebDriver driver;
    protected BasePage(WebDriver driver) { this.driver = driver; }
    protected void type(By locator, String text) { WebElement e = Waits.visible(driver, locator); e.clear(); e.sendKeys(text); }
    protected void click(By locator) { Waits.clickable(driver, locator).click(); }
    protected String text(By locator) { return Waits.visible(driver, locator).getText(); }
}
