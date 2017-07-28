package com.github.andrejnazarov.quotes.bean;

import android.support.annotation.NonNull;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSetter;

import io.realm.RealmObject;

/**
 * @author Nazarov on 28.07.17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Quote extends RealmObject {

    @NonNull
    private String quote;

    @NonNull
    private String author;

    public Quote() {
        //Empty constructor needed
    }

    @NonNull
    @JsonGetter("quote")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String getQuote() {
        return quote;
    }

    @JsonSetter("quote")
    public void setQuote(@NonNull String quote) {
        this.quote = quote;
    }

    @NonNull
    @JsonGetter("author")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String getAuthor() {
        return author;
    }

    @JsonSetter("author")
    public void setAuthor(@NonNull String author) {
        this.author = author;
    }
}