package com.solvd.components;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractUIObject;
import com.solvd.pages.NewInSubCatPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class NewInSubMenu extends AbstractUIObject {

    @FindBy(css = "ul[class*='inner-list']")
    private ExtendedWebElement submenuContainer;

    @FindBy(css = "ul>div[class*='photo-section']")
    private List<ExtendedWebElement> subCategories;

    @FindBy(xpath = ".//span[contains(text(),'new in')]/parent::li//div[text()='%s']")
    private ExtendedWebElement subcategory;

    public NewInSubMenu(WebDriver driver) {
        super(driver);
        setUiLoadedMarker(submenuContainer);
    }

    public boolean isSubMenuDisplayed() {
        return submenuContainer.isVisible();
    }

    public int getNumberOfSubcategories() {
        return subCategories.size();
    }

    public long getNumberOfSubcategoriesWithoutImg() {
        return subCategories
                .stream()
                .map(el -> el.findExtendedWebElement(By.tagName("img")).getAttribute("src"))
                .filter(String::isBlank)
                .count();
    }

    public long getNumberOfSubcategoriesWithoutTitle() {
        return subCategories
                .stream()
                .map(el -> el.findExtendedWebElement(By.cssSelector("div[class*='Text']")).getText())
                .filter(String::isBlank)
                .count();
    }

    public NewInSubCatPage clickOnSubcategory(String title) {
        subcategory.format(title.toUpperCase()).click();
        return new NewInSubCatPage(driver);
    }
}
