import static org.hamcrest.CoreMatchers.equalTo;
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
import pom.ProfilePage;
import rules.BrowserRule;

public class LogoutTest {

    UserController userController = new UserController();

    @Rule
    public BrowserRule browserRule = new BrowserRule();

    @Before
    @DisplayName("Создание пользователя")
    public void testCreateUser() {
        userController.createUser(
            RANDOM_EMAIL,
            PASSWORD,
            NAME
        ).then().statusCode(200).and().body("success", equalTo(true));
    }

    @Test
    @Description("Выход из аккаунта")
    public void logoutTest() {
        BurgerMainPage burgerMainPage = new BurgerMainPage(browserRule.getWebDriver());
        ProfilePage profilePage = new ProfilePage(browserRule.getWebDriver());
        LoginPage loginPage = new LoginPage(browserRule.getWebDriver());

        burgerMainPage.clickLoginButton();
        loginPage.fillLoginFormFields(RANDOM_EMAIL, PASSWORD)
            .clickEnterButton();
        burgerMainPage.clickProfilePageButton();
        profilePage.clickLogoutButton();

        assertTrue(loginPage.checkEnterLogoIsVisible());
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
