package com.github.andrejnazarov.quotes.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.widget.RemoteViews;

import com.github.andrejnazarov.quotes.R;
import com.github.andrejnazarov.quotes.bean.Quote;
import com.github.andrejnazarov.quotes.dagger.QuoteApplication;
import com.github.andrejnazarov.quotes.net.QuoteService;
import com.github.andrejnazarov.quotes.utils.SharedPrefManager;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.github.andrejnazarov.quotes.net.Categories.FAMOUS;

/**
 * @author Nazarov on 20.08.17.
 */

public class QuoteWidgetProvider extends AppWidgetProvider {

    @Inject
    QuoteService mService;

    @Inject
    SharedPrefManager mPrefManager;

    @Inject
    Realm mRealm;

    @Override
    public void onUpdate(final Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        ((QuoteApplication) context.getApplicationContext())
                .getNetComponent()
                .inject(this);

        Intent intent = new Intent(context, QuoteWidgetProvider.class);
        intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);
        final RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.quote_appwidget);
        PendingIntent pendingIntent =
                PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        views.setOnClickPendingIntent(R.id.get_button, pendingIntent);
        context.startService(intent);

        final AppWidgetManager manager = AppWidgetManager.getInstance(context);
        final ComponentName componentName = new ComponentName(context, QuoteWidgetProvider.class);
        manager.updateAppWidget(componentName, views);

        RealmResults<Quote> quotes = mRealm.allObjects(Quote.class);

        if (mPrefManager.hasConnection(context)) {
            Call<Quote> call = mService.getQuote(FAMOUS, 1);
            call.enqueue(new Callback<Quote>() {
                @Override
                public void onResponse(@NonNull Call<Quote> call, @NonNull Response<Quote> response) {
                    Quote quote = response.body();
                    if (quote != null) {
                        views.setTextViewText(R.id.quote_text_view, quote.getQuote());
                        views.setTextViewText(R.id.author_text_view, quote.getAuthor());
                        manager.updateAppWidget(componentName, views);
                    }
                }

                @Override
                public void onFailure(@NonNull Call<Quote> call, @NonNull Throwable t) {
                    views.setTextViewText(R.id.quote_text_view, context.getString(R.string.error));
                }
            });
        } else if (quotes != null && !quotes.isEmpty()) {
            int randomIndex = mPrefManager.generateRandomNumber(0, quotes.size());
            Quote quote = quotes.get(randomIndex);
            views.setTextViewText(R.id.quote_text_view, quote.getQuote());
            views.setTextViewText(R.id.author_text_view, quote.getAuthor());
            manager.updateAppWidget(componentName, views);
        }
        super.onUpdate(context, appWidgetManager, appWidgetIds);
    }
}