package com.github.andrejnazarov.quotes.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.github.andrejnazarov.quotes.R;

/**
 * @author Nazarov on 28.07.17.
 */

public class QuoteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private QuoteClickListener mListener;

    private TextView mQuoteTextView;
    private TextView mAuthorTextView;

    public QuoteViewHolder(View itemView, QuoteClickListener listener) {
        super(itemView);
        mListener = listener;
        itemView.setOnClickListener(this);
        mQuoteTextView = (TextView) itemView.findViewById(R.id.quote_text_view);
        mAuthorTextView = (TextView) itemView.findViewById(R.id.author_text_view);
    }

    @Override
    public void onClick(View v) {
        mListener.onQuoteClick(getLayoutPosition());
    }

    public void setQuoteTextViewValue(String text) {
        mQuoteTextView.setText(text);
    }

    public void setAuthorTextViewValue(String text) {
        mAuthorTextView.setText(text);
    }
}