package com.solvd.desktop.pages;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.solvd.common.pages.ProductPageBase;
import com.zebrunner.carina.utils.factory.DeviceType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

@DeviceType(pageType = DeviceType.Type.DESKTOP, parentClass = ProductPageBase.class)
public class ProductPage extends ProductPageBase {

    @FindAll({@FindBy(css = "h1.product-name"), @FindBy(css = "h1[data-testid='product-name']")})
    private ExtendedWebElement titleLabel;

    @FindBy(css = "div.basic-price")
    private ExtendedWebElement priceLabel;

    @FindBy(css = "div[data-testid='gallery'] img")
    private ExtendedWebElement mainImg;

    @FindAll({@FindBy(xpath = ".//label[@data-selen='description']"), @FindBy(css = "div[data-testid='description']")})
    private ExtendedWebElement descriptionLabel;

    @FindAll({@FindBy(xpath = ".//label[@data-selen='description']/following-sibling::div//div[contains(@class,'content')]"),
            @FindBy(css = "div[data-testid ='description']+div div[class^='content-description']")})
    private ExtendedWebElement descriptionContent;

    @FindAll({@FindBy(xpath = ".//label[@data-selen='composition']"),
    @FindBy(css = "div[data-testid='composition']")})
    private ExtendedWebElement materialAndCareLabel;

    @FindAll({@FindBy(xpath = ".//label[@data-selen='composition']/following-sibling::div//div[contains(@class,'content')]"),
            @FindBy(css = "div[data-testid ='composition']+div div[class^='content']")})
    private ExtendedWebElement materialAndCareContent;

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public String getTitle() {
        return titleLabel.getText();
    }

    public String getPrice() {
        return priceLabel.getText();
    }

    public boolean mainImageIsDisplayed() {
        return mainImg.isVisible();
    }

    public boolean isDescriptionExpanded() {
        return descriptionContent.isVisible(1);
    }

    public String getDescriptionText() {
        return descriptionContent.getText();
    }

    public boolean isMaterialAndCareExpanded() {
        return materialAndCareContent.isVisible(1);
    }

    public String getMaterialAndCareText() {
        return materialAndCareContent.getText();
    }

    public ProductPageBase expandCollapseMaterialAndCareSection() {
        return expandCollapseSection(materialAndCareLabel);
    }

    public ProductPageBase expandCollapseDescriptionSection() {
        return expandCollapseSection(descriptionLabel);
    }

    private ProductPageBase expandCollapseSection(ExtendedWebElement label) {
        label.click();
        return this;
    }
}
