package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by Ron on 13/10/2017.
 */
public class AuthenticationPage extends AmaysimPage<AuthenticationPage> {
    private static final String USER_NAME = "username";
    private static final String PASSWORD = "password";
    private static final String LOGIN_BUTTON = "button";

    @Override
    public void isLoaded() {
        element().waitForJavaScriptReadiness(10);
        element().waitForElementExists(By.id(USER_NAME), 5);
    }

    private WebElement numberEl() {
        return element().find(By.id(USER_NAME));
    }

    private WebElement passwordEl() {
        return element().find(By.id(PASSWORD));
    }

    private WebElement loginEl() {
        return element().find(By.name(LOGIN_BUTTON));
    }

    public AuthenticationPage enterNumber(String number) {
        numberEl().sendKeys(number);
        return this;
    }

    public AuthenticationPage enterPassword(String password) {
        passwordEl().sendKeys(password);
        return this;
    }

    /**
     * Select the login button. NOTE: For the purposes of this demo, assume it's always a successful login,
     * hence returning an instance of the Dashboard page instead of void.
     *
     * @return new Dashboard page instance
     */
    public DashboardPage selectLogin() {
        loginEl().click();
        return new DashboardPage();
    }
}
