package Chrome;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.*;

import static org.junit.Assert.assertTrue;

public class LogoutTest {

    private WebDriver driver;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup(); // автозагрузка драйвера
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    @Description("Логин и логаут с проверкой что поле личный кабинет ведёт на форму логина")
    public void logoutTest() {
        driver.get("https://stellarburgers.nomoreparties.site/login");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginUser("SeleniumTestUser12365@yandex.ru", "password");

        ConstructorPage constructorPage = new ConstructorPage(driver);
        constructorPage.waitUntilPageLoaded();
        constructorPage.clickPersonalAccount();

        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.waitUntilPageLoaded();
        assertTrue("Страница профиля загружена", profilePage.isProfilePageLoaded());
        profilePage.clickLogout();
        constructorPage.waitUntilPageLoaded();
        constructorPage.clickPersonalAccount();
        assertTrue("Страница логин загружена после выхода из аккаунта", loginPage.isLoginPageLoaded());
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
