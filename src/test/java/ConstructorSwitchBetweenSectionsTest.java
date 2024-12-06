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
public class ConstructorSwitchBetweenSectionsTest {
    private final By header;
    private final By section;

    @Rule
    public BrowserRule browserRule = new BrowserRule();

    public ConstructorSwitchBetweenSectionsTest(By header, By section) {
        this.header = header;
        this.section = section;
    }

    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return new Object[][] {
            { saucesHeader, saucesSectionLocator },
            { fillingsHeader, fillingsSectionLocator },
            { bunsHeader, bunsSectionLocator },
        };
    }

    @Test
    @Description("Скролл по разделам с ингредиентами")
    public void goToProfilePageViaConstructorButtonTest() {
        BurgerMainPage burgerMainPage = new BurgerMainPage(browserRule.getWebDriver());
        burgerMainPage.scrollToSection(section);
        Assert.assertTrue(burgerMainPage.checkIngredientSectionIsVisible(header));
    }
}
