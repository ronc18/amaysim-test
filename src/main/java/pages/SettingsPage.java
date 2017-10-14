package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by Ron on 13/10/2017.
 */
public class SettingsPage extends AmaysimPage<SettingsPage> {
    private static final String SIM_NICKNAME = "settings_sim_nickname";
    private static final String EDIT_SIM_NICKNAME_TXT_FIELD = "my_amaysim2_setting_phone_label";
    private static final String EDIT_SIM_NICKNAME_BTN = "edit_settings_phone_label";
    private static final String PLAN_SETTINGS = "ama-auto-renew-demo.settingstest";
    private static final String PUK_CODE = "settings_puk_code";
    private static final String CALLER_ID_CHK_BOX = "my_amaysim2_setting_caller_id_out"; // Assume this is non-dynamic
    private static final String CALL_WAITING_CHK_BOX = "my_amaysim2_setting_caller_waiting";
    private static final String VOICE_MAIL_CHK_BOX = "my_amaysim2_setting_voice_mail";
    private static final String USAGE_ALERT_CHK_BOX = "my_amaysim2_setting_usage_alert";
    private static final String INTERNATIONAL_ROAMING_CHK_BOX = "my_amaysim6_setting_intl_roaming";

    private static final String CALL_FORWARDING = "settings_call_forwarding";
    private static final String CALL_FORWARDING_TXT_BOX = "my_amaysim2_setting_call_divert_number";
    private static final String EDIT_CALL_FORWARD_SETTINGS = "edit_settings_call_forwarding";

    private static final String SAVE_BTN = "commit";
    private static final String OPTION_TEXT_SETTING = "setting-option-details-text";
    private static final String OPTION_VALUE_SETTING = "setting-option-value-text";

    private static final String PREMIUM_SMS_SETTING = "settings_premium_sms_limit";
    private static final String AUTO_RECHARGE_SETTING = "settings_auto_recharge";

    @Override
    public void isLoaded() {
        element().waitForJavaScriptReadiness(12);
        element().waitForElementExists(By.id(SIM_NICKNAME), 10);
    }

    private WebElement simNicknameEl() {
        return element().find(By.id(SIM_NICKNAME));
    }

    private WebElement editSimNickNameEl() {
        return simNicknameEl().findElement(By.id(EDIT_SIM_NICKNAME_BTN));
    }

    private WebElement simNickNameTxtField() {
        return element().find(By.id(EDIT_SIM_NICKNAME_TXT_FIELD));
    }

    private WebElement planSettingsEl() {
        return element().find(By.className(PLAN_SETTINGS));
    }

    private WebElement pukCodeEl() {
        return element().find(By.id(PUK_CODE));
    }

    private WebElement callerIdChkBoxEl() {
        return element().find(By.id(CALLER_ID_CHK_BOX));
    }

    private WebElement callWaitingChkBoxEle() {
        return element().find(By.id(CALL_WAITING_CHK_BOX));
    }

    private WebElement callForwardingTxtBox() {
        return element().find(By.id(CALL_FORWARDING_TXT_BOX));
    }

    private WebElement editCallForwardSettingsEl() {
        return element().find(By.id(EDIT_CALL_FORWARD_SETTINGS));
    }

    private WebElement voiceMailChkBoxEl() {
        return element().find(By.id(VOICE_MAIL_CHK_BOX));
    }

    private WebElement internationalRoamingEl() {
        return element().find(By.id(INTERNATIONAL_ROAMING_CHK_BOX));
    }

    private WebElement usageAlertEl() {
        return element().find(By.id(USAGE_ALERT_CHK_BOX));
    }

    private WebElement callForwardingEl() {
        return element().find(By.id(CALL_FORWARDING));
    }

    private WebElement premiumSMSLimitEl() {
        return element().find(By.id(PREMIUM_SMS_SETTING));
    }

    private WebElement autoRechargeEl() {
        return element().find(By.id(AUTO_RECHARGE_SETTING));
    }

    private String getOptionText(WebElement element) {
        return element().find(element, By.className(OPTION_TEXT_SETTING)).getText();
    }

    private WebElement saveBtnEl(WebElement baseEl) {
        return baseEl.findElement(By.name(SAVE_BTN));
    }

    private boolean isChecked(WebElement ele) {
        return ele.getAttribute(VALUE).equals("1");
    }

    private boolean isOptionSet(WebElement element) {
        return "Yes".equals(element.findElement(By.className(OPTION_VALUE_SETTING)).getText());
    }

    public SettingsPage selectSaveNickNameEdit() {
        element().clickByJavaScript(saveBtnEl(simNicknameEl()));
        return this.get();
    }

    public String getSimNickname() {
        return getOptionText(simNicknameEl());
    }

    public String getPlanPaymentType() {
        return planSettingsEl().findElement(By.tagName(ANCHOR_TAG)).getText();
    }

    public String getPukCode() {
        return getOptionText(pukCodeEl());
    }

    public boolean isCallerIDSelected() {
        return isChecked(callerIdChkBoxEl());
    }

    public boolean isCallWaitingSelected() {
        return isChecked(callWaitingChkBoxEle());
    }

    public boolean isVoiceMailSelected() {
        return isChecked(voiceMailChkBoxEl());
    }

    public boolean isInternationalRoamingSelected() {
        return isChecked(internationalRoamingEl());
    }

    public boolean isUsageAlertSelected() {
        return isChecked(usageAlertEl());
    }

    public void selectCallerIDDisplay() {
        WebElement callerIDEle = callerIdChkBoxEl();
        if (!isChecked(callerIDEle)) {
            element().clickByJavaScript(callerIDEle);
            new SuccessPopUp().get().closeWindow();
        }
        else {
            System.out.println("Caller ID was not set, as it had already been set");
        }
    }

    public void deSelectCallerIdDisplay() {
        if (isChecked(callerIdChkBoxEl())) {
            callerIdChkBoxEl().click();
            new SuccessPopUp().get().closeWindow();
        }
        else {
            System.out.println("Caller ID was not de-selected, as it had already been de-selected");
        }
    }

    public void selectCallWaiting() {
        if (isCallWaitingSelected()) {
            element().clickByJavaScript(callWaitingChkBoxEle());
            new SuccessPopUp().get().closeWindow();
        }
        else {
            System.out.println("Call waiting was not set, as it had already been set");
        }
    }

    public void selectVoiceMail() {
        if (isVoiceMailSelected()) {
            element().clickByJavaScript(voiceMailChkBoxEl());
            new SuccessPopUp().get().closeWindow();
        }
        else {
            System.out.println("Voicemail was not set, as it had already been set");
        }
    }

    public boolean isCallForwardingSet() {
        return isOptionSet(callForwardingEl());
    }

    public boolean isPremiumSMSSet() {
        return isOptionSet(premiumSMSLimitEl());
    }

    public boolean isAutoRechargeSet() {
        return isOptionSet(autoRechargeEl());
    }

    public SettingsPage editSimNickName(String nickName) {
        editSimNickNameEl().click();
        WebElement nickNameEle = simNickNameTxtField();
        nickNameEle.clear();
        nickNameEle.sendKeys(nickName);
        return selectSaveNickNameEdit();
    }

    public void selectCallForwarding() {
        if (!isCallForwardingSet()) {
            editCallForwardSettingsEl().click();
        }
    }

    public SettingsPage enterForwardCallsToAndSave(String number) {
        WebElement callForwarding = callForwardingTxtBox();
        callForwarding.sendKeys(number);
        saveBtnEl(callForwardingEl()).click();
        return this;
    }

    public void deSelectCallForwarding() {
        if (isCallForwardingSet()) {
            editCallForwardSettingsEl().click();
        }
    }
}
