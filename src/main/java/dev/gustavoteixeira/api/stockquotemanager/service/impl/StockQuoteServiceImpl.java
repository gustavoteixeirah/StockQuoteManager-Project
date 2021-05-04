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
        logger.info("StockQuoteServiceImpl.getAllStockQuotes - Start.");
        return databaseService.getAllStockQuotes();
    }

    @Override
    public void createNewStockQuote(StockQuote stockQuote) {
        logger.info("StockQuoteServiceImpl.createNewStockQuote - Start - Stock quote name: {}.", stockQuote.getId());
        /**
         * A user is allowed to create stockQuote quotes on stockQuote-quote-manager only if
         * the stockQuote is registered at stockQuote-manager
         */
        validateStock(stockQuote.getId());

        /**
         * Save the stock quote in the database
         */
        databaseService.persistStockQuote(stockQuote);
        logger.info("StockQuoteServiceImpl.createNewStockQuote - End");
    }

    @Override
    public StockQuote getStockQuoteById(String stockId) {
        logger.info("StockQuoteServiceImpl.getStockQuoteById - Start - Stock quote name: {}.", stockId);
        return databaseService.getStockQuoteById(stockId);
    }

    private void validateStock(String stockName) {
        if (!cachingService.stockIsRegistered(stockName)) {
            logger.warn("StockQuoteServiceImpl.validateStock - Warning: Stock not registered - Stock name: {}.", stockName);
            throw new StockNotRegisteredException();
        }
    }

}
