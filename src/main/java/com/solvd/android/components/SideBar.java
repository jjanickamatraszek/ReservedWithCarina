package com.solvd.android.components;

import com.solvd.common.components.ProductFiltersBase;
import com.solvd.common.components.SideBarBase;
import com.zebrunner.carina.utils.factory.DeviceType;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = ProductFiltersBase.class)
public class SideBar extends SideBarBase {
    private static final String THIS_METHOD_IS_DEFINED_ONLY_FOR_DESKTOP = "This method is not applicable for browsers on mobiles";

    public SideBar(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    @Override
    public String getActiveCategoryTitle() {
        throw new UnsupportedOperationException(THIS_METHOD_IS_DEFINED_ONLY_FOR_DESKTOP);
    }
}
