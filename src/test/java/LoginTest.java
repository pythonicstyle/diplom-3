import static org.junit.Assert.assertTrue;
import static constants.Constants.*;

import controllers.UserController;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import pom.BurgerMainPage;
import pom.LoginPage;
import pom.PasswordRecoveryPage;
import pom.RegistrationPage;
import rules.BrowserRule;

public class LoginTest {

    UserController userController = new UserController();
    String token;

    @Rule
    public BrowserRule browserRule = new BrowserRule();

    @Before
    @DisplayName("Создание пользователя")
    public void createUser() {
        token = userController.createUser(
            RANDOM_EMAIL,
            PASSWORD,
            NAME
        ).then().statusCode(200).extract().jsonPath().getString("accessToken");
    }

    @Test
    @Description("Логин пользователя по кнопке «Войти в аккаунт» на главной")
    public void loginViaLoginToAccountButtonTest() {
        BurgerMainPage burgerMainPage = new BurgerMainPage(browserRule.getWebDriver());
        LoginPage loginPage = new LoginPage(browserRule.getWebDriver());

        burgerMainPage.clickLoginButton();
        loginPage.fillLoginFormFields(RANDOM_EMAIL, PASSWORD)
            .clickEnterButton();

        burgerMainPage.checkOrderButtonIsVisible();
    }

    @Test
    @Description("Логин пользователя через кнопку «Личный кабинет»")
    public void loginViaPersonalAccountButtonTest() {
        BurgerMainPage burgerMainPage = new BurgerMainPage(browserRule.getWebDriver());
        LoginPage loginPage = new LoginPage(browserRule.getWebDriver());

        burgerMainPage.clickProfilePageButton();
        loginPage.fillLoginFormFields(RANDOM_EMAIL, PASSWORD)
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
        loginPage.fillLoginFormFields(RANDOM_EMAIL, PASSWORD)
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
        loginPage.fillLoginFormFields(RANDOM_EMAIL, PASSWORD)
            .clickEnterButton();

        assertTrue(burgerMainPage.checkOrderButtonIsVisible());
    }

    @After
    public void tearDown() {

        if (token != null) {
            userController.deleteUser(token).then().statusCode(202);
            System.out.printf("\nПользователь %s удален", RANDOM_EMAIL);
        }
    }
}
