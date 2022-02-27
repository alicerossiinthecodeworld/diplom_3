import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class RegistrationPage {
    public static String getURL() {
        return RegistrationPageURL;
    }

    private final static String RegistrationPageURL = "https://stellarburgers.nomoreparties.site/register";


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

}
