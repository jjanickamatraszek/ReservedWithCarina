package com.solvd.tests.dataProviders;

import com.solvd.propertiesReader.TestDataReader;
import org.testng.annotations.DataProvider;

public class DataProviders {

    @DataProvider(name = "New In submenu categories")
    public static Object[][] getNewInCategories() {
        int length = TestDataReader.getNewInCategories().length;
        Object[][] categories = new Object[length][1];
        for (int i = 0; i < length; i++) {
            categories[i][0] = TestDataReader.getNewInCategories()[i];
        }
        return categories;
    }
}
