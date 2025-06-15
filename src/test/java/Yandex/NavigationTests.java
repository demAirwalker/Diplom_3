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

import static org.junit.Assert.assertTrue;

public class NavigationTests {

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
    @Description("Переход с конструктора в логин")
    public void navConstructorToLoginTest() {
        driver.get("https://stellarburgers.nomoreparties.site/");
        ConstructorPage constructorPage = new ConstructorPage(driver);
        constructorPage.waitUntilPageLoaded();
        constructorPage.clickEnterAccount();

        LoginPage loginPage = new LoginPage(driver);
        assertTrue("Страница логин загружена", loginPage.isLoginPageLoaded());
    }

    @Test
    @Description("Переход с логина в конструктор")
    public void navLoginToConstructorTest() {
        driver.get("https://stellarburgers.nomoreparties.site/login");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickConstructorButton();

        ConstructorPage constructorPage = new ConstructorPage(driver);
        constructorPage.waitUntilPageLoaded();
        assertTrue("Страница конструктор загружена", constructorPage.isConstructorPageLoaded());
    }

    @Test
    @Description("Проверка кнопок навигации конструктора")
    public void navConstructorTest() {
        driver.get("https://stellarburgers.nomoreparties.site/");
        ConstructorPage constructorPage = new ConstructorPage(driver);
        constructorPage.waitUntilPageLoaded();
        constructorPage.clickSauces();
        assertTrue("Поле Соусы на экране", constructorPage.isSaucesHeaderDisplayed());
        constructorPage.clickFillings();
        assertTrue("Поле Начинки на экране", constructorPage.isFillingsHeaderDisplayed());
        constructorPage.clickBuns();
        assertTrue("Поле Булки на экране", constructorPage.isBunsHeaderDisplayed());
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}