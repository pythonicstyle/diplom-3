package pom;

import io.qameta.allure.Step;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    private final WebDriver webDriver;
    private final WebDriverWait wait;

    public LoginPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.wait = new WebDriverWait(webDriver, Duration.ofSeconds(3));
    }

    private final By EMAIL_FIELD = By.xpath("//label[text()='Email']/following-sibling::input");
    private final By PASSWORD_FIELD = By.xpath("//input[@name='Пароль']");
    private final By ENTER_BUTTON = By.xpath("//button[@class='button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_medium__3zxIa']");
    private final By RECOVER_PASSWORD_BUTTON = By.xpath("//a[contains(text(),'Восстановить пароль')]");
    private final By ENTER_LOGO = By.xpath("//h2[contains(text(),'Вход')]");
    private final By INCORRECT_PASSWORD_MESSAGE = By.xpath("//p[@class='input__error text_type_main-default']");
    private final By REGISTRATION_BUTTON = By.className("Auth_link__1fOlj");


    @Step("Проверка отображения заголовка 'Вход'")
    public boolean checkEnterLogoIsVisible() {
        WebElement logo = wait.until(ExpectedConditions.visibilityOfElementLocated(ENTER_LOGO));
        return logo.isDisplayed();
    }

    @Step("Заполнение полей на форме логина")
    public LoginPage fillLoginFormFields(String email, String password) {
        if (checkEnterLogoIsVisible()) {
            webDriver.findElement(EMAIL_FIELD).sendKeys(email);
            webDriver.findElement(PASSWORD_FIELD).sendKeys(password);
        }
        return this;
    }

    @Step("Нажатие на кнопку 'Войти'")
    public void clickEnterButton() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(ENTER_BUTTON));
        button.click();
    }

    @Step("Проверка отображения сообщения об ошибке при вводе некорректного пароля")
    public boolean checkIncorrectPasswordMessageIsVisible() {
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(INCORRECT_PASSWORD_MESSAGE));
        return errorMessage.isDisplayed();
    }


    @Step("Нажатие на кнопку 'Восстановить пароль'")
    public void clickRecoverPasswordButton() {
        webDriver.findElement(RECOVER_PASSWORD_BUTTON).click();
    }

    @Step("Нажатие на кнопку 'Зарегистрироваться'")
    public void clickRegistrationButton() {
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(REGISTRATION_BUTTON));
        loginButton.click();
    }
}
