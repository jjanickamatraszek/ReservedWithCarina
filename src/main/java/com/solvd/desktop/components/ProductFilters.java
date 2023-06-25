package com.solvd.desktop.components;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.solvd.common.components.ProductFiltersBase;
import com.solvd.common.components.enums.SortOption;
import com.solvd.common.pages.NewInSubCatPageBase;
import com.solvd.desktop.pages.NewInSubCatPage;
import com.zebrunner.carina.utils.factory.DeviceType;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.math.BigDecimal;

@DeviceType(pageType = DeviceType.Type.DESKTOP, parentClass = ProductFiltersBase.class)
public class ProductFilters extends ProductFiltersBase {

    @FindBy(xpath = ".//label[text()='Sort by']/parent::div")
    private ExtendedWebElement sortByFilterLabel;

    @FindBy(xpath = ".//label[text()='Sort by']/parent::div//li/input[@value='%s']")
    private ExtendedWebElement sortOptionLabel;

    @FindBy(xpath = ".//label[text()='Sort by']/parent::div//button[@type='submit']")
    private ExtendedWebElement sortBtn;

    @FindBy(xpath = ".//label[text()='Price']/parent::div")
    private ExtendedWebElement priceFilterLabel;

    @FindBy(css = "#priceFrom")
    private ExtendedWebElement priceFromTextField;

    @FindBy(css = "#priceTo")
    private ExtendedWebElement priceToTextField;

    @FindBy(xpath = ".//label[text()='Price']/parent::div//button[@type='submit']")
    private ExtendedWebElement priceFilterBtn;

    public ProductFilters(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public NewInSubCatPageBase sortBy(SortOption sortOption) {
        sortByFilterLabel.click();
        sortOptionLabel.format(sortOption.getValue()).click();
        sortBtn.click();
        return new NewInSubCatPage(driver);
    }

    public int getNumberOfActiveSortFilters() {
        return getNumberOfActiveFilters(sortByFilterLabel);
    }

    public NewInSubCatPageBase filterByPriceRange(BigDecimal priceFrom, BigDecimal priceTo) {
        priceFilterLabel.click();
        priceFromTextField.type(String.valueOf(priceFrom));
        priceToTextField.type(String.valueOf(priceTo));
        priceFilterBtn.click();
        return new NewInSubCatPage(driver);
    }

    public int getNumberOfActivePriceFilters() {
        return getNumberOfActiveFilters(priceFilterLabel);
    }

    private int getNumberOfActiveFilters(ExtendedWebElement filterLabel) {
        String activeFiltersNumber = filterLabel.getText().replaceAll("\\D", "");
        return activeFiltersNumber.isBlank() ? 0 : Integer.parseInt(activeFiltersNumber);
    }
}
