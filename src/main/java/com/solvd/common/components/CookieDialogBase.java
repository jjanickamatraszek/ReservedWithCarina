package com.solvd.common.components;

import com.qaprosoft.carina.core.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;

public abstract class CookieDialogBase extends AbstractUIObject {

    public CookieDialogBase(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public abstract void acceptCookies();
}
