package dev.gustavoteixeira.api.stockquotemanager.service.impl;

import dev.gustavoteixeira.api.stockquotemanager.dto.StockQuote;
import dev.gustavoteixeira.api.stockquotemanager.exception.StockNotRegisteredException;
import dev.gustavoteixeira.api.stockquotemanager.service.CachingService;
import dev.gustavoteixeira.api.stockquotemanager.service.DatabaseService;
import dev.gustavoteixeira.api.stockquotemanager.service.StockQuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;


@Service
public class StockQuoteServiceImpl implements StockQuoteService {

    @Autowired
    private CachingService cachingService;

    @Autowired
    private DatabaseService databaseService;

    @Override
    public Set<StockQuote> getAllStockQuotes() {
        return databaseService.getAllStockQuotes();
    }

    @Override
    public void createNewStockQuote(StockQuote stockQuote) {
        /**
         * A user is allowed to create stockQuote quotes on stockQuote-quote-manager only if
         * the stockQuote is registered at stockQuote-manager
         */
        validateStock(stockQuote.getId());

        /**
         * Save the stock quote in the database
         */
        databaseService.persistStockQuote(stockQuote);
    }

    @Override
    public StockQuote getStockQuoteById(String stockId) {
        return databaseService.getStockQuoteById(stockId);
    }

    private void validateStock(String stockName) {
        if (!cachingService.stockIsRegistered(stockName))
            throw new StockNotRegisteredException();
    }

}
