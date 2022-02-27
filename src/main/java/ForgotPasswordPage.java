import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ForgotPasswordPage {
    public static String getForgotPasswordURL() {
        return ForgotPasswordURL;
    }

    private static String ForgotPasswordURL = "https://stellarburgers.nomoreparties.site/forgot-password";

    public void clickLoginButton(){
        loginButton.click();
    }

    @FindBy(how = How.XPATH, using = ".//a[text() = 'Войти']")
    private SelenideElement loginButton;
}
