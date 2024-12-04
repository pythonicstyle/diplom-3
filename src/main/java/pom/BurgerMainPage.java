package pom;

import io.qameta.allure.Step;
import java.time.Duration;
import org.openqa.selenium.By;
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

    private final By PROFILE_PAGE_BUTTON = By.xpath("//p[contains(text(),'Личный Кабинет')]");
    private final By CREATE_BURGER_HEADER = By.xpath("//h1[@class='text text_type_main-large mb-5 mt-10']");

    private final By CONSTRUCTOR_BUTTON = By.xpath("//p[contains(text(),'Конструктор')]");
    private final By LOGO_STELLAR_BURGER_BUTTON = By.xpath("//div[@class='AppHeader_header__logo__2D0X2']//a//*[name()='svg']");

    public static final By SAUCES_HEADER = By.xpath("//span[contains(text(),'Соусы')]");
    public static final By FILLINGS_HEADER = By.xpath("//span[contains(text(),'Начинки')]");
    public static final By BUNS_HEADER = By.xpath("//body/div[@id='root']/div[contains(@class,'App_App__aOmNj')]/main[contains(@class,'App_componentContainer__2JC2W')]/section[contains(@class,'BurgerIngredients_ingredients__1N8v2')]/div[1]");

    public static final By SAUCES_SECTION = By.xpath("//h2[contains(text(),'Соусы')]");
    public static final By FILLINGS_SECTION = By.xpath("//h2[contains(text(),'Начинки')]");
    public static final By BUNS_SECTION = By.xpath("//h2[contains(text(),'Булки')]");


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
        WebElement profileButton = wait.until(ExpectedConditions.elementToBeClickable(PROFILE_PAGE_BUTTON));
        profileButton.click();
        return this;
    }

    @Step("Проверка видимости логотипа 'Stellar Burger'")
    public boolean checkCreateBurgerHeaderIsVisible() {
        WebElement burgerHeader = wait.until(ExpectedConditions.elementToBeClickable(CREATE_BURGER_HEADER));
        return burgerHeader.isDisplayed();
    }

    @Step("Нажатие на логотип 'Stellar Burger'")
    public void clickStellarBurgerHeader() {
        WebElement burgerHeader = wait.until(ExpectedConditions.elementToBeClickable(LOGO_STELLAR_BURGER_BUTTON));
        burgerHeader.click();
    }

    @Step("Нажатие на кнопку 'Конструктор'")
    public void clickConstructorButton() {
        webDriver.findElement(CONSTRUCTOR_BUTTON).click();
    }

    @Step("Нажатие на заголовок")
    public void clickSaucesHeader(By locator) {
        boolean checkHeader = webDriver.findElement(locator).isSelected();
        if (!checkHeader) {
            webDriver.findElement(locator).click();
        }
    }

    @Step("Проверка отображения заголовка раздела")
    public boolean checkSaucesSectionIsVisible(By locator) {
        WebElement section = wait.until(ExpectedConditions.elementToBeClickable(locator));
        return section.isDisplayed();
    }

}