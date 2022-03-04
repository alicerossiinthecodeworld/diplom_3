import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LoginPage {
    public static String getLOGIN_URL() {
        return LOGIN_URL;
    }

    private static String LOGIN_URL = "https://stellarburgers.nomoreparties.site/login";

    @Step("ввести email")
    public void setEmailField(String email) {
        emailField.click();
        emailField.setValue(email);
    }

    @Step("ввести пароль")
    public void setPasswordField(String password) {
        passwordField.click();
        passwordField.setValue(password);
    }

    @Step("логин")
    public void login(String email, String password) {
        setEmailField(email);
        setPasswordField(password);
        clickLoginButton();
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

    public SelenideElement getOrderButton() {
        return orderButton;
    }

    @FindBy(how = How.XPATH, using = "//button[contains(text(),'Оформить заказ')]")
    private SelenideElement orderButton;

    public SelenideElement getLoginButton() {
        return loginButton;
    }

    @FindBy(how = How.CLASS_NAME, using = "button_button__33qZ0")
    private SelenideElement loginButton;


}
