package com.solvd.desktop.components;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.solvd.common.components.MainMenuBase;
import com.solvd.common.components.NewInSubMenuBase;
import com.zebrunner.carina.utils.factory.DeviceType;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

@DeviceType(pageType = DeviceType.Type.DESKTOP, parentClass = MainMenuBase.class)
public class MainMenu extends MainMenuBase {

    @FindBy(xpath = ".//span[contains(text(),'new in')]/parent::li")
    private ExtendedWebElement newInCategory;

    public MainMenu(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public NewInSubMenuBase expandNewInSubMenu() {
        newInCategory.hover();
        return new NewInSubMenu(driver);
    }
}
