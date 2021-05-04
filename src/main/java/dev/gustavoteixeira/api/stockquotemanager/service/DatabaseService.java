package dev.gustavoteixeira.api.stockquotemanager.service;

import dev.gustavoteixeira.api.stockquotemanager.dto.StockQuote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

public interface DatabaseService {

    Logger logger = LoggerFactory.getLogger(DatabaseService.class);

    void persistStockQuote(StockQuote stockQuote);

    Set<StockQuote> getAllStockQuotes();

    StockQuote getStockQuoteById(String stockId);
}
