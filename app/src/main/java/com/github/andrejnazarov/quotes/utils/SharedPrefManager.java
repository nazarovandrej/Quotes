package com.github.andrejnazarov.quotes.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * @author Nazarov on 28.07.17.
 */

public class SharedPrefManager {

    private static final String QUOTES_COUNT = "quotes_count";
    private static final String IS_FAMOUS_CHECKED = "is_famous_checked";

    private SharedPreferences mSharedPreferences;

    public SharedPrefManager(SharedPreferences sharedPreferences) {
        mSharedPreferences = sharedPreferences;
    }

    /**
     * Method to check network accessibility
     *
     * @param context Context
     * @return true if there is connection, false otherwise
     */
    public boolean hasConnection(Context context) {
        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            connected = true;
        }
        return connected;
    }

    public int generateRandomNumber(int min, int max) {
        return min + (int) (Math.random() * (max - min));
    }

    public void writeQuotesCount(String quotesCount) {
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

        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putInt(QUOTES_COUNT, count);
        editor.apply();
    }

    public int readQuotesCount() {
        int defaultValue = 10;
        return mSharedPreferences.getInt(QUOTES_COUNT, defaultValue);
    }

    public void writeIsFamousChecked(boolean isChecked) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putBoolean(IS_FAMOUS_CHECKED, isChecked);
        editor.apply();
    }

    public boolean readIsFamousChecked() {
        boolean defaultValue = true;
        return mSharedPreferences.getBoolean(IS_FAMOUS_CHECKED, defaultValue);
    }
}