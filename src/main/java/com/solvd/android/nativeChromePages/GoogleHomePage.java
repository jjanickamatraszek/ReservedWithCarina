package com.solvd.android.nativeChromePages;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class GoogleHomePage extends AbstractPage {

    @FindBy(id = "com.android.chrome:id/search_provider_logo")
    private ExtendedWebElement googleLogo;

    @FindBy(id = "com.android.chrome:id/search_box_text")
    private ExtendedWebElement searchTextField;

    @FindBy(id = "com.android.chrome:id/url_bar")
    private ExtendedWebElement urlBar;

    @FindBy(xpath = ".//*[@resource-id='com.android.chrome:id/line_1']")
    private List<ExtendedWebElement> searchResulstList;

    public GoogleHomePage(WebDriver driver) {
        super(driver);
    }

    public boolean isGoogleLogoDisplayed() {
        return googleLogo.isVisible();
    }

    public void searchFirstEntryForPhrase(String url) {
        searchTextField.sendKeys(Keys.ENTER);
        urlBar.type(String.valueOf(url));
        searchResulstList
                .stream()
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No entries for given search phrase"))
                .click();
    }
}
