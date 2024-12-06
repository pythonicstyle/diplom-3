package pom;

import io.qameta.allure.Step;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BurgerMainPage {

    private final WebDriver webDriver;
    private final WebDriverWait wait;

    public BurgerMainPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.wait = new WebDriverWait(webDriver, Duration.ofSeconds(3));
    }

    private final By loginButton = By.xpath(
        "//button[@class='button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_large__G21Vg']");

    private final By profilePageButton = By.xpath("//p[contains(text(),'Личный Кабинет')]");
    private final By createBurgerHeader = By.xpath("//h1[@class='text text_type_main-large mb-5 mt-10']");

    private final By constructorButton = By.xpath("//p[contains(text(),'Конструктор')]");
    private final By logoStellarBurgerButton = By.xpath("//div[@class='AppHeader_header__logo__2D0X2']//a//*[name()='svg']");

    public static final By saucesSectionLocator  = By.xpath("//h2[contains(text(),'Соусы')]");
    public static final By fillingsSectionLocator = By.xpath("//h2[contains(text(),'Начинки')]");
    public static final By bunsSectionLocator = By.xpath("//h2[contains(text(),'Булки')]");

    public static final By saucesHeader = By.xpath("//div[contains(@class, 'tab_tab__1SPyG') and contains(@class, 'tab_tab_type_current__2BEPc')]//span[text()='Соусы']");
    public static final By fillingsHeader = By.xpath("//div[contains(@class, 'tab_tab__1SPyG') and contains(@class, 'tab_tab_type_current__2BEPc')]//span[text()='Начинки']");
    public static final By bunsHeader = By.xpath("//div[contains(@class, 'tab_tab__1SPyG') and contains(@class, 'tab_tab_type_current__2BEPc')]//span[text()='Булки']");


    @Step("Проверка видимости кнопки 'Вход'")
    public boolean checkOrderButtonIsVisible() {
        WebElement orderButton = wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        return orderButton.isDisplayed();
    }

    @Step("Нажатие кнопки 'Вход'")
    public void clickLoginButton() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        button.click();
    }

    @Step("Нажатие кнопки 'Личный кабинет'")
    public BurgerMainPage clickProfilePageButton() {
        WebElement profileButton = wait.until(ExpectedConditions.elementToBeClickable(profilePageButton));
        profileButton.click();
        return this;
    }

    @Step("Проверка видимости логотипа 'Stellar Burger'")
    public boolean checkCreateBurgerHeaderIsVisible() {
        WebElement burgerHeader = wait.until(ExpectedConditions.elementToBeClickable(createBurgerHeader));
        return burgerHeader.isDisplayed();
    }

    @Step("Нажатие на логотип 'Stellar Burger'")
    public void clickStellarBurgerHeader() {
        WebElement burgerHeader = wait.until(ExpectedConditions.elementToBeClickable(logoStellarBurgerButton));
        burgerHeader.click();
    }

    @Step("Нажатие на кнопку 'Конструктор'")
    public void clickConstructorButton() {
        webDriver.findElement(constructorButton).click();
    }

//    @Step("Нажатие на заголовок")
//    public void clickIngredientHeader(By locator) {
//        boolean checkHeader = webDriver.findElement(locator).isSelected();
//        if (!checkHeader) {
//            webDriver.findElement(locator).click();
//        }
//    }

    @Step("Скролл до нужного раздела с ингредиентами")
    public void scrollToSection(By buttonLocator) {
        ((JavascriptExecutor) webDriver).executeScript(
            "arguments[0].scrollIntoView();",
            webDriver.findElement(buttonLocator)
        );
    }

    @Step("Проверка отображения заголовка раздела")
    public boolean checkIngredientSectionIsVisible(By locator) {
        WebElement section = wait.until(ExpectedConditions.elementToBeClickable(locator));
        return section.isDisplayed();
    }

}