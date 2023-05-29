package com.solvd.iOS.components;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.solvd.iOS.pages.NewInSubCatPage;
import com.solvd.common.components.NewInSubMenuBase;
import com.solvd.common.pages.NewInSubCatPageBase;
import com.zebrunner.carina.utils.factory.DeviceType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

@DeviceType(pageType = DeviceType.Type.IOS_PHONE, parentClass = NewInSubMenuBase.class)
public class NewInSubMenu extends NewInSubMenuBase {
    private static final String THIS_METHOD_IS_DEFINED_ONLY_FOR_DESKTOP = "This method is not applicable for browsers on mobiles";

    @FindBy(xpath = ".//div[@id='nm23716']/parent::div")
    private ExtendedWebElement submenuContainer;

    @FindBy(xpath = ".//*[@id='nm23716']/following-sibling::div[contains(@class, 'nm-menu-row')]")
    private List<ExtendedWebElement> subCategories;

    @FindBy(xpath = ".//div[@id='nm-tree-view']//span[text() = '%s']")
    private ExtendedWebElement subcategory;

    public NewInSubMenu(WebDriver driver) {
        super(driver);
    }

    public boolean isSubMenuDisplayed() {
        return submenuContainer.getAttribute("class").contains("active");
    }

    public int getNumberOfSubcategories() {
        return subCategories.size();
    }

    public long getNumberOfSubcategoriesWithoutImg() {
        throw new UnsupportedOperationException(THIS_METHOD_IS_DEFINED_ONLY_FOR_DESKTOP);
    }

    public long getNumberOfSubcategoriesWithoutTitle() {
        return subCategories
                .stream()
                .map(ExtendedWebElement::getText)
                .filter(String::isBlank)
                .count();
    }

    public NewInSubCatPageBase clickOnSubcategory(String title) {
        subcategory.format(title.toUpperCase()).click();
        return new NewInSubCatPage(driver);
    }
}
