package com.solvd.utils;

import com.zebrunner.carina.utils.mobile.IMobileUtils;

public class MobileContextUtils implements IMobileUtils {

    public void switchMobileContext(View context) {
        switchContext(context.getView());
    }

    public enum View {
        NATIVE("NATIVE_APP"), WEB_VIEW("CHROMIUM");

        String viewName;

        View(String viewName) {
            this.viewName = viewName;
        }

        public String getView() {
            return this.viewName;
        }
    }
}
