package com.solvd.common.pages;

import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;

public abstract class ProductPageBase extends AbstractPage {

    public ProductPageBase(WebDriver driver) {
        super(driver);
    }

    public abstract String getTitle();

    public abstract String getPrice();

    public abstract boolean mainImageIsDisplayed();

    public abstract boolean isDescriptionExpanded();

    public abstract String getDescriptionText();

    public abstract boolean isMaterialAndCareExpanded();

    public abstract String getMaterialAndCareText();

    public abstract ProductPageBase expandCollapseMaterialAndCareSection();

    public abstract ProductPageBase expandCollapseDescriptionSection();
}
