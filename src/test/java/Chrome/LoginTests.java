package Chrome;

import io.qameta.allure.Description;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.*;

import static org.junit.Assert.assertTrue;

public class LoginTests {

    private WebDriver driver;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    @Description("Переход на логин по кнопке личный кабинет и логин")
    public void personalAccountLoginTest() {
        driver.get("https://stellarburgers.nomoreparties.site/");
        ConstructorPage constructorPage = new ConstructorPage(driver);
        constructorPage.waitUntilPageLoaded();
        constructorPage.clickPersonalAccount();

        LoginPage loginPage = new LoginPage(driver);
        assertTrue("Страница логин загружена", loginPage.isLoginPageLoaded());
        loginPage.loginUser("SeleniumTestUser12365@yandex.ru", "password");
        constructorPage.waitUntilPageLoaded();
        constructorPage.clickPersonalAccount();

        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.waitUntilPageLoaded();
        assertTrue("Страница профиля загружена", profilePage.isProfilePageLoaded());
    }


    @Test
    @Description("Переход на логин по кнопке войти в аккаунт и логин")
    public void enterAccountLoginTest() {
        driver.get("https://stellarburgers.nomoreparties.site/");
        ConstructorPage constructorPage = new ConstructorPage(driver);
        constructorPage.waitUntilPageLoaded();
        constructorPage.clickEnterAccount();

        LoginPage loginPage = new LoginPage(driver);
        assertTrue("Страница логин загружена", loginPage.isLoginPageLoaded());
        loginPage.loginUser("SeleniumTestUser12365@yandex.ru", "password");
        constructorPage.waitUntilPageLoaded();
        constructorPage.clickPersonalAccount();

        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.waitUntilPageLoaded();
        assertTrue("Страница профиля загружена", profilePage.isProfilePageLoaded());
    }

    @Test
    @Description("Переход на логин с формы регистрации и логин")
    public void registerPageLoginTest() {
        driver.get("https://stellarburgers.nomoreparties.site/register");
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.waitUntilPageLoaded();
        registerPage.clickEnterButton();

        LoginPage loginPage = new LoginPage(driver);
        assertTrue("Страница логин загружена", loginPage.isLoginPageLoaded());
        loginPage.loginUser("SeleniumTestUser12365@yandex.ru", "password");
        ConstructorPage constructorPage = new ConstructorPage(driver);
        constructorPage.waitUntilPageLoaded();
        constructorPage.clickPersonalAccount();

        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.waitUntilPageLoaded();
        assertTrue("Страница профиля загружена", profilePage.isProfilePageLoaded());
    }

    @Test
    @Description("Переход на логин с формы восстановления пароля и логин")
    public void forgotPasswordPageLoginTest() {
        driver.get("https://stellarburgers.nomoreparties.site/forgot-password");
        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);
        forgotPasswordPage.waitUntilPageLoaded();
        forgotPasswordPage.clickEnterButton();

        LoginPage loginPage = new LoginPage(driver);
        assertTrue("Страница логин загружена", loginPage.isLoginPageLoaded());
        loginPage.loginUser("SeleniumTestUser12365@yandex.ru", "password");
        ConstructorPage constructorPage = new ConstructorPage(driver);
        constructorPage.waitUntilPageLoaded();
        constructorPage.clickPersonalAccount();

        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.waitUntilPageLoaded();
        assertTrue("Страница профиля загружена", profilePage.isProfilePageLoaded());
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
