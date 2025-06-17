package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class RegisterPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By enterButton = By.xpath("//*[text()[contains(.,'Войти')]]");
    private By nameInput = By.xpath("//label[text()='Имя']/following-sibling::input");
    private By emailInput = By.xpath("//label[text()='Email']/following-sibling::input");
    private By passwordInput = By.xpath("//input[@type='password' and @name='Пароль']");
    private By registerButton = By.xpath("//*[text()[contains(.,'Зарегистрироваться')]]");
    private By passwordError = By.xpath("//p[contains(@class, 'input__error') and text()='Некорректный пароль']");


    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(3));
    }

    public void clickEnterButton() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(enterButton));
        button.click();
    }

    public void waitUntilPageLoaded() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(enterButton));
    }

    public void enterName(String name) {
        WebElement nameField = wait.until(ExpectedConditions.visibilityOfElementLocated(nameInput));
        nameField.clear();
        nameField.sendKeys(name);
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

    public void clickRegisterButton() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(registerButton));
        button.click();
    }

    public void registerUser(String name, String email, String password) {
        enterName(name);
        enterEmail(email);
        enterPassword(password);
        clickRegisterButton();
    }

    public boolean isPasswordErrorDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(passwordError)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

}
