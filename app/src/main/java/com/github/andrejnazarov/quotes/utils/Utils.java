package com.github.andrejnazarov.quotes.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * @author Nazarov on 28.07.17.
 */

public class Utils {

    private static final String QUOTES_COUNT = "quotes_count";
    private static final String IS_FAMOUS_CHECKED = "is_famous_checked";

    private Utils() {
        throw new IllegalStateException("can't create object");
    }

    /**
     * Method to check network accessibility
     *
     * @param context Context
     * @return true if there is connection, false otherwise
     */
    public static boolean hasConnection(Context context) {
        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            connected = true;
        }
        return connected;
    }

    public static int generateRandomNumber(int min, int max) {
        return min + (int)(Math.random() * (max - min));
    }

    public static void writeQuotesCount(Activity activity, String quotesCount) {
        int count;
        if (quotesCount == null || quotesCount.equals("0")) {
            count = 1;
        } else {
            count = Integer.parseInt(quotesCount);
        }

        if (count < 0) {
            count = 1;
        } else if (count > 10) {
            count = 10;
        }

        SharedPreferences preferences = activity.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(QUOTES_COUNT, count);
        editor.apply();
    }

    public static int readQuotesCount(Activity activity) {
        int defaultValue = 10;
        SharedPreferences preferences = activity.getPreferences(Context.MODE_PRIVATE);
        return preferences.getInt(QUOTES_COUNT, defaultValue);
    }

    public static void writeIsFamousChecked(Activity activity, boolean isChecked) {
        SharedPreferences preferences = activity.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(IS_FAMOUS_CHECKED, isChecked);
        editor.apply();
    }

    public static boolean readIsFamousChecked(Activity activity) {
        boolean defaultValue = true;
        SharedPreferences preferences = activity.getPreferences(Context.MODE_PRIVATE);
        return preferences.getBoolean(IS_FAMOUS_CHECKED, defaultValue);
    }

}
