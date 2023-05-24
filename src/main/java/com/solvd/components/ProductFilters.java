package com.solvd.components;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractUIObject;
import com.solvd.components.enums.SortOption;
import com.solvd.pages.NewInSubCatPage;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.math.BigDecimal;

public class ProductFilters extends AbstractUIObject {

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

    public NewInSubCatPage sortBy(SortOption sortOption) {
        sortByFilterLabel.click();
        sortOptionLabel.format(sortOption.getValue()).click();
        sortBtn.click();
        return new NewInSubCatPage(driver);
    }

    public int getNumberOfActiveSortFilters() {
        return getNumberOfActiveFilters(sortByFilterLabel);
    }

    public NewInSubCatPage filterByPriceRange(BigDecimal priceFrom, BigDecimal priceTo) {
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
