package rules;

import static constants.Constants.*;

import org.junit.rules.ExternalResource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


public class BrowserRule extends ExternalResource {
    private WebDriver webDriver;

    protected void before() {

        ChromeOptions options = new ChromeOptions();

        if ("yandex".equals(BROWSER)) {
            System.setProperty(DRIVER, PATH_TO_YANDEX_DRIVER);
            options.setBinary(PATH_TO_YANDEX_BROWSER);
        } else {
            options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
        }
        webDriver = new ChromeDriver(options);
        webDriver.get(BASE_URL);
    }

    protected void after() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }

    public WebDriver getWebDriver() {
        return webDriver;
    }
}
