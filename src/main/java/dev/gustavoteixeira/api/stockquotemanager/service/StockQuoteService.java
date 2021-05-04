package dev.gustavoteixeira.api.stockquotemanager.service;

import dev.gustavoteixeira.api.stockquotemanager.dto.StockQuoteDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

public interface StockQuoteService {

    Logger logger = LoggerFactory.getLogger(StockQuoteService.class);

    void createNewStockQuote(StockQuoteDTO stock);

    StockQuoteDTO getStockQuoteById(String stockId);

    Set<StockQuoteDTO> getAllStockQuotes();

}
