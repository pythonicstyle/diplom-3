import static org.junit.Assert.assertTrue;
import static pom.Constants.*;

import io.qameta.allure.Description;
import org.junit.Rule;
import org.junit.Test;
import pom.BurgerMainPage;
import pom.LoginPage;
import pom.ProfilePage;
import rules.BrowserRule;

public class LogoutTest {

    @Rule
    public BrowserRule browserRule = new BrowserRule();

    @Test
    @Description("Выход из аккаунта")
    public void logoutTest() {
        BurgerMainPage burgerMainPage = new BurgerMainPage(browserRule.getWebDriver());
        ProfilePage profilePage = new ProfilePage(browserRule.getWebDriver());
        LoginPage loginPage = new LoginPage(browserRule.getWebDriver());

        burgerMainPage.clickLoginButton();
        loginPage.fillLoginFormFields(EMAIL, PASSWORD)
            .clickEnterButton();
        burgerMainPage.clickProfilePageButton();
        profilePage.clickLogoutButton();

        assertTrue(loginPage.checkEnterLogoIsVisible());
    }

}
