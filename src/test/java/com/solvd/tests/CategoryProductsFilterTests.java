package com.solvd.tests;

import com.qaprosoft.carina.core.foundation.IAbstractTest;
import com.solvd.common.components.enums.SortOption;
import com.solvd.common.pages.NewInSubCatPageBase;
import com.solvd.route.Route;
import com.solvd.utils.TestUtils;
import com.zebrunner.agent.core.annotation.TestRailCaseId;
import com.zebrunner.carina.core.registrar.ownership.MethodOwner;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

public class CategoryProductsFilterTests implements IAbstractTest {

    @Test(description = "Sort products by price asc")
    @MethodOwner(owner = "jjanickamatraszek")
    @TestRailCaseId("C3580")
    public void sortProductsByPriceAscTest() {
        NewInSubCatPageBase categoryPage = initPage(getDriver(), NewInSubCatPageBase.class);
        categoryPage.goToPage(Route.NEW_IN_WOMEN).getCookieDialog().acceptCookies();
        List<BigDecimal> expectedProductsPricesSorted = categoryPage
                .getProductsPricesAsNumbers()
                .stream()
                .sorted(BigDecimal::compareTo)
                .collect(Collectors.toList());

        NewInSubCatPageBase categoryPageSortedByPrice = categoryPage.getProductFilters()
                .sortBy(SortOption.PRICE_ASC);

        List<BigDecimal> actualProductsPricesSorted = categoryPageSortedByPrice
                .getProductsPricesAsNumbers();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualProductsPricesSorted, expectedProductsPricesSorted,
                "Products aren't sorted by price asc");

        if (!TestUtils.isMobile()) {
            softAssert.assertEquals(categoryPageSortedByPrice.getProductFilters().getNumberOfActiveSortFilters(), 1,
                    "Number of active sort filters is different from expected");
        }

        softAssert.assertAll();
    }

    @Test(description = "Sort products by default order")
    @MethodOwner(owner = "jjanickamatraszek")
    @TestRailCaseId("C3581")
    public void sortProductsByDefaultOrderTest() {
        NewInSubCatPageBase categoryPage = initPage(getDriver(), NewInSubCatPageBase.class);
        categoryPage.goToPage(Route.NEW_IN_WOMEN).getCookieDialog().acceptCookies();
        List<String> expectedProductsTitles = categoryPage.getProductsTitles();

        NewInSubCatPageBase categoryPageSortedByPrice = categoryPage
                .getProductFilters()
                .sortBy(SortOption.PRICE_ASC);

        Assert.assertTrue(categoryPageSortedByPrice.areProductsLoaded(),
                "Products didn't load after sorting by price asc");

        NewInSubCatPageBase categoryPageSortedByDefaultOrder = categoryPageSortedByPrice
                .getProductFilters()
                .sortBy(SortOption.DEFAULT);

        Assert.assertTrue(categoryPageSortedByPrice.areProductsLoaded(),
                "Products didn't load after sorting by default order");

        List<String> actualProductsTitles = categoryPageSortedByDefaultOrder
                .getProductsTitles();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualProductsTitles, expectedProductsTitles,
                "Order of products is different than after page launch");

        if (!TestUtils.isMobile()) {
            int actualNumberOfActiveSortFilters = categoryPageSortedByDefaultOrder
                    .getProductFilters()
                    .getNumberOfActiveSortFilters();
            softAssert.assertEquals(actualNumberOfActiveSortFilters, 0,
                    "Number of active sort filters is displayed when should be removed");
        }

        softAssert.assertAll();
    }

    @Test(description = "Filter products by price range")
    @MethodOwner(owner = "jjanickamatraszek")
    @TestRailCaseId("C3582")
    public void filterProductsByPriceRangeTest() {
        NewInSubCatPageBase categoryPage = initPage(getDriver(), NewInSubCatPageBase.class);
        categoryPage.goToPage(Route.NEW_IN_WOMEN).getCookieDialog().acceptCookies();
        List<BigDecimal> productPricesSortedAsc = categoryPage
                .getProductsPricesAsNumbers()
                .stream()
                .sorted(BigDecimal::compareTo)
                .collect(Collectors.toList());

        int amountOfProductBeforeFiltering = productPricesSortedAsc.size();
        BigDecimal priceFrom = productPricesSortedAsc.get(1).setScale(0, RoundingMode.CEILING);
        BigDecimal priceTo = productPricesSortedAsc.get(productPricesSortedAsc.size() - 2).setScale(0, RoundingMode.FLOOR);

        NewInSubCatPageBase categoryPageFiltered = categoryPage
                .getProductFilters()
                .filterByPriceRange(priceFrom, priceTo);

        List<BigDecimal> productsPricesFiltered = categoryPageFiltered.getProductsPricesAsNumbers();

        int amountOfProductsAfterFilter = productsPricesFiltered.size();
        long numberOfPricesWithValueLessPriceFrom = productsPricesFiltered
                .stream().filter(p -> p.compareTo(priceFrom) < 0)
                .count();
        long numberOfPricesWithValueOverPriceTo = productsPricesFiltered
                .stream().filter(p -> p.compareTo(priceTo) > 0)
                .count();

        SoftAssert soft = new SoftAssert();
        soft.assertTrue(amountOfProductBeforeFiltering > amountOfProductsAfterFilter,
                "Amount of products after applying price range filter didn't change");
        soft.assertEquals(numberOfPricesWithValueLessPriceFrom, 0,
                "Products with price lower than lower price limit were displayed");
        soft.assertEquals(numberOfPricesWithValueOverPriceTo, 0,
                "Products with price greater than upper price limit were displayed");

        if (!TestUtils.isMobile()) {
            int actualNumberOfActivePriceFilters = categoryPageFiltered
                    .getProductFilters()
                    .getNumberOfActivePriceFilters();
            soft.assertEquals(actualNumberOfActivePriceFilters, 2,
                    "Number of active price filters is different from expected");
        }

        soft.assertAll();
    }
}
