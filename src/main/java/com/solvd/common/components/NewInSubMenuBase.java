package com.solvd.common.components;

import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import com.solvd.common.pages.NewInSubCatPageBase;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;

public abstract class NewInSubMenuBase extends AbstractUIObject {

    public NewInSubMenuBase(WebDriver driver) {
        super(driver);
    }

    public NewInSubMenuBase(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public abstract boolean isSubMenuDisplayed();

    public abstract int getNumberOfSubcategories();

    public abstract long getNumberOfSubcategoriesWithoutImg();

    public abstract long getNumberOfSubcategoriesWithoutTitle();

    public abstract NewInSubCatPageBase clickOnSubcategory(String title);

}
