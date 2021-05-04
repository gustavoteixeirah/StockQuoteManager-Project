package dev.gustavoteixeira.api.stockquotemanager.service;

import dev.gustavoteixeira.api.stockquotemanager.dto.StockQuoteDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

public interface DatabaseService {

    Logger logger = LoggerFactory.getLogger(DatabaseService.class);

    void persistStockQuote(StockQuoteDTO stockQuote);

    Set<StockQuoteDTO> getAllStockQuotes();

    StockQuoteDTO getStockQuoteById(String stockId);
}
