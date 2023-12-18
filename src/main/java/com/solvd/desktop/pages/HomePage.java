package com.solvd.desktop.pages;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.solvd.common.components.CookieDialogBase;
import com.solvd.common.components.MainMenuBase;
import com.solvd.common.pages.HomePageBase;
import com.solvd.desktop.components.CookieDialog;
import com.solvd.desktop.components.MainMenu;
import com.zebrunner.carina.utils.factory.DeviceType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

@DeviceType(pageType = DeviceType.Type.DESKTOP, parentClass = HomePageBase.class)
public class HomePage extends HomePageBase {

    @FindBy(css = "ul[data-testid='category-list']")
    private MainMenu mainMenu;

    @FindBy(css = "#cookiebanner")
    private CookieDialog cookieDialog;

    @FindBy(css = "[data-testid='brand-logo-button']")
    private ExtendedWebElement logo;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public HomePageBase     goToPage() {
        open();
        return this;
    }

    @Override
    public CookieDialogBase getCookieDialog() {
        return new CookieDialog(getDriver(), cookieDialog.getRootExtendedElement().getElement());
    }

    @Override
    public MainMenuBase getMainMenu() {
        return new MainMenu(getDriver(), mainMenu.getRootExtendedElement().getElement());
    }

    @Override
    public boolean isLogoVisible() {
        return logo.isVisible();
    }
}
