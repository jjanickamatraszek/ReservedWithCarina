package com.solvd.android.components;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.solvd.android.pages.NewInSubCatPage;
import com.solvd.common.components.ProductFiltersBase;
import com.solvd.common.components.enums.SortOption;
import com.solvd.common.pages.NewInSubCatPageBase;
import com.zebrunner.carina.utils.factory.DeviceType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.math.BigDecimal;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = ProductFiltersBase.class)
public class ProductFilters extends ProductFiltersBase {
    private static final String THIS_METHOD_IS_DEFINED_ONLY_FOR_DESKTOP = "This method is not applicable for browsers on mobiles";

    @FindBy(xpath = ".//label[text()='Sort by']/parent::div")
    private ExtendedWebElement sortByFilterLabel;

    @FindBy(xpath = ".//label[text()='Sort by']/parent::div//li/input[@value='%s']")
    private ExtendedWebElement sortOptionLabel;

    @FindBy(xpath = ".//label[text()='Price']/parent::div")
    private ExtendedWebElement priceFilterLabel;

    @FindBy(css = "#priceFrom")
    private ExtendedWebElement priceFromTextField;

    @FindBy(css = "#priceTo")
    private ExtendedWebElement priceToTextField;

    @FindBy(css = "#formGroups+div>button[type='submit']")
    private ExtendedWebElement filterBtn;

    public ProductFilters(WebDriver driver) {
        super(driver);
    }

    @Override
    public NewInSubCatPageBase sortBy(SortOption sortOption) {
        sortByFilterLabel.click();
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
        priceFilterLabel.click();
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
