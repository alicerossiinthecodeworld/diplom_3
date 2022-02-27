import com.UserOperations;
import com.codeborne.selenide.Condition;
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
public class MainPageTest {
    private final String driverPath;

    public MainPageTest(String driver) {
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
        myAccount = new MyAccount();
    }

    @After
    public void TearDown(){
        webdriver().driver().close();
    }

    @Test
    public void loginButtonOpensLoginPageTest() {
        mainPage.clickLoginButton();
        assertEquals(LoginPage.getLOGIN_URL(), WebDriverRunner.getWebDriver().getCurrentUrl());
    }

    @Test
    public void myAccountButtonOpensLoginPageTest() throws InterruptedException {
        mainPage.clickAccountButton();
        assertEquals(LoginPage.getLOGIN_URL(), WebDriverRunner.getWebDriver().getCurrentUrl());
    }

    @Test
    public void myAccountButtonOpensAccountAfterRegTest() throws InterruptedException {
        mainPage.clickAccountButton();
        Map<String, String> userData = userOperations.register();
        loginPage = open(LoginPage.getLOGIN_URL(), LoginPage.class);
        login(userData.get("email"), userData.get("password"));
        mainPage.clickAccountButton();
        wait5000();
        assertEquals("https://stellarburgers.nomoreparties.site/account/profile", WebDriverRunner.getWebDriver().getCurrentUrl());
        userOperations.delete();
    }

    @Test
    public void bunFilterTest(){
        mainPage.clickSauceButton();
        mainPage.clickBunButton();
        mainPage.getBunSection().shouldBe(Condition.visible);
    }

    @Test
    public void sauceFilterTest(){
        mainPage.clickSauceButton();
        mainPage.getSauceSection().shouldBe(Condition.visible);
    }

    @Test
    public void ingredientFilterTest(){
        mainPage.clickIngredientButton();
        mainPage.getIngredientSection().shouldBe(Condition.visible);
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

    @Step("wait")
    public void wait5000() throws InterruptedException {
        Thread.sleep(5000);
    }
}
