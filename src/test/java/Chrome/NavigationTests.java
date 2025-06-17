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

public class NavigationTests {

    private WebDriver driver;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup(); // автозагрузка драйвера
        driver = new ChromeDriver();
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
    @Description("Проверка кнопки Соусы")
    public void navConstructorSaucesTest() {
        driver.get("https://stellarburgers.nomoreparties.site/");
        ConstructorPage constructorPage = new ConstructorPage(driver);
        constructorPage.waitUntilPageLoaded();
        constructorPage.clickSauces();
        assertTrue("Кнопка Соусы была нажата", constructorPage.isSaucesHeaderDisplayed());
    }

    @Test
    @Description("Проверка кнопки Начинки")
    public void navConstructorFillingsTest() {
        driver.get("https://stellarburgers.nomoreparties.site/");
        ConstructorPage constructorPage = new ConstructorPage(driver);
        constructorPage.waitUntilPageLoaded();
        constructorPage.clickFillings();
        assertTrue("Кнопка Начинки была нажата", constructorPage.isFillingsHeaderDisplayed());
    }

    @Test
    @Description("Проверка кнопки Булки")
    public void navConstructorBunsTest() {
        driver.get("https://stellarburgers.nomoreparties.site/");
        ConstructorPage constructorPage = new ConstructorPage(driver);
        constructorPage.waitUntilPageLoaded();
        constructorPage.clickSauces();
        constructorPage.clickBuns();
        assertTrue("Кнопка Булки была нажата", constructorPage.isBunsHeaderDisplayed());
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}