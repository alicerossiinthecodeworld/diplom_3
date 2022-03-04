import com.UserOperations;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

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
    private UserOperations userOperations;
    private MyAccount myAccount;
    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", driverPath);
        userOperations = new UserOperations();
        myAccount = open(myAccount.getMainPageUrl(), MyAccount.class);
    }
    @After
    public void TearDown(){
        webdriver().driver().close();
    }

    @Test
    @Step("тест выхода из аккаунта")
    public void logOutTest() {
        myAccount.accessAccount();
        myAccount.clickLog0utButton();
        myAccount.getPasswordField().shouldBe(Condition.visible);
        assertEquals("https://stellarburgers.nomoreparties.site/login", WebDriverRunner.getWebDriver().getCurrentUrl());
        userOperations.delete();
    }

    @Test
    @Step("тест перехода по кнопке лого")
    public void logoButtonTest() throws InterruptedException {
        myAccount.accessAccount();
        myAccount.clickLogoButton();
        myAccount.getOrderButton().shouldBe(Condition.visible);
        assertEquals("https://stellarburgers.nomoreparties.site/", WebDriverRunner.getWebDriver().getCurrentUrl());
        userOperations.delete();
    }

    @Test
    @DisplayName("тест перехода по кнопке конструктора")
    public void constructorButtonTest() throws InterruptedException {
        myAccount.accessAccount();
        myAccount.clickConstructorButton();
        myAccount.getOrderButton().shouldBe(Condition.visible);
        assertEquals("https://stellarburgers.nomoreparties.site/", WebDriverRunner.getWebDriver().getCurrentUrl());
        userOperations.delete();
    }
}
