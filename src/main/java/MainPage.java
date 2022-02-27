import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class MainPage {

    public static String getMainPageURL() {
        return MainPageURL;
    }

    private final static String MainPageURL = "https://stellarburgers.nomoreparties.site/";

    public SelenideElement getOrderButton() {
        return orderButton;
    }

    public void clickOrderButton(){
        orderButton.click();
    }

    public void clickLoginButton(){
        loginButton.click();
    }
    @FindBy(how = How.XPATH, using = "//button[contains(text(),'Оформить заказ')]")
    private SelenideElement orderButton;

    @FindBy(how = How.XPATH, using = ".//button[text() = 'Войти в аккаунт']")
    private SelenideElement loginButton;

    public SelenideElement getLoginButton() {
        return loginButton;
    }

    public void clickAccountButton(){
        myAccountButton.click();
    }

    @FindBy(how = How.XPATH, using = ".//p[contains(text(),'Личный Кабинет')]")
    private SelenideElement myAccountButton;

    public void clickSauceButton() {sauceButton.click();}

    @FindBy(how = How.XPATH, using = ".//span[contains(text(),'Соусы')]")
    private SelenideElement sauceButton;

    public SelenideElement getSauceSection() {
        return sauceSection;
    }

    @FindBy(how = How.XPATH, using = "//h2[contains(text(), 'Соусы')]")
    private SelenideElement sauceSection;

    public void clickBunButton() {bunButton.click();}

    @FindBy(how = How.XPATH, using = ".//span[contains(text(),'Булки')]")
    private SelenideElement bunButton;

    public SelenideElement getBunSection() {
        return bunSection;
    }

    @FindBy(how = How.XPATH, using = "//h2[contains(text(), 'Булки')]")
    private SelenideElement bunSection;

    public void clickIngredientButton() {ingredientButton.click();}

    @FindBy(how = How.XPATH, using = ".//span[contains(text(),'Начинки')]")
    private SelenideElement ingredientButton;

    public SelenideElement getIngredientSection() {
        return ingredientSection;
    }

    @FindBy(how = How.XPATH, using = "//h2[contains(text(), 'Начинки')]")
    private SelenideElement ingredientSection;
}
