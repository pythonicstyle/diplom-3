package constants;

public class Constants {
    public static final String NAME = "Yuri";
    public static final String EMAIL = "YuriTitov@gmai.com";
    public static final String RANDOM_EMAIL = "YuriTitov_" + (int)(Math.random() * 1000) + "@gmail.com";
    public static final String PASSWORD = "Yuri100500";
    public static final String INCORRECT_PASSWORD = "0000";

    public static final String BASE_URL = "https://stellarburgers.nomoreparties.site";
    public static final String API_AUTH_REGISTER = "/api/auth/register";
    public static final String API_AUTH_LOGIN = "/api/auth/login";
    public static final String API_AUTH_USER = "/api/auth/user";

    public static final String BROWSER = System.getProperty("browser");
    public static final String DRIVER = "webdriver.chrome.driver";
    public static final String PATH_TO_YANDEX_BROWSER = "C:\\Users\\agaranin\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe";
    public static final String PATH_TO_YANDEX_DRIVER = "C:\\WebDriver\\yandexdriver.exe";
}
