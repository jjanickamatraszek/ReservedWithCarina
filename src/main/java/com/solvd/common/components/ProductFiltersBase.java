package com.solvd.common.components;

import com.qaprosoft.carina.core.gui.AbstractUIObject;
import com.solvd.common.components.enums.SortOption;
import com.solvd.common.pages.NewInSubCatPageBase;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;

import java.math.BigDecimal;

public abstract class ProductFiltersBase extends AbstractUIObject {

    public ProductFiltersBase(WebDriver driver) {
        super(driver);
    }

    public ProductFiltersBase(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public abstract NewInSubCatPageBase sortBy(SortOption sortOption);

    public abstract int getNumberOfActiveSortFilters();

    public abstract NewInSubCatPageBase filterByPriceRange(BigDecimal priceFrom, BigDecimal priceTo);

    public abstract int getNumberOfActivePriceFilters();
}
