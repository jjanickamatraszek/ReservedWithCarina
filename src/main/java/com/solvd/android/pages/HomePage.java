package com.solvd.android.pages;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.solvd.android.components.CookieDialog;
import com.solvd.android.components.MainMenu;
import com.solvd.common.components.CookieDialogBase;
import com.solvd.common.components.MainMenuBase;
import com.solvd.common.pages.HomePageBase;
import com.zebrunner.carina.utils.factory.DeviceType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = HomePageBase.class)
public class HomePage extends HomePageBase {

    @FindBy(xpath = ".//button[starts-with(@class,'hamburger')]")
    private ExtendedWebElement mainMenuBtn;

    @FindBy(css = "#cookiebanner")
    private CookieDialog cookieDialog;

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
}
