package rules;

import org.junit.rules.ExternalResource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


public class BrowserRule extends ExternalResource {
    public static final String BROWSER = System.getProperty("browser");
    public static final String URL = "https://stellarburgers.nomoreparties.site";
    private WebDriver webDriver;


    protected void before() {

        ChromeOptions options = new ChromeOptions();

        if ("yandex".equals(BROWSER)) {
            System.setProperty("webdriver.chrome.driver", "C:\\WebDriver\\yandexdriver.exe");
            options.setBinary("C:\\Users\\agaranin\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe");
        } else {
            options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
        }
        webDriver = new ChromeDriver(options);
        webDriver.get(URL);
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
