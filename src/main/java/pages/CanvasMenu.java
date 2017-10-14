package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by Ron on 13/10/2017.
 * NOTE: This is treated as a page for the purpose of the demo - in reality it could be its own container type (e.g. panel)
 */
public class CanvasMenu extends AmaysimPage<CanvasMenu> {
    private static final String CANVAS_CONTAINER = "main_menu_container";
    private static final String SETTINGS = "Settings";

    @Override
    public void isLoaded() {
        element().waitForElementExists(By.id(CANVAS_CONTAINER), 5);
    }

    private WebElement canvasContainerEl() {
        return element().find(By.id(CANVAS_CONTAINER));
    }

    private List<WebElement> canvasElList() {
        return element().findAll(canvasContainerEl(), By.cssSelector(LI + " " + ANCHOR_TAG + " " + SPAN));
    }

    private WebElement settingsEl() {
        return canvasElList().stream().filter(element -> SETTINGS.equals(element.getText())).findFirst().get();
    }

    public SettingsPage selectSettings() {
        settingsEl().click();
        return new SettingsPage().get();
    }

}
