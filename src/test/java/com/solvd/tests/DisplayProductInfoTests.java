package com.solvd.tests;

import com.qaprosoft.carina.core.foundation.IAbstractTest;
import com.solvd.common.pages.HomePageBase;
import com.solvd.common.pages.ProductPageBase;
import com.solvd.model.Product;
import com.solvd.propertiesReader.TestDataReader;
import com.zebrunner.agent.core.annotation.TestRailCaseId;
import com.zebrunner.carina.core.registrar.ownership.MethodOwner;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class DisplayProductInfoTests implements IAbstractTest {
    private String newInCategory;
    private Product product;

    @BeforeClass
    public void loadTestData() {
        newInCategory = TestDataReader.getNewInCategories()[0];
        product = TestDataReader.getNewInProduct();
    }

    @Test(description = "Display product page")
    @MethodOwner(owner = "jjanickamatraszek")
    @TestRailCaseId("C3578")
    public void displayProductInfoTest() {
        String expectedProductTitle = product.getTitle();

        HomePageBase homePage = initPage(getDriver(), HomePageBase.class);
        homePage.goToPage().getCookieDialog().acceptCookies();
        ProductPageBase productPage = homePage.getMainMenu()
                .expandNewInSubMenu()
                .clickOnSubcategory(newInCategory)
                .clickOnProduct(product);

        SoftAssert soft = new SoftAssert();
        soft.assertEquals(productPage.getTitle(), expectedProductTitle, "Product title is different than expected");
        soft.assertFalse(productPage.getPrice().isBlank(), "Product price is blank");
        soft.assertTrue(productPage.mainImageIsDisplayed(), "Product main image is not displayed");
        soft.assertTrue(productPage.isDescriptionExpanded(), "Product description section is not expanded after page launch");
        soft.assertFalse(productPage.getDescriptionText().isBlank(), "Product description text is blank");
        soft.assertFalse(productPage.isMaterialAndCareExpanded(), "Product material and care section is expanded after page launch");

        productPage.expandCollapseMaterialAndCareSection();

        soft.assertTrue(productPage.isMaterialAndCareExpanded(), "Product material and care section is collapsed after click on label");
        soft.assertFalse(productPage.getMaterialAndCareText().isBlank(), "Product material and care section is blank when expanded");
        soft.assertAll();
    }

    @Test(description = "Expand and collapse description section")
    @MethodOwner(owner = "jjanickamatraszek")
    @TestRailCaseId("C3579")
    public void expandAndCollapseDescTest() {
        HomePageBase homePage = initPage(getDriver(), HomePageBase.class);
        homePage.goToPage().getCookieDialog().acceptCookies();
        ProductPageBase productPage = homePage.getMainMenu()
                .expandNewInSubMenu()
                .clickOnSubcategory(newInCategory)
                .clickOnProduct(product);

        ProductPageBase productPageWithCollapsedAllSections = productPage
                .expandCollapseDescriptionSection();

        SoftAssert soft = new SoftAssert();
        soft.assertFalse(productPageWithCollapsedAllSections.isMaterialAndCareExpanded(), "Material section is " +
                "expanded when originally should be collapsed");
        soft.assertFalse(productPageWithCollapsedAllSections.isDescriptionExpanded(), "Desc section is expanded " +
                "when after click on desc label should have collapsed");

        ProductPageBase productPageWithExpandedMaterialAndCare = productPageWithCollapsedAllSections
                .expandCollapseMaterialAndCareSection();

        soft.assertFalse(productPageWithExpandedMaterialAndCare.isDescriptionExpanded(), "Desc section is expanded" +
                " when after click on material label should have collapsed");
        soft.assertTrue(productPageWithExpandedMaterialAndCare.isMaterialAndCareExpanded(), "Material section is " +
                "collapsed when after click on material label should have been expanded");

        ProductPageBase productPageWithExpandedDesc = productPageWithExpandedMaterialAndCare
                .expandCollapseDescriptionSection();

        soft.assertFalse(productPageWithExpandedDesc.isMaterialAndCareExpanded(), "Material section stayed expanded" +
                " when after click on desc label should have collapsed");
        soft.assertTrue(productPageWithExpandedDesc.isDescriptionExpanded(), "Desc section is collapsed when after" +
                " click on desc label should have become expanded");
        soft.assertAll();
    }
}
