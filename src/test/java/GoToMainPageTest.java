import static pom.Constants.*;


import io.qameta.allure.Description;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import pom.BurgerMainPage;
import pom.LoginPage;
import rules.BrowserRule;

public class GoToMainPageTest {

    @Rule
    public BrowserRule browserRule = new BrowserRule();

    @Test
    @Description("Переход на главную страницу по клику на кнопку «Конструктор»")
    public void goToProfilePageViaConstructorButtonTest() {
        BurgerMainPage burgerMainPage = new BurgerMainPage(browserRule.getWebDriver());
        LoginPage loginPage = new LoginPage(browserRule.getWebDriver());

        burgerMainPage.clickLoginButton();
        loginPage.fillLoginFormFields(EMAIL, PASSWORD)
            .clickEnterButton();
        burgerMainPage.clickProfilePageButton()
            .clickConstructorButton();

        Assert.assertTrue(burgerMainPage.checkCreateBurgerHeaderIsVisible());
    }

    @Test
    @Description("Переход на главную страницу по клику на логотип Stellar Burgers")
    public void goToProfilePageViaLogoHeaderTest() {
        BurgerMainPage burgerMainPage = new BurgerMainPage(browserRule.getWebDriver());
        LoginPage loginPage = new LoginPage(browserRule.getWebDriver());

        burgerMainPage.clickLoginButton();
        loginPage.fillLoginFormFields(EMAIL, PASSWORD)
            .clickEnterButton();
        burgerMainPage.clickProfilePageButton()
            .clickStellarBurgerHeader();

        Assert.assertTrue(burgerMainPage.checkCreateBurgerHeaderIsVisible());
    }
}
