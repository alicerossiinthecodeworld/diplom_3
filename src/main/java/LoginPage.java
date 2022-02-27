import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LoginPage {
    public static String getLOGIN_URL() {
        return LOGIN_URL;
    }

    private static String LOGIN_URL = "https://stellarburgers.nomoreparties.site/login";

    public void setEmailField(String email) {
        emailField.click();
        emailField.setValue(email);
    }

    public void setPasswordField(String password) {
        passwordField.click();
        passwordField.setValue(password);
    }

    public void clickLoginButton(){
        loginButton.click();
    }

    public SelenideElement getEmailField() {
        return emailField;
    }

    @FindBy(how = How.NAME, using = "name")
    private SelenideElement emailField;

    public SelenideElement getPasswordField() {
        return passwordField;
    }

    @FindBy(how = How.NAME, using = "Пароль")
    private SelenideElement passwordField;

    @FindBy(how = How.CLASS_NAME, using = "button_button__33qZ0")
    private SelenideElement loginButton;

}
