package com.github.andrejnazarov.quotes.net;

import android.support.annotation.StringDef;

/**
 * @author Nazarov on 28.07.17.
 */

public class Categories {

    @StringDef({FAMOUS,
            MOVIES})

    public @interface Category{
    }

    public static final String FAMOUS = "famous";
    public static final String MOVIES = "movies";
}