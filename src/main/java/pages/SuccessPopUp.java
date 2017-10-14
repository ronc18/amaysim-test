package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by Ron on 13/10/2017.
 */
public class SuccessPopUp extends AmaysimPage<SuccessPopUp> {
    private static final String POP_UP_CONTAINER = "form_info_popup";
    private static final String POP_UP_SUCCESS = "popup-success";
    private static final String CLOSE_WINDOW = "close-reveal-modal";

    @Override
    public void isLoaded() {
        element().waitForJavaScriptReadiness(20);
        element().waitForElementExists(By.className(POP_UP_CONTAINER), 20);
    }

    private WebElement closeWindowEl() {
        return element().find(By.className(CLOSE_WINDOW));
    }

    public boolean isSuccessful() {
        return element().isPresentImmediately(By.className(POP_UP_SUCCESS));
    }

    public void closeWindow() {
        element().clickByJavaScript(closeWindowEl());
    }
}
