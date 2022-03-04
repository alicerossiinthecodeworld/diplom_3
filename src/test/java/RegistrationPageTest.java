import com.UserOperations;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import com.model.User;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.webdriver;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class RegistrationPageTest {
    private final String driverPath;

    public RegistrationPageTest(String driver) {
        this.driverPath = driver;
        }

    @Parameterized.Parameters //
    public static Object[][] getDriver() {
        return new Object[][] {
                {"drivers/yandexdriver"},
                {"drivers/chromedriver"}
        };
    }
    private RegistrationPage registrationPage;
    private LoginPage loginPage;
    private UserOperations userOperations;
    private Response response;
    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", driverPath);
        registrationPage = open(RegistrationPage.getURL(), RegistrationPage.class);
        loginPage = new LoginPage();
        userOperations = new UserOperations();
    }

    @After
    public void tearDown(){
        webdriver().driver().close();
    }

    @Test
    @DisplayName("тест ввода имени")
    public void nameFieldTest(){
        registrationPage.setNameField("alice");
        assertEquals("alice", registrationPage.getNameField().getValue());
    }

    @Test
    @DisplayName("тест ввода email")
    public void emailFieldTest(){
        registrationPage.setEmailField("alicerossi@yandex.ru");
        assertEquals("alicerossi@yandex.ru", registrationPage.getEmailField().getValue());
    }
    @Test
    @DisplayName("тест ввода пароля")
    public void passwordFieldTest(){
        registrationPage.setPasswordField("sEkret123");
        assertEquals("sEkret123", registrationPage.getPasswordField().getValue());
    }

    @Test
    @DisplayName("тест успешной регистрации")
    public void registerSuccessTest() {
            User user = User.getRandomUser();
            registrationPage.registration(user.getName(), user.getEmail(), user.getPassword());
            response = userOperations.login(user);
            assertEquals(200, response.statusCode());
            userOperations.deleteWithToken(response.body().path("accessToken"));
        }

    @Test
    @DisplayName("тест на короткий пароль")
    public void passwordTooShortTest(){
        registrationPage.registration("alice","rossi@123.ru","132");
        registrationPage.getIncorrectPasswordText().shouldBe(Condition.visible);
        assertEquals("Некорректный пароль",registrationPage.getIncorrectPasswordText().getText());
    }

    @Test
    @DisplayName("тест логина через кнопку регистрации")
    public void registerButtonLeadsToLoginPage() {
        User user = User.getRandomUser();
        registrationPage.registration(user.getName(), user.getEmail(), user.getPassword());
        registrationPage.clickRegisterButton();
        registrationPage.getLoginText().shouldBe(Condition.visible, Duration.ofMillis(2000));
        assertEquals(LoginPage.getLOGIN_URL(), WebDriverRunner.getWebDriver().getCurrentUrl());
    }


}
