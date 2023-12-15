package com.solvd.desktop.pages;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;
import com.solvd.common.components.CookieDialogBase;
import com.solvd.common.components.MainMenuBase;
import com.solvd.common.pages.HomePageBase;
import com.solvd.common.pages.NewInSubCatPageBase;
import com.solvd.common.pages.ProductPageBase;
import com.solvd.desktop.components.CookieDialog;
import com.solvd.desktop.components.MainMenu;
import com.solvd.desktop.components.ProductFilters;
import com.solvd.desktop.components.SideBar;
import com.solvd.model.Product;
import com.zebrunner.carina.utils.factory.DeviceType;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@DeviceType(pageType = DeviceType.Type.DESKTOP, parentClass = NewInSubCatPageBase.class)
public class NewInSubCatPage extends NewInSubCatPageBase {

    @FindBy(css = "h1[class*='category-title']")
    private ExtendedWebElement titleLabel;

    @FindBy(css = "div[class*='sidebar__StyledSidebar']")
    @Getter
    private SideBar sideBar;

    @FindBy(css = "ul[data-testid='category-list']")
    private MainMenu mainMenu;

    @FindBy(css = "#categoryProducts>article")
    private List<ExtendedWebElement> products;

    @FindBy(css = "#cookiebanner")
    private CookieDialog cookieDialog;

    @FindBy(css = "#categoryFilters")
    @Getter
    private ProductFilters productFilters;

    @FindBy(css = "#categoryProducts")
    private ExtendedWebElement productsContainer;

    @FindBy(xpath = ".//*[@id='categoryProducts']/article[@data-id='%s']")
    private ExtendedWebElement product;

    public NewInSubCatPage(WebDriver driver) {
        super(driver);
        setUiLoadedMarker(productsContainer);
    }

    public NewInSubCatPageBase goToPage(String route) {
        openURL(route);
        return this;
    }

    @Override
    public String getTitle() {
        return titleLabel.getText();
    }

    @Override
    public ProductPageBase clickOnProduct(Product testDataProduct) {
        product.format(testDataProduct.getDataId()).click();
        return initPage(driver, ProductPageBase.class);
    }

    @Override
    public List<BigDecimal> getProductsPricesAsNumbers() {
        waitUntil(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".jDLjGg")), 2);
        return products
                .stream()
                .map(el -> new BigDecimal(el.findExtendedWebElement(By.cssSelector(".es-final-price")).getText().replaceAll("[^.0-9]", "")))
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getProductsTitles() {
        waitUntil(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".jDLjGg")), 2);
        return products
                .stream()
                .map(el -> el.findExtendedWebElement(By.cssSelector(".es-product-name")).getText())
                .collect(Collectors.toList());
    }

    @Override
    public boolean areProductsLoaded() {
        return waitUntil(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".jDLjGg")), 2);
    }

    @Override
    public CookieDialogBase getCookieDialog() {
        return new CookieDialog(getDriver(), cookieDialog.getRootExtendedElement().getElement());
    }

    @Override
    public MainMenuBase getMainMenu() {
        return new MainMenu(getDriver(), mainMenu.getRootExtendedElement().getElement());
    }
}
