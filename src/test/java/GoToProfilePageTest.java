import static pom.Constants.*;


import io.qameta.allure.Description;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import pom.BurgerMainPage;
import pom.LoginPage;
import pom.ProfilePage;
import rules.BrowserRule;

public class GoToProfilePageTest {

    @Rule
    public BrowserRule browserRule = new BrowserRule();

    @Test
    @Description("Переход по клику на «Личный кабинет» без авторизации")
    public void goToProfilePageWithoutAuthTest() {
        BurgerMainPage burgerMainPage = new BurgerMainPage(browserRule.getWebDriver());
        LoginPage loginPage = new LoginPage(browserRule.getWebDriver());

        burgerMainPage.clickLoginButton();
        Assert.assertTrue(loginPage.checkEnterLogoIsVisible());
    }

    @Test
    @Description("Переход по клику на «Личный кабинет» без авторизации")
    public void goToProfilePageWithAuthTest() {
        BurgerMainPage burgerMainPage = new BurgerMainPage(browserRule.getWebDriver());
        ProfilePage profilePage = new ProfilePage(browserRule.getWebDriver());
        LoginPage loginPage = new LoginPage(browserRule.getWebDriver());

        burgerMainPage.clickLoginButton();
        loginPage.fillLoginFormFields(EMAIL, PASSWORD)
            .clickEnterButton();
        burgerMainPage.clickProfilePageButton();

        Assert.assertTrue(profilePage.checkLabelProfileIsVisible());
    }
}
