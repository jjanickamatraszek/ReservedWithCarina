package com.solvd.android.components;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.solvd.common.components.MainMenuBase;
import com.solvd.common.components.NewInSubMenuBase;
import com.zebrunner.carina.utils.factory.DeviceType;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = MainMenuBase.class)
public class MainMenu extends MainMenuBase {

    @FindBy(xpath = ".//div[@id='nm23716']/parent::div")
    private ExtendedWebElement newInCategory;

    public MainMenu(WebDriver driver) {
        super(driver);
    }

    public MainMenu(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    @Override
    public NewInSubMenuBase expandNewInSubMenu() {
        newInCategory.click();
        return new NewInSubMenu(driver);
    }
}
