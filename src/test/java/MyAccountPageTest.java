import com.UserOperations;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
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
public class MyAccountPageTest {
    private final String driverPath;

    public MyAccountPageTest(String driver) {
        this.driverPath = driver;
    }

    @Parameterized.Parameters //
    public static Object[][] getDriver() {
        return new Object[][]{
                {"drivers/yandexdriver"},
                {"drivers/chromedriver"}
        };
    }

    private MainPage mainPage;
    private LoginPage loginPage;
    private UserOperations userOperations;
    private MyAccount myAccount;
    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", driverPath);
        mainPage = open(MainPage.getMainPageURL(), MainPage.class);
        userOperations = new UserOperations();
    }
    @After
    public void TearDown(){
        webdriver().driver().close();
    }

    @Test
    public void logOutTest() throws InterruptedException {
        accessAccount();
        myAccount.clickLog0utButton();
        wait5000();
        assertEquals("https://stellarburgers.nomoreparties.site/login", WebDriverRunner.getWebDriver().getCurrentUrl());
        userOperations.delete();
    }

    @Test
    public void logoButtonTest() throws InterruptedException {
        accessAccount();
        myAccount.clickLogoButton();
        wait5000();
        assertEquals("https://stellarburgers.nomoreparties.site/", WebDriverRunner.getWebDriver().getCurrentUrl());
        userOperations.delete();
    }

    @Test
    public void constructorButtonTest() throws InterruptedException {
        accessAccount();
        myAccount.clickConstructorButton();
        wait5000();
        assertEquals("https://stellarburgers.nomoreparties.site/", WebDriverRunner.getWebDriver().getCurrentUrl());
        userOperations.delete();
    }


    @Step("access to my account")
    public void accessAccount() throws InterruptedException {
        mainPage.clickAccountButton();
        Map<String, String> userData = userOperations.register();
        loginPage = open(LoginPage.getLOGIN_URL(), LoginPage.class);
        login(userData.get("email"), userData.get("password"));
        loginPage.clickLoginButton();
        myAccount = open("https://stellarburgers.nomoreparties.site/", MyAccount.class);
        myAccount.clickAccountButton();
    }

    @Step("login")
    public void login(String email, String password) {
        loginPage.setEmailField(email);
        loginPage.setPasswordField(password);
        loginPage.clickLoginButton();
    }

    @Step("wait")
    public void wait5000() throws InterruptedException {
        Thread.sleep(5000);
    }
}
