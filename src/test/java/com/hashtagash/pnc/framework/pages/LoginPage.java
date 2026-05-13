package com.hashtagash.pnc.framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
    // Resilient locators: prefer stable IDs/data-test attributes over brittle XPath.
    private final By username = By.cssSelector("[data-test='username']");
    private final By password = By.cssSelector("[data-test='password']");
    private final By loginButton = By.cssSelector("[data-test='login-button']");
    private final By error = By.cssSelector("[data-test='error']");
    public LoginPage(WebDriver driver) { super(driver); }
    public void login(String user, String pass) { type(username, user); type(password, pass); click(loginButton); }
    public String errorMessage() { return text(error); }
}
