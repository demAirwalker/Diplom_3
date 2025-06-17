package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private By emailInput = By.xpath("//input[@name='name']");    // поле Email
    private By passwordInput = By.xpath("//input[@type='password' and @name='Пароль']");
    private By enterButton = By.xpath("//button[text()='Войти']");
    private By constructorButton = By.xpath("//*[text()[contains(.,'Конструктор')]]");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(3));
    }

    public boolean isLoginPageLoaded() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        return wait.until(ExpectedConditions.and(
                ExpectedConditions.urlToBe("https://stellarburgers.nomoreparties.site/login"),
                ExpectedConditions.visibilityOfElementLocated(enterButton)
        )) != null;
    }

    public void enterEmail(String email) {
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(emailInput));
        emailField.clear();
        emailField.sendKeys(email);
    }

    public void enterPassword(String password) {
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInput));
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void clickLoginButton() {
        WebElement loginBtn = wait.until(ExpectedConditions.elementToBeClickable(enterButton));
        loginBtn.click();
    }

    public void loginUser(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickLoginButton();
    }

    public void clickConstructorButton() {
        WebElement constructorBtn = wait.until(ExpectedConditions.elementToBeClickable(constructorButton));
        constructorBtn.click();
    }
}
