import com.UserOperations;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import com.model.User;
import io.qameta.allure.Step;
import org.junit.After;
import org.junit.Assert;
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
    public void TearDown(){
        webdriver().driver().close();
    }

    @Test
    public void emailFieldTest(){
        setEmailField("alicerossi@yandex.ru");
        assertEquals("alicerossi@yandex.ru", loginPage.getEmailField().getValue());
    }
    @Test
    public void passwordFieldTest(){
        setPassField("sEkret123");
        assertEquals("sEkret123", loginPage.getPasswordField().getValue());
    }

    @Test
    public void loginSuccess() throws InterruptedException {
        Map<String, String>  userData = userOperations.register();
        login(userData.get("email"), userData.get("password"));
        wait1000();
        assertEquals("https://stellarburgers.nomoreparties.site/", WebDriverRunner.getWebDriver().getCurrentUrl());
        deleteUser();
    }




    @Step("setEmailField")
    public void setEmailField(String email){
        loginPage.setEmailField(email);
    }

    @Step("setPasswordField")
    public void setPassField(String password){
        loginPage.setPasswordField(password);
    }


    @Step("login")
    public void login(String email, String password) {
        loginPage.setEmailField(email);
        loginPage.setPasswordField(password);
        loginPage.clickLoginButton();
    }

    @Step("deleteUser")
    public void deleteUser(){
        userOperations.delete();
    }

    @Step("wait")
    public void wait1000() throws InterruptedException {
        Thread.sleep(1000);
    }
}
