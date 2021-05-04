package dev.gustavoteixeira.api.stockquotemanager.service.impl;

import dev.gustavoteixeira.api.stockquotemanager.dto.StockQuote;
import dev.gustavoteixeira.api.stockquotemanager.exception.StockNotFoundException;
import dev.gustavoteixeira.api.stockquotemanager.service.DatabaseService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class DatabaseServiceImpl implements DatabaseService {


    static Set<StockQuote> stocksQuotesList = new HashSet<>();

    @Override
    public void persistStockQuote(StockQuote newStockQuote) {
        logger.info("DatabaseServiceImpl.persistStockQuote - Start - Stock quote name: {}.", newStockQuote.getId());
        /**
         * Search in the database by stock name
         */
        Optional<StockQuote> stockQuoteFromDatabaseOptional = findStockQuoteById(newStockQuote.getId());
        /**
         * If the stock is already present
         */
        if (stockQuoteFromDatabaseOptional.isPresent()) {
            /**
             * Gets the stock quote from database
             */
            StockQuote stockQuoteFromDatabase = stockQuoteFromDatabaseOptional.get();
            /**
             * updates it with the new quotes
             */
            StockQuote updatedStockQuote = updateStockQuote(newStockQuote, stockQuoteFromDatabase);
            /**
             * then saves it into the database
             */
            stocksQuotesList.add(updatedStockQuote);
        } else
        /**
         * If the stock does not exist in the database, then just save it since it is a new record
         */
            stocksQuotesList.add(newStockQuote);
    }

    @Override
    public Set<StockQuote> getAllStockQuotes() {
        logger.info("DatabaseServiceImpl.getAllStockQuotes - Start.");
        return stocksQuotesList;
    }

    @Override
    public StockQuote getStockQuoteById(String stockId) {
        logger.info("DatabaseServiceImpl.getStockQuoteById - Start - Stock quote name: {}.", stockId);
        Optional<StockQuote> stockQuoteFromDatabaseOptional = findStockQuoteById(stockId);
        return stockQuoteFromDatabaseOptional.orElseThrow(StockNotFoundException::new);
    }

    private Optional<StockQuote> findStockQuoteById(String stockId) {
        return stocksQuotesList.stream()
                .filter(stockQuote -> stockQuote.getId().equals(stockId))
                .findFirst();
    }

    private StockQuote updateStockQuote(StockQuote stockQuote, StockQuote stockQuoteFromDatabase) {
        HashMap<LocalDate, String> quotesFromDatabaseQuotes = stockQuoteFromDatabase.getQuotes();
        quotesFromDatabaseQuotes.putAll(stockQuote.getQuotes());
        stockQuoteFromDatabase.setQuotes(quotesFromDatabaseQuotes);
        return stockQuoteFromDatabase;
    }

}
