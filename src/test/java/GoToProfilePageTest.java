import static constants.Constants.*;

import controllers.UserController;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import pom.BurgerMainPage;
import pom.LoginPage;
import pom.ProfilePage;
import rules.BrowserRule;

public class GoToProfilePageTest {

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
    @Description("Переход по клику на «Личный кабинет» без авторизации")
    public void goToProfilePageWithoutAuthTest() {
        BurgerMainPage burgerMainPage = new BurgerMainPage(browserRule.getWebDriver());
        LoginPage loginPage = new LoginPage(browserRule.getWebDriver());

        burgerMainPage.clickLoginButton();
        Assert.assertTrue(loginPage.checkEnterLogoIsVisible());
    }

    @Test
    @Description("Переход по клику на «Личный кабинет» авторизованным пользователем")
    public void goToProfilePageWithAuthTest() {
        BurgerMainPage burgerMainPage = new BurgerMainPage(browserRule.getWebDriver());
        ProfilePage profilePage = new ProfilePage(browserRule.getWebDriver());
        LoginPage loginPage = new LoginPage(browserRule.getWebDriver());

        burgerMainPage.clickLoginButton();
        loginPage.fillLoginFormFields(RANDOM_EMAIL, PASSWORD)
            .clickEnterButton();
        burgerMainPage.clickProfilePageButton();

        Assert.assertTrue(profilePage.checkLabelProfileIsVisible());
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
