import com.codeborne.selenide.WebDriverRunner;
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
public class ForgotPasswordPageTest {
    private final String driverPath;

    public ForgotPasswordPageTest(String driver) {
        this.driverPath = driver;
    }

    @Parameterized.Parameters //
    public static Object[][] getDriver() {
        return new Object[][]{
                {"drivers/yandexdriver"},
                {"drivers/chromedriver"}
        };
    }

    private ForgotPasswordPage forgotPasswordPage;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", driverPath);
        forgotPasswordPage = open(ForgotPasswordPage.getFORGOT_PASSWORD_URL(), ForgotPasswordPage.class);
    }

    @After
    public void tearDown(){
        webdriver().driver().close();
    }

    @Test
    @DisplayName("тест логина через форму восстановления пароля")
    public void loginButtonLeadsToLoginPage(){
        forgotPasswordPage.clickLoginButton();
        assertEquals("https://stellarburgers.nomoreparties.site/login", WebDriverRunner.getWebDriver().getCurrentUrl());
    }
}
