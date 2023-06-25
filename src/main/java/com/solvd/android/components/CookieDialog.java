package com.solvd.android.components;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.solvd.common.components.CookieDialogBase;
import com.zebrunner.carina.utils.factory.DeviceType;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = CookieDialogBase.class)
public class CookieDialog extends CookieDialogBase {

    @FindBy(css = "#cookiebotDialogOkButton")
    private ExtendedWebElement okBtn;

    public CookieDialog(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    @Override
    public void acceptCookies() {
        okBtn.clickIfPresent(3);
    }
}
