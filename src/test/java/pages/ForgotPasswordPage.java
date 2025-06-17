package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ForgotPasswordPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By enterButton = By.xpath("//*[text()[contains(.,'Войти')]]");

    public ForgotPasswordPage(WebDriver driver) {
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
}
