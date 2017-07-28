package com.github.andrejnazarov.quotes.net;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * @author Nazarov on 28.07.17.
 */

public class ApiClient {

    private static final String BASE_URL = "https://andruxnet-random-famous-quotes.p.mashape.com";
    private static Retrofit sRetrofit = null;

    private ApiClient() {
        throw new IllegalStateException("cant' create object");
    }

    public static Retrofit getClient() {
        if (sRetrofit == null) {
            sRetrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(JacksonConverterFactory.create())
                    .build();
        }
        return sRetrofit;
    }
}