package com.solvd.components;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class MainMenu extends AbstractUIObject {

    @FindBy(xpath = ".//span[contains(text(),'new in')]/parent::li")
    private ExtendedWebElement newInCategory;

    public MainMenu(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public NewInSubMenu expandNewInSubMenu() {
        newInCategory.hover();
        return new NewInSubMenu(driver);
    }
}
