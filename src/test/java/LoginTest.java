import static org.junit.Assert.assertTrue;
import static pom.Constants.*;

import io.qameta.allure.Description;
import org.junit.Rule;
import org.junit.Test;
import pom.BurgerMainPage;
import pom.LoginPage;
import pom.PasswordRecoveryPage;
import pom.RegistrationPage;
import rules.BrowserRule;

public class LoginTest {

    @Rule
    public BrowserRule browserRule = new BrowserRule();

    @Test
    @Description("Логин пользователя по кнопке «Войти в аккаунт» на главной")
    public void loginViaLoginToAccountButtonTest() {
        BurgerMainPage burgerMainPage = new BurgerMainPage(browserRule.getWebDriver());
        LoginPage loginPage = new LoginPage(browserRule.getWebDriver());

        burgerMainPage.clickLoginButton();
        loginPage.fillLoginFormFields(EMAIL, PASSWORD)
            .clickEnterButton();

        burgerMainPage.checkOrderButtonIsVisible();
    }

    @Test
    @Description("Логин пользователя через кнопку «Личный кабинет»")
    public void loginViaPersonalAccountButtonTest() {
        BurgerMainPage burgerMainPage = new BurgerMainPage(browserRule.getWebDriver());
        LoginPage loginPage = new LoginPage(browserRule.getWebDriver());

        burgerMainPage.clickProfilePageButton();
        loginPage.fillLoginFormFields(EMAIL, PASSWORD)
            .clickEnterButton();

        assertTrue(burgerMainPage.checkOrderButtonIsVisible());
    }

    @Test
    @Description("Логин пользователя через кнопку в форме регистрации")
    public void loginViaRegistrationFormTest() {
        BurgerMainPage burgerMainPage = new BurgerMainPage(browserRule.getWebDriver());
        RegistrationPage registrationPage = new RegistrationPage(browserRule.getWebDriver());
        LoginPage loginPage = new LoginPage(browserRule.getWebDriver());

        burgerMainPage.clickLoginButton();
        loginPage.clickRegistrationButton();
        registrationPage.clickEnterButton();
        loginPage.fillLoginFormFields(EMAIL, PASSWORD)
            .clickEnterButton();
        assertTrue(burgerMainPage.checkOrderButtonIsVisible());
    }

    @Test
    @Description("Логин пользователя через кнопку в форме восстановления пароля")
    public void loginViaPasswordRecoveryFormTest() {
        BurgerMainPage burgerMainPage = new BurgerMainPage(browserRule.getWebDriver());
        LoginPage loginPage = new LoginPage(browserRule.getWebDriver());
        PasswordRecoveryPage passwordRecoveryPage = new PasswordRecoveryPage(browserRule.getWebDriver());

        burgerMainPage.clickProfilePageButton();
        loginPage.clickRecoverPasswordButton();
        passwordRecoveryPage.clickEnterButton();
        loginPage.fillLoginFormFields(EMAIL, PASSWORD)
            .clickEnterButton();

        assertTrue(burgerMainPage.checkOrderButtonIsVisible());
    }
}
