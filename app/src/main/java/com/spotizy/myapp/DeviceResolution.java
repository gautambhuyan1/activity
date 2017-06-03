package com.spotizy.myapp;

import android.app.Activity;
import android.util.DisplayMetrics;

/**
 * Created by Barnali on 4/12/2016.
 */
public class DeviceResolution {

    private static int width = -1;
    private static int height = -1;
    private static double screenInches = -1.0;

    /**
     * Calculate screen resolutions.
     */
    private static void getDeviceResolution(Activity activity) {
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        width = dm.widthPixels;
        height = dm.heightPixels;

        //Calculate Screen Inches
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        double x = Math.pow(width / dm.xdpi, 2);
        double y = Math.pow(height / dm.ydpi, 2);

        screenInches = Math.sqrt(x + y);

        if (width <= 480 && screenInches > 5.0) {
            screenInches = 4.5;
        }

    }

    public static int getScreenWidth(Activity activity) {
        try {
            if (width > 0)
                return width;
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        getDeviceResolution(activity);

        return width;
    }

    public static int getScreenHeight(Activity activity) {
        try {
            if (height > 0)
                return height;
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        getDeviceResolution(activity);

        return height;
    }

    public static double getScreenInches(Activity activity) {
        try {
            if (screenInches > 0)
                return screenInches;
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        getDeviceResolution(activity);

        return screenInches;
    }

}
