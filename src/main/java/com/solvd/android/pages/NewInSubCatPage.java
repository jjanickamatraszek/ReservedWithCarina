package com.solvd.android.pages;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.solvd.android.components.CookieDialog;
import com.solvd.android.components.MainMenu;
import com.solvd.android.components.ProductFilters;
import com.solvd.common.components.CookieDialogBase;
import com.solvd.common.components.MainMenuBase;
import com.solvd.common.components.ProductFiltersBase;
import com.solvd.common.components.SideBarBase;
import com.solvd.common.pages.NewInSubCatPageBase;
import com.solvd.common.pages.ProductPageBase;
import com.solvd.model.Product;
import com.zebrunner.carina.utils.factory.DeviceType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = NewInSubCatPageBase.class)
public class NewInSubCatPage extends NewInSubCatPageBase {
    private static final String THIS_METHOD_IS_DEFINED_ONLY_FOR_DESKTOP = "This method is not applicable for browsers on mobiles";

    @FindBy(css = "h1 span")
    private ExtendedWebElement titleLabel;

    @FindBy(xpath = ".//button[starts-with(@class,'hamburger')]")
    private ExtendedWebElement mainMenuBtn;

    @FindBy(css = "#categoryProducts>article")
    private List<ExtendedWebElement> products;

    @FindBy(css = "#cookiebanner")
    private CookieDialog cookieDialog;

    @FindBy(css = "#categoryFilters")
    private ExtendedWebElement productFiltersBtn;

    @FindBy(xpath = ".//*[@id='categoryProducts']/article[@data-id='%s']")
    private ExtendedWebElement product;

    private final By productsLocator = By.cssSelector("#categoryProducts>article");

    @FindBy(css = "div[data-qa-id='close-button']")
    private ExtendedWebElement closeDownloadMobileAppDialogBtn;

    public NewInSubCatPage(WebDriver driver) {
        super(driver);
        waitUntil(ExpectedConditions.visibilityOfAllElementsLocatedBy(productsLocator), 6);
        closeDownloadMobileAppDialogBtn.clickIfPresent(3);
    }

    @Override
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
        return products
                .stream()
                .map(el -> new BigDecimal(el.findExtendedWebElement(By.cssSelector(".es-final-price")).getText().replaceAll("[^.0-9]", "")))
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getProductsTitles() {
        return products
                .stream()
                .map(el -> el.findExtendedWebElement(By.cssSelector(".es-product-name")).getText())
                .collect(Collectors.toList());
    }

    @Override
    public MainMenuBase getMainMenu() {
        mainMenuBtn.click();
        return new MainMenu(getDriver());
    }

    @Override
    public boolean areProductsLoaded() {
        return waitUntil(ExpectedConditions.visibilityOfAllElementsLocatedBy(productsLocator), 6);
    }

    @Override
    public CookieDialogBase getCookieDialog() {
        return new CookieDialog(driver, cookieDialog.getRootExtendedElement().getElement());
    }

    @Override
    public ProductFiltersBase getProductFilters() {
        productFiltersBtn.click();
        return new ProductFilters(driver);
    }

    @Override
    public SideBarBase getSideBar() {
        throw new UnsupportedOperationException(THIS_METHOD_IS_DEFINED_ONLY_FOR_DESKTOP);
    }
}
