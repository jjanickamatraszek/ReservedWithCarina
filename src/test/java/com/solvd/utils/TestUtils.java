package com.solvd.utils;

import com.zebrunner.carina.utils.R;

public class TestUtils {

    public static boolean isMobile() {
        String platformName = R.CONFIG.get("capabilities.platformName");
        return (platformName.equalsIgnoreCase("Android") || platformName.equalsIgnoreCase("iOS"));
    }
}
