package com.solvd.common.pages;

import com.qaprosoft.carina.core.gui.AbstractPage;
import com.solvd.common.components.CookieDialogBase;
import com.solvd.common.components.MainMenuBase;
import org.openqa.selenium.WebDriver;

public abstract class HomePageBase extends AbstractPage {

    public HomePageBase(WebDriver driver) {
        super(driver);
    }

    public abstract HomePageBase goToPage();

    public abstract CookieDialogBase getCookieDialog();

    public abstract MainMenuBase getMainMenu();
}
