package Chrome;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.*;
import java.util.Random;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertTrue;

public class RegisterTests {

    private WebDriver driver;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup(); // автозагрузка драйвера
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    @Description("Регистрация нового пользователя с последующим удалением по API")
    public void registerNewUserTest() {
        driver.get("https://stellarburgers.nomoreparties.site/register");
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.waitUntilPageLoaded();

        Random random = new Random();
        int randomNumber = 1000000 + random.nextInt(9000000);
        String name = "SeleniumTestUser" + randomNumber;
        String email = name + "@yandex.ru";
        registerPage.registerUser(name, email, "password");

        LoginPage loginPage = new LoginPage(driver);
        assertTrue("Страница логин загружена", loginPage.isLoginPageLoaded());
        loginPage.loginUser(email, "password");
        ConstructorPage constructorPage = new ConstructorPage(driver);
        constructorPage.waitUntilPageLoaded();
        constructorPage.clickPersonalAccount();

        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.waitUntilPageLoaded();
        assertTrue("Страница профиля загружена", profilePage.isProfilePageLoaded());

        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site";
        Response response = given()
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "  \"email\": \"" + email + "\",\n" +
                        "  \"password\": \"password\"\n" +
                        "}")
                .when()
                .post("/api/auth/login");
        String accessToken = response.jsonPath().getString("accessToken");
        given()
                .header("Authorization", accessToken)
                .when()
                .delete("/api/auth/user")
                .then()
                .statusCode(202);
    }

    @Test
    @Description("Проверка отображения ошибки пароля")
    public void errorShortPasswordTest() {
        driver.get("https://stellarburgers.nomoreparties.site/register");
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.waitUntilPageLoaded();
        registerPage.enterPassword("123");
        registerPage.clickRegisterButton();
        assertTrue("Ошибка некорректного пароля отображается", registerPage.isPasswordErrorDisplayed());
    }


    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}