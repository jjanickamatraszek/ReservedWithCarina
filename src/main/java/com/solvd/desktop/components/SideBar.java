package com.solvd.desktop.components;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class SideBar extends AbstractUIObject {

    @FindBy(css = "li.active")
    private ExtendedWebElement activeCategory;

    public SideBar(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public String getActiveCategoryTitle() {
        return activeCategory.getText();
    }
}
