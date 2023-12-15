package com.solvd.tests;

import com.qaprosoft.carina.core.foundation.IAbstractTest;
import com.solvd.common.components.NewInSubMenuBase;
import com.solvd.common.pages.HomePageBase;
import com.solvd.common.pages.NewInSubCatPageBase;
import com.solvd.tests.dataProviders.DataProviders;
import com.solvd.utils.TestUtils;
import com.zebrunner.carina.core.registrar.ownership.MethodOwner;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class NewInSubMenuTests implements IAbstractTest {

    @Test(description = "Display submenu of 'New In' menu category")
    @MethodOwner(owner = "jjanickamatraszek")
    public void displaySubMenuTest() {
        int expectedNumberOfSubcategories = 4;

        HomePageBase homePage = initPage(getDriver(), HomePageBase.class);

        homePage.goToPage().getCookieDialog().acceptCookies();
        NewInSubMenuBase newInSubMenu = homePage.getMainMenu().expandNewInSubMenu();

        Assert.assertTrue(newInSubMenu.isSubMenuDisplayed(), "'New In' submenu is not visible after hover on menu category");

        SoftAssert soft = new SoftAssert();
        soft.assertEquals(newInSubMenu.getNumberOfSubcategories(), expectedNumberOfSubcategories, "Number of subcategories" +
                "is different than expected ");

        if (!TestUtils.isMobile()) {
            soft.assertEquals(newInSubMenu.getNumberOfSubcategoriesWithoutImg(), 0, "Some subcategories don't have an image");
        }

        soft.assertEquals(newInSubMenu.getNumberOfSubcategoriesWithoutTitle(), 0, "Some subcategories don't have a title");
        soft.assertAll();
    }

    @Test(description = "Go to subcategory page",
            dataProvider = "New In submenu categories", dataProviderClass = DataProviders.class)
    @MethodOwner(owner = "jjanickamatraszek")
    public void goToSubcategoryPageTest(String subcategory) {
        HomePageBase homePage = initPage(getDriver(), HomePageBase.class);
        homePage.goToPage().getCookieDialog().acceptCookies();

        NewInSubCatPageBase newInSubCatPage = homePage
                .getMainMenu()
                .expandNewInSubMenu()
                .clickOnSubcategory(subcategory);

        Assert.assertTrue(newInSubCatPage.areProductsLoaded(), "Products didn't load");

        SoftAssert soft = new SoftAssert();
        soft.assertEquals(newInSubCatPage.getTitle(), subcategory, "Title on 'New In' subcategory page is different " +
                "than subcategory chosen from menu");

        if (!TestUtils.isMobile()) {
            soft.assertEquals(newInSubCatPage.getSideBar().getActiveCategoryTitle(), subcategory, "Active category in " +
                    "sidebar is different from subcategory chosen from menu");
        }

        soft.assertAll();
    }
}
