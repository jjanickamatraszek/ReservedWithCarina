package com.solvd.android.nativeChromePages;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class ChromeMainPage extends AbstractPage {

    @FindBy(id = "com.android.chrome:id/home_button")
    private ExtendedWebElement homeBtn;

    public ChromeMainPage(WebDriver driver) {
        super(driver);
    }

    public GoogleHomePage goToHomePage() {
        homeBtn.click();
        return new GoogleHomePage(driver);
    }
}
