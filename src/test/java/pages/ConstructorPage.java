package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class ConstructorPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By personalAccountButton = By.xpath("//*[text()[contains(.,'Личный Кабинет')]]");
    private By enterAccountButton = By.xpath("//*[text()[contains(.,'Войти в аккаунт')]]");

    private By bunsButton = By.xpath("//span[text()='Булки']");
    private By bunsHeader = By.xpath("//h2[text()='Булки']");
    private By saucesButton = By.xpath("//span[text()='Соусы']");
    private By saucesHeader = By.xpath("//h2[text()='Соусы']");
    private By fillingsButton = By.xpath("//span[text()='Начинки']");
    private By fillingsHeader = By.xpath("//h2[text()='Начинки']");

    public ConstructorPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(3));
    }

    public void clickPersonalAccount() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(personalAccountButton));
        button.click();
    }

    public void clickEnterAccount() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(enterAccountButton));
        button.click();
    }

    public void waitUntilPageLoaded() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(personalAccountButton));
    }

    public boolean isConstructorPageLoaded() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        return wait.until(ExpectedConditions.urlToBe("https://stellarburgers.nomoreparties.site/"));
    }

    public void clickBuns() {
        wait.until(ExpectedConditions.elementToBeClickable(bunsButton)).click();
    }

    public boolean isBunsHeaderDisplayed() {
        try {
            wait.withTimeout(Duration.ofSeconds(3))
                    .until(ExpectedConditions.visibilityOfElementLocated(bunsHeader));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void clickSauces() {
        wait.until(ExpectedConditions.elementToBeClickable(saucesButton)).click();
    }

    public boolean isSaucesHeaderDisplayed() {
        try {
            wait.withTimeout(Duration.ofSeconds(3))
                    .until(ExpectedConditions.visibilityOfElementLocated(saucesHeader));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void clickFillings() {
        wait.until(ExpectedConditions.elementToBeClickable(fillingsButton)).click();
    }

    public boolean isFillingsHeaderDisplayed() {
        try {
            wait.withTimeout(Duration.ofSeconds(3))
                    .until(ExpectedConditions.visibilityOfElementLocated(fillingsHeader));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
