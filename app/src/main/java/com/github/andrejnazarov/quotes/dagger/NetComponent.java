package com.github.andrejnazarov.quotes.dagger;

import com.github.andrejnazarov.quotes.MainActivity;
import com.github.andrejnazarov.quotes.QuotesRealmFragment;
import com.github.andrejnazarov.quotes.QuotesServerFragment;
import com.github.andrejnazarov.quotes.widget.QuoteWidgetProvider;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author Nazarov on 26.08.17.
 */

@Singleton
@Component(modules = {AppModule.class, NetModule.class})
public interface NetComponent {

    void inject(MainActivity activity);

    void inject(QuotesRealmFragment fragment);

    void inject(QuotesServerFragment fragment);

    void inject(QuoteWidgetProvider provider);
}