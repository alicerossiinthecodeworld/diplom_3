import com.UserOperations;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import com.model.User;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

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
    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", driverPath);
        registrationPage = open(RegistrationPage.getURL(), RegistrationPage.class);
        loginPage = new LoginPage();
        userOperations = new UserOperations();
    }

    @After
    public void TearDown(){
        webdriver().driver().close();
    }

    @Test
    public void nameFieldTest(){
        setNameField("alice");
        assertEquals("alice", registrationPage.getNameField().getValue());
    }

    @Test
    public void emailFieldTest(){
        setEmailField("alicerossi@yandex.ru");
        assertEquals("alicerossi@yandex.ru", registrationPage.getEmailField().getValue());
    }
    @Test
    public void passwordFieldTest(){
        setPassField("sEkret123");
        assertEquals("sEkret123", registrationPage.getPasswordField().getValue());
    }

    @Test
    public void registerSuccessTest() throws InterruptedException {
            User user = User.getRandomUser();
            registration(user.getName(), user.getEmail(), user.getPassword());
            userOperations.login(user);
        }

    @Test
    public void passwordTooShortTest(){
        registration("alice","rossi@123.ru","132");
        registrationPage.getIncorrectPasswordText().shouldBe(Condition.visible);
        assertEquals("Некорректный пароль",registrationPage.getIncorrectPasswordText().getText());
    }

    @Test
    public void registerButtonLeadsToLoginPage() throws InterruptedException {
        User user = User.getRandomUser();
        registration(user.getName(), user.getEmail(), user.getPassword());
        registrationPage.clickRegisterButton();
        wait1000();
        assertEquals(LoginPage.getLOGIN_URL(), WebDriverRunner.getWebDriver().getCurrentUrl());
    }

    @Step("registration")
    public void registration(String name, String email, String password){
        setNameField(name);
        setEmailField(email);
        setPassField(password);
        registrationPage.clickRegisterButton();
    }

    @Step("login")
    public void login(User user) {
        loginPage = open(LoginPage.getLOGIN_URL(), LoginPage.class);
        loginPage.setEmailField(user.getEmail());
        loginPage.setPasswordField(user.getPassword());
        loginPage.clickLoginButton();
    }


    @Step("setNameField")
    public void setNameField(String name){
        registrationPage.setNameField(name);
    }

    @Step("setEmailField")
    public void setEmailField(String email){
        registrationPage.setEmailfield(email);
    }

    @Step("setPasswordField")
    public void setPassField(String password){
        registrationPage.setPasswordField(password);
    }

    @Step("deleteUser")
    public Response deleteUser(String token){
        return userOperations.deleteWithToken(token);
    }

    @Step("wait")
    public void wait1000() throws InterruptedException {
        Thread.sleep(1000);
    }
}
