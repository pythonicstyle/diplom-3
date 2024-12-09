package rules;

import static config.BrowserConfig.*;

import org.junit.rules.ExternalResource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


public class BrowserRule extends ExternalResource {
    private WebDriver webDriver;
    protected void before() {

        ChromeOptions options = new ChromeOptions();

        if ("yandex".equals(BROWSER)) {
            String driverPath = System.getenv("YANDEX_DRIVER_PATH");
            String binaryPath = System.getenv("YANDEX_BINARY_PATH");
            System.setProperty(DRIVER, driverPath);
            options.setBinary(binaryPath);
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
