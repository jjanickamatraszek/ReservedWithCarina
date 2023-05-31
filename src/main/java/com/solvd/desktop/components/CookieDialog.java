package com.solvd.desktop.components;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.solvd.common.components.CookieDialogBase;
import com.zebrunner.carina.utils.factory.DeviceType;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

@DeviceType(pageType = DeviceType.Type.DESKTOP, parentClass = CookieDialogBase.class)
public class CookieDialog extends CookieDialogBase {

    @FindBy(css = "#cookiebotDialogOkButton")
    private ExtendedWebElement okBtn;

    public CookieDialog(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public void acceptCookies() {
        okBtn.clickIfPresent(3);
    }
}
