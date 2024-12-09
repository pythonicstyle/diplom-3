package pom;

import io.qameta.allure.Step;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProfilePage {
    private final WebDriverWait wait;

    public ProfilePage(WebDriver webDriver) {
        this.wait = new WebDriverWait(webDriver, Duration.ofSeconds(3));
    }

    private final By logoutButton = By.xpath("//button[contains(text(),'Выход')]");
    private final By profileHeader = By.xpath(
        "//a[@class='Account_link__2ETsJ text text_type_main-medium text_color_inactive Account_link_active__2opc9']");

    @Step("Нажатие на кнопку 'Выход'")
    public void clickLogoutButton() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(logoutButton));
        button.click();
    }

    @Step("проверка отображения заголовка 'Профиль'")
    public boolean checkLabelProfileIsVisible() {
        WebElement label = wait.until(ExpectedConditions.elementToBeClickable(profileHeader));
        return label.isDisplayed();
    }
}
