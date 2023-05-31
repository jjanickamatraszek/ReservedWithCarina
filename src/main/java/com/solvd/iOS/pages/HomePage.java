package com.solvd.iOS.pages;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.solvd.common.components.CookieDialogBase;
import com.solvd.common.components.MainMenuBase;
import com.solvd.common.pages.HomePageBase;
import com.solvd.iOS.components.CookieDialog;
import com.solvd.iOS.components.MainMenu;
import com.zebrunner.carina.utils.factory.DeviceType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

@DeviceType(pageType = DeviceType.Type.IOS_PHONE, parentClass = HomePageBase.class)
public class HomePage extends HomePageBase{

    @FindBy(xpath = ".//button[starts-with(@class,'hamburger')]")
    private ExtendedWebElement mainMenuBtn;

    @FindBy(css = "#cookiebanner")
    private CookieDialog cookieDialog;

    @FindBy(css = "[data-testid='brand-logo-button']")
    private ExtendedWebElement logo;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public HomePageBase goToPage() {
        open();
        return initPage(getDriver(), HomePageBase.class);
    }

    @Override
    public CookieDialogBase getCookieDialog() {
        return new CookieDialog(getDriver(), cookieDialog.getRootExtendedElement().getElement());
    }

    @Override
    public MainMenuBase getMainMenu() {
        mainMenuBtn.click();
        return new MainMenu(getDriver());
    }

    public boolean isLogoVisible(){
        return logo.isVisible();
    }
}
