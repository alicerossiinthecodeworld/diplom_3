import com.UserOperations;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import com.model.User;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Map;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.webdriver;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class LoginPageTest {
    private final String driverPath;
    public LoginPageTest(String driver) {
        this.driverPath = driver;
    }

    @Parameterized.Parameters //
    public static Object[][] getDriver() {
        return new Object[][] {
                {"drivers/yandexdriver"},
                {"drivers/chromedriver"}
        };
    }

    private User user;
    private UserOperations userOperations;
    private LoginPage loginPage;
    private MainPage mainPage;
    private MyAccount myAccount;
    private final String MAIN_PAGE_URL = "https://stellarburgers.nomoreparties.site/";
    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", driverPath);
        loginPage = open(LoginPage.getLOGIN_URL(), LoginPage.class);
        user = User.getRandomUser();
        userOperations = new UserOperations();
        myAccount = new MyAccount();
        mainPage = new MainPage();
    }

    @After
    public void tearDown(){
        webdriver().driver().close();
    }

    @Test
    @DisplayName("тест поля почты")
    public void emailFieldTest(){
        loginPage.setEmailField("alicerossi@yandex.ru");
        assertEquals("alicerossi@yandex.ru", loginPage.getEmailField().getValue());
    }
    @Test
    @DisplayName("тест поля пароля")
    public void passwordFieldTest(){
        loginPage.setPasswordField("sEkret123");
        assertEquals("sEkret123", loginPage.getPasswordField().getValue());
    }

    @Test
    @DisplayName("тест логина")
    public void loginSuccess() {
        Map<String, String>  userData = userOperations.register();
        loginPage.login(userData.get("email"), userData.get("password"));
        loginPage.getOrderButton().shouldBe(Condition.visible);
        assertEquals(MAIN_PAGE_URL, WebDriverRunner.getWebDriver().getCurrentUrl());
        userOperations.delete();
    }
}
