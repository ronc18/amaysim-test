package demo.settingstest;

import manager.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.*;

/**
 * Created by Ron on 14/10/2017.
 */
public class SettingsIT {
    private static final String AMAYSIM_URL = "https://accounts.amaysim.com.au";
    private static final String USER_NUMBER = "0468340754";
    private static final String PASSWORD = "theHoff34";

    private AuthenticationPage authenticationPage;
    private DashboardPage dashboardPage;
    private CanvasMenu canvasMenu;
    private SettingsPage settingsPage;

    @BeforeMethod
    public void testSetUp() {
        driver().get(AMAYSIM_URL);
        driver().manage().window().maximize();
        login();
    }

    private WebDriver driver() {
        return DriverManager.getInstance();
    }

    /**
     * Login with a default username & password. NOTE: This would be parameterised if login details were to differ.
     */
    private void login() {
        authenticationPage = new AuthenticationPage().get();
        authenticationPage.enterNumber(USER_NUMBER);
        authenticationPage.enterPassword(PASSWORD);
        dashboardPage = authenticationPage.selectLogin().get(); // wait for the Dashboard page to load
    }

    private void navigateToSettings() {
        dashboardPage.selectServiceNumber(USER_NUMBER);
        canvasMenu = new CanvasMenu().get();
        settingsPage = canvasMenu.selectSettings().get();
    }

    /*
     * For the purpose of this test, assume none of the call settings (Caller ID,
     * Call waiting, & Voicemail) have been set. Script will set these 3 values (after
     * checking that they have not been set; if they have been set it does not uncheck them)
     */
    @Test(groups = "settings")
    public void setCallSettingsOptions() {
         navigateToSettings();

         settingsPage.selectCallerIDDisplay();
         settingsPage.selectCallWaiting();
         settingsPage.selectVoiceMail();

         Assert.assertTrue(settingsPage.isCallerIDSelected(), "Caller ID has not been set");
         Assert.assertTrue(settingsPage.isCallWaitingSelected(), "Call Waiting has not been set");
         Assert.assertTrue(settingsPage.isVoiceMailSelected(), "Voice Mail has not been set");
    }

    /**
     * For the purpose of this test demo, assume call forwarding is not set (the method to select it checks first
     * if it has been set so that it does not accidentally deselect the option). Test would require a real number
     * to complete, so use an invalid number, which would cause validation
     */
    @Test(groups = "settings")
    public void setCallForwardingOption() {
        navigateToSettings();

        settingsPage.selectCallForwarding();
        new ConfirmationPopUp().get().selectConfirmBtn();
        // Enter number here - for demo purpose, don't use real data, or confirm. Would assert after
        settingsPage.enterForwardCallsToAndSave("123456789");   // Validation will occur at this point
    }

    @Test
    public void editSimNickName() {
        String editedName = "autotest_rspec 1";

        navigateToSettings();
        settingsPage.editSimNickName(editedName);

        Assert.assertEquals(settingsPage.getSimNickname(), editedName, "Sim Nick Name after Edit does not match expected");
    }

    @AfterMethod
    public void tearDown() {
        DriverManager.quitSession();
    }

}
