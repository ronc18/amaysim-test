package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by Ron on 13/10/2017.
 */
public class ConfirmationPopUp extends AmaysimPage<ConfirmationPopUp> {
    private static final String POP_UP_CONTAINER = "form_confirm_popup";
    private static final String CONFIRM_BTN_STR = "confirm_popup_confirm";

    @Override
    public void isLoaded() {
        element().waitForJavaScriptReadiness(20);
        element().waitForElementExists(By.className(POP_UP_CONTAINER), 10);
    }

    private WebElement confirmBtnEl() {
        return element().find(By.className(CONFIRM_BTN_STR));
    }

    public SettingsPage selectConfirmBtn() {
        element().clickByJavaScript(confirmBtnEl());
        return new SettingsPage().get();
    }

}
