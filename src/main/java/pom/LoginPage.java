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

    private final By emailField = By.xpath("//label[text()='Email']/following-sibling::input");
    private final By passwordField = By.xpath("//input[@name='Пароль']");
    private final By enterButton = By.xpath("//button[@class='button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_medium__3zxIa']");
    private final By recoverPasswordButton = By.xpath("//a[contains(text(),'Восстановить пароль')]");
    private final By enterLogo = By.xpath("//h2[contains(text(),'Вход')]");
    private final By incorrectPasswordMessage = By.xpath("//p[@class='input__error text_type_main-default']");
    private final By registrationButton = By.className("Auth_link__1fOlj");


    @Step("Проверка отображения заголовка 'Вход'")
    public boolean checkEnterLogoIsVisible() {
        WebElement logo = wait.until(ExpectedConditions.visibilityOfElementLocated(enterLogo));
        return logo.isDisplayed();
    }

    @Step("Заполнение полей на форме логина")
    public LoginPage fillLoginFormFields(String email, String password) {
        if (checkEnterLogoIsVisible()) {
            webDriver.findElement(emailField).sendKeys(email);
            webDriver.findElement(passwordField).sendKeys(password);
        }
        return this;
    }

    @Step("Нажатие на кнопку 'Войти'")
    public void clickEnterButton() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(enterButton));
        button.click();
    }

    @Step("Проверка отображения сообщения об ошибке при вводе некорректного пароля")
    public boolean checkIncorrectPasswordMessageIsVisible() {
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(incorrectPasswordMessage));
        return errorMessage.isDisplayed();
    }


    @Step("Нажатие на кнопку 'Восстановить пароль'")
    public void clickRecoverPasswordButton() {
        webDriver.findElement(recoverPasswordButton).click();
    }

    @Step("Нажатие на кнопку 'Зарегистрироваться'")
    public void clickRegistrationButton() {
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(registrationButton));
        loginButton.click();
    }
}
