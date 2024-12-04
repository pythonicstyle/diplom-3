import static org.junit.Assert.assertTrue;
import static pom.Constants.*;

import io.qameta.allure.Description;
import org.junit.Rule;
import org.junit.Test;
import pom.BurgerMainPage;
import pom.LoginPage;
import pom.ProfilePage;
import pom.RegistrationPage;
import rules.BrowserRule;

public class RegistrationTest {

    @Rule
    public BrowserRule browserRule = new BrowserRule();

    @Test
    @Description("Успешная регистрация пользователя")
    public void registrationTest() {
        BurgerMainPage burgerMainPage = new BurgerMainPage(browserRule.getWebDriver());
        RegistrationPage registrationPage = new RegistrationPage(browserRule.getWebDriver());
        LoginPage loginPage = new LoginPage(browserRule.getWebDriver());
        ProfilePage profilePage = new ProfilePage(browserRule.getWebDriver());

        burgerMainPage.clickLoginButton();
        loginPage.clickRegistrationButton();
        registrationPage.fillRegistrationFormFields(NAME, RANDOM_EMAIL, PASSWORD)
            .clickConfirmRegistrationButton();
        loginPage.fillLoginFormFields(RANDOM_EMAIL, PASSWORD)
                .clickEnterButton();
        burgerMainPage.clickProfilePageButton();

        assertTrue(profilePage.checkLabelProfileIsVisible());
    }

    @Test
    @Description("Регистрация пользователя с паролем менее 6 символов")
    public void registrationTestWithIncorrectPassword() {
        BurgerMainPage burgerMainPage = new BurgerMainPage(browserRule.getWebDriver());
        RegistrationPage registrationPage = new RegistrationPage(browserRule.getWebDriver());
        LoginPage loginPage = new LoginPage(browserRule.getWebDriver());

        burgerMainPage.clickLoginButton();
        loginPage.clickRegistrationButton();
        registrationPage.fillRegistrationFormFields(NAME, EMAIL, INCORRECT_PASSWORD)
            .clickConfirmRegistrationButton();

        assertTrue(loginPage.checkIncorrectPasswordMessageIsVisible());
    }
}
