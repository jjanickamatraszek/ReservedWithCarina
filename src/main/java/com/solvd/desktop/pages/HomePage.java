package com.solvd.desktop.pages;

import com.qaprosoft.carina.core.gui.AbstractPage;
import com.solvd.desktop.components.CookieDialog;
import com.solvd.desktop.components.MainMenu;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class HomePage extends AbstractPage {

    @FindBy(css = "ul[data-testid='category-list']")
    @Getter
    private MainMenu mainMenu;

    @FindBy(css = "#cookiebanner")
    @Getter
    private CookieDialog cookieDialog;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public HomePage goToPage() {
        open();
        return this;
    }
}
