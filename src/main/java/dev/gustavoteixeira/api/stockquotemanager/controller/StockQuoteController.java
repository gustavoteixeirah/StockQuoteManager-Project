package dev.gustavoteixeira.api.stockquotemanager.controller;


import dev.gustavoteixeira.api.stockquotemanager.dto.StockQuote;
import dev.gustavoteixeira.api.stockquotemanager.service.StockQuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Controller
@RequestMapping("/stock-quote")
public class StockQuoteController {


    @Autowired
    private StockQuoteService stockQuoteService;

    /**
     * Create a Stock Quote
     */
    @PostMapping
    public ResponseEntity<Void> createNewStockQuote(@RequestBody StockQuote stockQuote) {

        stockQuoteService.createNewStockQuote(stockQuote);
        //Implementar URI location

        return ResponseEntity.ok().build();
    }


    /**
     * Read a Stock Quote by id
     */
    @GetMapping("/{stockId}")
    public ResponseEntity<StockQuote> getStockQuote(@PathVariable String stockId) {
        return ResponseEntity.ok(stockQuoteService.getStockQuoteById(stockId));
    }

    /**
     * Read all Stock Quotes
     */
    @GetMapping
    public ResponseEntity<Set<StockQuote>> getStockQuote() {
        return ResponseEntity.ok(stockQuoteService.getAllStockQuotes());
    }


}
