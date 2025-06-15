package Yandex;

import io.qameta.allure.Description;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.ConstructorPage;
import pages.LoginPage;
import pages.ProfilePage;

import static org.junit.Assert.assertTrue;

public class LogoutTest {

    private WebDriver driver;

    @Before
    public void setUp() { //необходимо поменять ссылки на драйвер и браузер
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Public\\yandexdriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.setBinary("C:\\Users\\Денис\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe");
        driver = new ChromeDriver(options);
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
