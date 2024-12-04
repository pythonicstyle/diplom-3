package pom;

import io.qameta.allure.Step;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegistrationPage {
    private final WebDriver webDriver;
    private final WebDriverWait wait;

    public RegistrationPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.wait = new WebDriverWait(webDriver, Duration.ofSeconds(3));
    }

    private final By NAME_FIELD = By.xpath("//label[text()='Имя']/following-sibling::input");
    private final By EMAIL_FIELD = By.xpath("//label[text()='Email']/following-sibling::input");
    private final By PASSWORD_FIELD = By.xpath("//input[@name='Пароль']");

    private final By REGISTRATION_HEADER = By.xpath("//h2[contains(text(),'Регистрация')]");
    private final By ENTER_BUTTON = By.xpath("//a[@class='Auth_link__1fOlj']");
    private final By CONFIRM_REGISTRATION_FIELD = By.xpath("//button[@class='button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_medium__3zxIa']");

    @Step("Проверка отображения заголовка 'Регистрация'")
    public boolean checkRegistrationHeaderIsVisible() {
        WebElement logo = wait.until(ExpectedConditions.visibilityOfElementLocated(REGISTRATION_HEADER));
        return logo.isDisplayed();
    }

    @Step("Заполнение полей на форме регистрации")
    public RegistrationPage fillRegistrationFormFields(String name, String email, String password) {
        if (checkRegistrationHeaderIsVisible()) {
            webDriver.findElement(NAME_FIELD).sendKeys(name);
            webDriver.findElement(EMAIL_FIELD).sendKeys(email);
            webDriver.findElement(PASSWORD_FIELD).sendKeys(password);
        }
        return this;
    }

    @Step("Подтверждение регистрации")
    public void clickConfirmRegistrationButton() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(CONFIRM_REGISTRATION_FIELD));
        button.click();
    }

    @Step("Нажатие на кнопку 'Войти' на странице регистрации")
    public void clickEnterButton() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(ENTER_BUTTON));
        button.click();
    }
}
