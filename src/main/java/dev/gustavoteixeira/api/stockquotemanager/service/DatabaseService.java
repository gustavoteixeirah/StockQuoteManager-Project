package dev.gustavoteixeira.api.stockquotemanager.service;

import dev.gustavoteixeira.api.stockquotemanager.dto.StockQuote;

import java.util.Set;

public interface DatabaseService {

    void persistStockQuote(StockQuote stockQuote);

    Set<StockQuote> getAllStockQuotes();

    StockQuote getStockQuoteById(String stockId);
}
