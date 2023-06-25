package com.solvd.common.components;

import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;

public abstract class SideBarBase extends AbstractUIObject {

    public SideBarBase(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public abstract String getActiveCategoryTitle();
}
