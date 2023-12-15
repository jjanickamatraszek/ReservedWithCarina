package com.solvd.common.pages;

import com.qaprosoft.carina.core.gui.AbstractPage;
import com.solvd.common.components.CookieDialogBase;
import com.solvd.common.components.MainMenuBase;
import com.solvd.common.components.ProductFiltersBase;
import com.solvd.common.components.SideBarBase;
import com.solvd.model.Product;
import org.openqa.selenium.WebDriver;

import java.math.BigDecimal;
import java.util.List;

public abstract class NewInSubCatPageBase extends AbstractPage {

    public NewInSubCatPageBase(WebDriver driver) {
        super(driver);
    }

    public abstract NewInSubCatPageBase goToPage(String route);

    public abstract String getTitle();

    public abstract ProductPageBase clickOnProduct(Product testDataProduct);

    public abstract List<BigDecimal> getProductsPricesAsNumbers();

    public abstract List<String> getProductsTitles();

    public abstract MainMenuBase getMainMenu();

    public abstract boolean areProductsLoaded();

    public abstract CookieDialogBase getCookieDialog();

    public abstract ProductFiltersBase getProductFilters();

    public abstract SideBarBase getSideBar();
}
