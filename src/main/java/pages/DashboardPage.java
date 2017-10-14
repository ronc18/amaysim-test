package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Ron on 13/10/2017.
 */
public class DashboardPage extends AmaysimPage<DashboardPage> {
    private static final String DASHBOARD_BASE = "amaysim-components-theme";
    private static final String SERVICE_NUMBER = "obwPG";

    @Override
    public void isLoaded() {
        element().waitForJavaScriptReadiness(10);
        element().waitForElementExists(By.className(DASHBOARD_BASE), 5);
    }

    private List<WebElement> serviceNumberList() {
        return element().findAll(By.className(SERVICE_NUMBER));
    }

    public void selectServiceNumber(String number) {
        WebElement serviceEl = serviceNumberList().stream().filter(webElement ->
                webElement.getText().replace(" ", "").startsWith(number.replace(" ", ""))).findFirst().get();
        serviceEl.click();
    }
}
