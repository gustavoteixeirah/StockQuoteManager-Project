package dev.gustavoteixeira.api.stockquotemanager.service;

import dev.gustavoteixeira.api.stockquotemanager.dto.StockQuote;

import java.util.Set;

public interface StockQuoteService {

    void createNewStockQuote(StockQuote stock);

    StockQuote getStockQuoteById(String stockId);

    Set<StockQuote> getAllStockQuotes();

}
