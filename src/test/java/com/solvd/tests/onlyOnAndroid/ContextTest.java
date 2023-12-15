package com.solvd.tests.onlyOnAndroid;

import com.qaprosoft.carina.core.foundation.IAbstractTest;
import com.solvd.android.nativeChromePages.ChromeMainPage;
import com.solvd.android.nativeChromePages.GoogleHomePage;
import com.solvd.common.pages.HomePageBase;
import com.solvd.utils.MobileContextUtils;
import com.zebrunner.carina.utils.Configuration;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ContextTest implements IAbstractTest {

    @Test
    public void switchContextTest() {
        HomePageBase reservedHomePage = initPage(getDriver(), HomePageBase.class);
        reservedHomePage.goToPage();

        MobileContextUtils contextHelper = new MobileContextUtils();
        contextHelper.switchMobileContext(MobileContextUtils.View.NATIVE);

        GoogleHomePage googleHomePage = (new ChromeMainPage(getDriver())).goToHomePage();

        Assert.assertTrue(googleHomePage.isGoogleLogoDisplayed(), "Google logo is not displayed on chrome home " +
                "page after context switch");

        googleHomePage.searchFirstEntryForPhrase(Configuration.get(Configuration.Parameter.URL));

        contextHelper.switchMobileContext(MobileContextUtils.View.CHROME);

        initPage(getDriver(), HomePageBase.class)
                .getCookieDialog()
                .acceptCookies();
        reservedHomePage = initPage(getDriver(), HomePageBase.class);

        Assert.assertTrue(reservedHomePage.isLogoVisible(), "Reserved logo is not visible after return to web view");
    }
}
