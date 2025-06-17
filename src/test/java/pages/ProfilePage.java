package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ProfilePage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By profileText = By.xpath("//*[text()[contains(.,'Профиль')]]");
    private By logoutButton = By.xpath("//button[text()='Выход']");

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(3));
    }

    public void waitUntilPageLoaded() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(profileText));
    }

    public boolean isProfilePageLoaded() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        return wait.until(ExpectedConditions.and(
                ExpectedConditions.urlToBe("https://stellarburgers.nomoreparties.site/account/profile"),
                ExpectedConditions.visibilityOfElementLocated(profileText)
        )) != null;
    }

    public void clickLogout() {
        wait.until(ExpectedConditions.elementToBeClickable(logoutButton)).click();
    }
}
