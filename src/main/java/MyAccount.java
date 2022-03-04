import com.UserOperations;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.Map;

public class MyAccount {
    private static String MY_ACCOUNT_URL = "https://stellarburgers.nomoreparties.site/account/profile";

    public static String getMainPageUrl() {
        return MAIN_PAGE_URL;
    }

    private static String MAIN_PAGE_URL = "https://stellarburgers.nomoreparties.site/";

    public final static String getMY_ACCOUNT_URL() {
        return MY_ACCOUNT_URL;
    }
    private UserOperations userOperations = new UserOperations();

    public void clickLog0utButton(){
        logOutButton.click();
    }

    @FindBy(how = How.XPATH, using = "//li[@class='Account_listItem__35dAP']/button[text()='Выход']")
    private SelenideElement logOutButton;

    public void clickAccountButton(){
        myAccountButton.click();
    }

    @FindBy(how = How.XPATH, using = ".//p[contains(text(),'Личный Кабинет')]")
    private SelenideElement myAccountButton;

    public void clickLogoButton(){
        logoButton.click();
    }

    public void clickConstructorButton(){
        constructorButton.click();
    }

    @FindBy(how = How.CLASS_NAME, using = "AppHeader_header__logo__2D0X2")
    private SelenideElement logoButton;

    @FindBy(how = How.XPATH, using = "//p[contains(text(),'Конструктор')]")
    private SelenideElement constructorButton;

    public SelenideElement getOrderButton() {
        return orderButton;
    }

    @FindBy(how = How.NAME, using = "Пароль")
    private SelenideElement passwordField;

    @FindBy(how = How.XPATH, using = "//button[contains(text(),'Оформить заказ')]")
    private SelenideElement orderButton;

    public SelenideElement getPasswordField() {
        return passwordField;
    }

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

    public SelenideElement getEmailField() {
        return emailField;
    }

    @FindBy(how = How.NAME, using = "name")
    private SelenideElement emailField;

    @FindBy(how = How.CLASS_NAME, using = "Modal_modal__close__TnseK")
    private SelenideElement closeOrderWindow;

    @FindBy(how = How.CLASS_NAME, using = "button_button__33qZ0")
    private SelenideElement loginButton;

    @Step("access to my account")
    public void accessAccount()  {
        clickAccountButton();
        Map<String, String> userData = userOperations.register();
        login(userData.get("email"), userData.get("password"));
        clickLoginButton();
        clickWindow();
        clickAccountButton();
    }


    public void clickLoginButton(){
        loginButton.click();
    }

    public void clickWindow(){
        closeOrderWindow.click();
    }
}
