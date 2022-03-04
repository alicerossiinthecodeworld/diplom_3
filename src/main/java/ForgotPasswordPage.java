import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ForgotPasswordPage {
    public static String getFORGOT_PASSWORD_URL() {
        return FORGOT_PASSWORD_URL;
    }

    private static String FORGOT_PASSWORD_URL = "https://stellarburgers.nomoreparties.site/forgot-password";

    public void clickLoginButton(){
        loginButton.click();
    }

    @FindBy(how = How.XPATH, using = ".//a[text() = 'Войти']")
    private SelenideElement loginButton;
}
