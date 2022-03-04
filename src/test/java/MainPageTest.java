import com.UserOperations;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
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

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", driverPath);
        mainPage = open(MainPage.getMAIN_PAGE_URL(), MainPage.class);
        userOperations = new UserOperations();
    }

    @After
    public void TearDown(){
        webdriver().driver().close();
    }

    @Test
    @DisplayName("логин по кнопке входа на главной странице")
    public void loginButtonOpensLoginPageTest() {
        mainPage.clickLoginButton();
        assertEquals(LoginPage.getLOGIN_URL(), WebDriverRunner.getWebDriver().getCurrentUrl());
    }

    @Test
    @DisplayName("вход через кнопку мой аккаунт")
    public void myAccountButtonOpensLoginPageTest() throws InterruptedException {
        mainPage.clickAccountButton();
        assertEquals(LoginPage.getLOGIN_URL(), WebDriverRunner.getWebDriver().getCurrentUrl());
    }

    @Test
    @DisplayName("мой аккаунт открывается после регистрации")
    public void myAccountButtonOpensAccountAfterRegTest(){
        mainPage.clickAccountButton();
        Map<String, String> userData = userOperations.register();
        loginPage = open(LoginPage.getLOGIN_URL(), LoginPage.class);
        loginPage.login(userData.get("email"), userData.get("password"));
        mainPage.clickAccountButton();
        mainPage.getLogOutButton().shouldBe(Condition.visible);
        assertEquals("https://stellarburgers.nomoreparties.site/account/profile", WebDriverRunner.getWebDriver().getCurrentUrl());
        userOperations.delete();
    }

    @Test
    @DisplayName("тест фильтра булочки")
    public void bunFilterTest(){
        mainPage.clickSauceButton();
        mainPage.clickBunButton();
        mainPage.getBunSection().shouldBe(Condition.visible);
    }

    @Test
    @DisplayName("тест фильтра соусов")
    public void sauceFilterTest(){
        mainPage.clickSauceButton();
        mainPage.getSauceSection().shouldBe(Condition.visible);
    }

    @Test
    @DisplayName("тест фильтра начинки")
    public void ingredientFilterTest(){
        mainPage.clickIngredientButton();
        mainPage.getIngredientSection().shouldBe(Condition.visible);
    }
}
