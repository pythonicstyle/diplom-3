import static org.junit.Assert.assertTrue;
import static constants.Constants.*;

import controllers.UserController;
import io.qameta.allure.Description;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import pom.BurgerMainPage;
import pom.LoginPage;
import pom.ProfilePage;
import pom.RegistrationPage;
import rules.BrowserRule;

public class RegistrationTest {

    UserController userController = new UserController();

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
        registrationPage.fillRegistrationFormFields(NAME, RANDOM_EMAIL, INCORRECT_PASSWORD)
            .clickConfirmRegistrationButton();

        assertTrue(loginPage.checkIncorrectPasswordMessageIsVisible());
    }

    @After
    public void tearDown() {
        String token = userController.getAuthToken(RANDOM_EMAIL, PASSWORD);
        if (token != null) {
            userController.deleteUser(token).then().statusCode(202);
            System.out.printf("\nПользователь %s удален", RANDOM_EMAIL);
        }
    }
}
