package com.github.andrejnazarov.quotes;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.github.andrejnazarov.quotes.adapter.QuoteAdapter;
import com.github.andrejnazarov.quotes.adapter.QuoteClickListener;
import com.github.andrejnazarov.quotes.bean.Quote;

import java.util.ArrayList;

/**
 * @author Nazarov on 28.07.17.
 */

public class QuotesRealmFragment extends BasicFragment {

    private OnQuoteRealmClickListener mListener;

    public static QuotesRealmFragment newInstance() {
        return new QuotesRealmFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnQuoteRealmClickListener) {
            mListener = (OnQuoteRealmClickListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnQuoteRealmClickListener");
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView.setAdapter(new QuoteAdapter(new ArrayList<Quote>(), new QuoteClickListener() {
            @Override
            public void onQuoteClick(int position) {

            }
        }));
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    protected void onSwipeRefresh() {
        // TODO: 28.07.17 setadapter from realm
        onStopRefreshing();
    }

    private void onStopRefreshing() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    public interface OnQuoteRealmClickListener {
        void onQuoteRealmClick(String quote);
    }
}
