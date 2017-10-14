package manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Objects;

/**
 * Created by Ron on 13/10/2017.
 */
public class DriverManager {
    private static final ThreadLocal<WebDriver> driverInstance = new ThreadLocal<>();
    private static final String USER_DIRECTORY = "user.dir";
    private static final String MAIN_RESOURCES = "/src/main/resources/";

    private DriverManager() {
        // private constructor to prevent instantiation
    }

    public static WebDriver getInstance() {
        WebDriver driver = driverInstance.get();

        if (Objects.isNull(driver)) {
            startInstance();
            driver = driverInstance.get();
        }
        return driver;
    }

    private static void startInstance() {
        /*
         * Only setup for Chrome for demo purposes. Assume we won't be running multi-threaded, so no need to make
         * thread-safe.
         */
        String chromeSystemProp = "webdriver.chrome.driver";
        String chromePath = System.getProperty(chromeSystemProp);
        if (Objects.isNull(chromePath)) {
            chromePath = System.getProperty(USER_DIRECTORY) + MAIN_RESOURCES + "chromedriver.exe";
            System.setProperty(chromeSystemProp, chromePath);
        }
        driverInstance.set(new ChromeDriver());
    }

    public static void quitSession() {
        WebDriver driver = driverInstance.get();
        if (!Objects.isNull(driver)) {
            driver.quit();
            driverInstance.remove();
        }
    }

}
