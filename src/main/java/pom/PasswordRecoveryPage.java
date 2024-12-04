package pom;

import io.qameta.allure.Step;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PasswordRecoveryPage {
    private final WebDriverWait wait;

    public PasswordRecoveryPage(WebDriver webDriver) {
        this.wait = new WebDriverWait(webDriver, Duration.ofSeconds(3));
    }

    private final By ENTER_BUTTON = By.xpath("//a[@class='Auth_link__1fOlj']");
    private final By PASSWORD_RECOVERY_HEADER = By.xpath("//h2[contains(text(),'Восстановление пароля')]");

    @Step("Проверка отображения заголовка 'Восстановление пароля'")
    public boolean checkRecoveryPasswordHeaderIsVisible() {
        WebElement logo = wait.until(ExpectedConditions.visibilityOfElementLocated(PASSWORD_RECOVERY_HEADER));
        return logo.isDisplayed();
    }

    @Step("Нажатие на кнопку 'Войти' на странице восстановления пароля")
    public void clickEnterButton() {
        if (checkRecoveryPasswordHeaderIsVisible()) {
            WebElement button = wait.until(ExpectedConditions.elementToBeClickable(ENTER_BUTTON));
            button.click();
        }
    }
}
