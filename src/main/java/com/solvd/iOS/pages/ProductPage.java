package com.solvd.iOS.pages;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.solvd.common.pages.ProductPageBase;
import com.zebrunner.carina.utils.factory.DeviceType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

@DeviceType(pageType = DeviceType.Type.IOS_PHONE, parentClass = ProductPageBase.class)
public class ProductPage extends ProductPageBase {

    @FindAll({@FindBy(css = "h1.product-name"), @FindBy(css = "h1[data-testid='product-name']")})
    private ExtendedWebElement titleLabel;

    @FindBy(css = "div.basic-price")
    private ExtendedWebElement priceLabel;

    @FindBy(css = "[data-selen='gallery']>[class*='MainPhoto'] .bWWRbz")
    private ExtendedWebElement mainImg;

    @FindBy(xpath = ".//label[@data-selen='description']/parent::div")
    private ExtendedWebElement descriptionLabelParent;

    @FindAll({@FindBy(xpath = ".//label[@data-selen='description']"), @FindBy(css = "div[data-testid='description']")})
    private ExtendedWebElement descriptionLabel;

    @FindBy(css = "label[data-selen='description']+div")
    private ExtendedWebElement descriptionContent;

    @FindBy(xpath = ".//label[@data-selen='composition']/parent::div")
    private ExtendedWebElement materialAndCareLabelParent;

    @FindAll({@FindBy(xpath = ".//label[@data-selen='composition']"), @FindBy(css = "div[data-testid='composition']")})
    private ExtendedWebElement materialAndCareLabel;

    @FindBy(css = "label[data-selen='composition']+div")
    private ExtendedWebElement materialAndCareContent;

    @FindBy(css = "div[data-qa-id='close-button']")
    private ExtendedWebElement closeDownloadMobileAppDialogBtn;

    public ProductPage(WebDriver driver) {
        super(driver);
        setUiLoadedMarker(mainImg);
        closeDownloadMobileAppDialogBtn.clickIfPresent(3);
    }

    @Override
    public String getTitle() {
        return titleLabel.getText();
    }

    @Override
    public String getPrice() {
        return priceLabel.getText();
    }

    @Override
    public boolean mainImageIsDisplayed() {
        return mainImg.isVisible();
    }

    @Override
    public boolean isDescriptionExpanded() {
        return descriptionContent.isVisible(3);
    }

    @Override
    public String getDescriptionText() {
        return descriptionContent.getText();
    }

    @Override
    public boolean isMaterialAndCareExpanded() {
        return materialAndCareContent.isVisible(3);
    }

    @Override
    public String getMaterialAndCareText() {
        return materialAndCareContent.getText();
    }

    @Override
    public ProductPageBase expandCollapseMaterialAndCareSection() {
        if (descriptionLabelParent.getAttribute("class").contains("expanded")) {
            materialAndCareLabel.click();
            waitUntil(ExpectedConditions.invisibilityOf(materialAndCareContent.getElement()), 5);
        } else {
            materialAndCareLabel.click();
            waitUntil(ExpectedConditions.visibilityOf(materialAndCareContent.getElement()), 5);
        }
        return this;
    }

    @Override
    public ProductPageBase expandCollapseDescriptionSection() {
        if (materialAndCareLabelParent.getAttribute("class").contains("expanded")) {
            descriptionLabel.click();
            waitUntil(ExpectedConditions.invisibilityOf(descriptionContent.getElement()), 5);
        } else {
            descriptionLabel.click();
            waitUntil(ExpectedConditions.visibilityOf(descriptionContent.getElement()), 5);
        }
        return this;
    }
}
