import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class MyAccount {
    private static String MyAccountURL = "https://stellarburgers.nomoreparties.site/account/profile";

    public static String getMyAccountURL() {
        return MyAccountURL;
    }

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
}
