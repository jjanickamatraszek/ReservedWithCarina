package com.solvd.desktop.components;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.solvd.common.components.SideBarBase;
import com.zebrunner.carina.utils.factory.DeviceType;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

@DeviceType(pageType = DeviceType.Type.DESKTOP, parentClass = SideBarBase.class)
public class SideBar extends SideBarBase {

    @FindBy(css = "li.active")
    private ExtendedWebElement activeCategory;

    public SideBar(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public String getActiveCategoryTitle() {
        return activeCategory.getText();
    }
}
