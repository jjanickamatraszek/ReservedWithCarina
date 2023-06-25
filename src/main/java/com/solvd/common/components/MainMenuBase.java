package com.solvd.common.components;

import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;

public abstract class MainMenuBase extends AbstractUIObject {

    public MainMenuBase(WebDriver driver) {
        super(driver);
    }

    public MainMenuBase(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public abstract NewInSubMenuBase expandNewInSubMenu();
}
