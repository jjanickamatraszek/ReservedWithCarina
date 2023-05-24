package com.solvd.components;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class CookieDialog extends AbstractUIObject {

    @FindBy(css = "#cookiebotDialogOkButton")
    private ExtendedWebElement okBtn;

    public CookieDialog(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public void acceptCookies() {
        okBtn.clickIfPresent(3);
    }
}
