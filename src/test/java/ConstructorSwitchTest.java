import static pom.BurgerMainPage.*;

import io.qameta.allure.Description;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import pom.BurgerMainPage;
import rules.BrowserRule;

@RunWith(Parameterized.class)
public class ConstructorSwitchTest {
    private final By header;
    private final By section;

    @Rule
    public BrowserRule browserRule = new BrowserRule();

    public ConstructorSwitchTest(By header, By section) {
        this.header = header;
        this.section = section;
    }

    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return new Object[][] {
            { SAUCES_HEADER, SAUCES_SECTION },
            { FILLINGS_HEADER, FILLINGS_SECTION },
            { BUNS_HEADER, BUNS_SECTION },
        };
    }

    @Test
    @Description("Переход по разделам с ингредиентами")
    public void goToProfilePageViaConstructorButtonTest() {
        BurgerMainPage burgerMainPage = new BurgerMainPage(browserRule.getWebDriver());
        burgerMainPage.clickSaucesHeader(header);
        Assert.assertTrue(burgerMainPage.checkSaucesSectionIsVisible(section));
    }
}
