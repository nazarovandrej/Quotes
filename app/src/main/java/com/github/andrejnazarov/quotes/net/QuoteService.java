package com.github.andrejnazarov.quotes.net;

import com.github.andrejnazarov.quotes.bean.Quote;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * @author Nazarov on 28.07.17.
 */

public interface QuoteService {

    @Headers("X-Mashape-Key: ttUbmOF3RImshnnF61GVRWimePq6p1AFKAbjsnvHlCahKC3hQd")
    @GET("/")
    Call<List<Quote>> getQuotesList(@Query("cat") @Categories.Category String cat,
                                    @Query("count") int count);

    @Headers("X-Mashape-Key: ttUbmOF3RImshnnF61GVRWimePq6p1AFKAbjsnvHlCahKC3hQd")
    @GET("/")
    Call<Quote> getQuote(@Query("cat") @Categories.Category String cat,
                         @Query("count") int count);

}