package dev.gustavoteixeira.api.stockquotemanager.controller;


import dev.gustavoteixeira.api.stockquotemanager.dto.StockQuote;
import dev.gustavoteixeira.api.stockquotemanager.service.StockQuoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Set;

@Controller
@RequestMapping("/stock-quote")
public class StockQuoteController {

    private static final Logger logger = LoggerFactory.getLogger(StockQuoteController.class);

    @Autowired
    private StockQuoteService stockQuoteService;

    /**
     * Create a Stock Quote
     */
    @PostMapping
    public ResponseEntity<Void> createNewStockQuote(@RequestBody StockQuote stockQuote) {
        logger.info("StockQuoteController.createNewStockQuote - Start - Stock quote identifier: {}.", stockQuote.getId());

        stockQuoteService.createNewStockQuote(stockQuote);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(stockQuote.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    /**
     * Read a Stock Quote by id
     */
    @GetMapping("/{stockId}")
    public ResponseEntity<StockQuote> getStockQuoteById(@PathVariable String stockId) {
        logger.info("StockQuoteController.getStockQuoteById - Start - Stock quote identifier: {}.", stockId);
        return ResponseEntity.ok(stockQuoteService.getStockQuoteById(stockId));
    }

    /**
     * Read all Stock Quotes
     */
    @GetMapping
    public ResponseEntity<Set<StockQuote>> getStockQuote() {
        logger.info("StockQuoteController.getStockQuote - Start.");
        return ResponseEntity.ok(stockQuoteService.getAllStockQuotes());
    }

}
