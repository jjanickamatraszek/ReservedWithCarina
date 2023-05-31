package com.solvd.iOS.components;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.solvd.common.components.ProductFiltersBase;
import com.solvd.common.components.enums.SortOption;
import com.solvd.common.pages.NewInSubCatPageBase;
import com.solvd.iOS.pages.NewInSubCatPage;
import com.zebrunner.carina.utils.factory.DeviceType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.math.BigDecimal;

@DeviceType(pageType = DeviceType.Type.IOS_PHONE, parentClass = ProductFiltersBase.class)
public class ProductFilters extends ProductFiltersBase {
    private static final String THIS_METHOD_IS_DEFINED_ONLY_FOR_DESKTOP = "This method is not applicable for browsers on mobiles";

    @FindBy(xpath = ".//div[@id='formGroups']//label[contains(text(),'Sort by')]")
    private ExtendedWebElement sortByFilterLabel;

    @FindBy(xpath = ".//*[@for='sortBy-%s']")
    private ExtendedWebElement sortOptionLabel;

    @FindBy(xpath = ".//label[text()='Price']")
    private ExtendedWebElement priceFilterLabel;

    @FindBy(css = "#priceFrom")
    private ExtendedWebElement priceFromTextField;

    @FindBy(css = "#priceTo")
    private ExtendedWebElement priceToTextField;

    @FindBy(css = "#formGroups+div>button[type='submit']")
    private ExtendedWebElement filterBtn;

    public ProductFilters(WebDriver driver) {
        super(driver);
        setUiLoadedMarker(priceFilterLabel);
    }

    @Override
    public NewInSubCatPageBase sortBy(SortOption sortOption) {
        if (!sortByFilterLabel.getAttribute("class").contains("active")) {
            sortByFilterLabel.click();
            waitUntil(ExpectedConditions.visibilityOf(sortOptionLabel.format(sortOption.getValue()).getElement()), 10);
        }
        sortOptionLabel.format(sortOption.getValue()).click();
        filterBtn.click();
        return new NewInSubCatPage(driver);
    }

    @Override
    public int getNumberOfActiveSortFilters() {
        throw new UnsupportedOperationException(THIS_METHOD_IS_DEFINED_ONLY_FOR_DESKTOP);
    }

    @Override
    public NewInSubCatPageBase filterByPriceRange(BigDecimal priceFrom, BigDecimal priceTo) {
        if (!priceFilterLabel.getAttribute("class").contains("active")) {
            priceFilterLabel.click();
            waitUntil(ExpectedConditions.visibilityOf(priceFromTextField.getElement()), 10);
        }
        priceFromTextField.type(String.valueOf(priceFrom));
        priceToTextField.type(String.valueOf(priceTo));
        filterBtn.click();
        return new NewInSubCatPage(driver);
    }

    @Override
    public int getNumberOfActivePriceFilters() {
        throw new UnsupportedOperationException(THIS_METHOD_IS_DEFINED_ONLY_FOR_DESKTOP);
    }
}
