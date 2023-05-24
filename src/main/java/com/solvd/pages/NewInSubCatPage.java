package com.solvd.pages;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;
import com.solvd.components.CookieDialog;
import com.solvd.components.MainMenu;
import com.solvd.components.ProductFilters;
import com.solvd.components.SideBar;
import com.solvd.model.Product;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class NewInSubCatPage extends AbstractPage {

    @FindBy(css = "h1[class*='category-title']")
    private ExtendedWebElement titleLabel;

    @FindBy(css = "div[class*='sidebar__StyledSidebar']")
    @Getter
    private SideBar sideBar;

    @FindBy(css = "ul[data-testid='category-list']")
    @Getter
    private MainMenu mainMenu;

    @FindBy(css = "#categoryProducts>article")
    private List<ExtendedWebElement> products;

    @FindBy(css = "#cookiebanner")
    @Getter
    private CookieDialog cookieDialog;

    @FindBy(css = "#categoryFilters")
    @Getter
    private ProductFilters productFilters;

    @FindBy(xpath = ".//*[@id='categoryProducts']/article[@data-id='%s']")
    private ExtendedWebElement product;

    public NewInSubCatPage(WebDriver driver) {
        super(driver);
    }

    public NewInSubCatPage goToPage(String route) {
        openURL(route);
        return this;
    }

    public String getTitle() {
        return titleLabel.getText();
    }

    public ProductPage clickOnProduct(Product testDataProduct) {
        product.format(testDataProduct.getDataId()).click();
        return new ProductPage(driver);
    }

    public List<BigDecimal> getProductsPricesAsNumbers() {
        waitUntil(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".jDLjGg")), 2);
        return products
                .stream()
                .map(el -> new BigDecimal(el.findExtendedWebElement(By.cssSelector(".es-final-price")).getText().replaceAll("[^.0-9]", "")))
                .collect(Collectors.toList());
    }

    public List<String> getProductsTitles() {
        waitUntil(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".jDLjGg")), 2);
        return products
                .stream()
                .map(el -> el.findExtendedWebElement(By.cssSelector(".es-product-name")).getText())
                .collect(Collectors.toList());
    }

    public boolean areProductsLoaded() {
        return waitUntil(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".jDLjGg")), 2);
    }
}
