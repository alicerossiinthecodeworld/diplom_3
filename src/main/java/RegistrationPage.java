import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class RegistrationPage {
    public static String getURL() {
        return REGISTRATION_PAGE_URL;
    }
    private final static String REGISTRATION_PAGE_URL = "https://stellarburgers.nomoreparties.site/register";
    private final static String LOGIN_PAGE_URL  = "https://stellarburgers.nomoreparties.site/login";

    public SelenideElement getNameField() {
        return fields.get(0);
    }
    public SelenideElement getEmailField() {
        return fields.get(1);
    }

    public void setNameField(String email) {
        getNameField().setValue(email);
    }

    public void setEmailfield(String email) {
        getEmailField().setValue(email);
    }

    public SelenideElement getPasswordField() {
        return passwordField;
    }

    public void setPasswordField(String password) {
        passwordField.setValue(password);
    }

    public void clickRegisterButton(){
        registerButton.click();
    }

    @FindBy(how = How.NAME, using = "name")
    private List<SelenideElement> fields;

    @FindBy(how = How.NAME, using = "Пароль")
    private SelenideElement passwordField;

    @FindBy(how = How.CLASS_NAME, using = "button_button__33qZ0")
    private SelenideElement registerButton;

    public SelenideElement getIncorrectPasswordText() {
        return incorrectPasswordText;
    }

    @FindBy(how = How.XPATH, using = "/html//div[@id='root']/div[@class='App_App__aOmNj']//form[@class='Auth_form__3qKeq mb-20']//p[@class='input__error text_type_main-default']")
    private SelenideElement incorrectPasswordText;

    @Step("ввести email")
    public void setEmailField(String email) {
        emailField.click();
        emailField.setValue(email);
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

    @FindBy(how = How.XPATH, using = "//label[text()='Email']/following-sibling::input")
    private SelenideElement emailField;

    public SelenideElement getLoginButton() {
        return loginButton;
    }


    public SelenideElement getLoginText() {
        return loginText;
    }

    @FindBy(how = How.XPATH, using = "//h2[text()='Вход']")
    private SelenideElement loginText;

    @FindBy(how = How.XPATH, using = "//a[text()='Войти']")
    private SelenideElement loginButton;

    @Step("registration")
    public void registration(String name, String email, String password){
        setNameField(name);
        setEmailField(email);
        setPasswordField(password);
        clickRegisterButton();
    }
}
