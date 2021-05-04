package dev.gustavoteixeira.api.stockquotemanager.service.impl;

import dev.gustavoteixeira.api.stockquotemanager.client.StockManagerClient;
import dev.gustavoteixeira.api.stockquotemanager.dto.StockDTO;
import dev.gustavoteixeira.api.stockquotemanager.service.CachingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CachingServiceImpl implements CachingService {

    private List<String> registeredStocks = new ArrayList<>();

    @Autowired
    private StockManagerClient stockManager;

    @Override
    public void populateCache() {
        logger.info("CachingServiceImpl.populateCache - Start.");
        /**
         * Get the currently registered stocks
         */
        List<StockDTO> stocks = stockManager.getAllStocks();

        /**
         * Map the Stock Object into String object (that is, only the name of the stock)
         * Then save it into the cache "registeredStocks"
         */
        registeredStocks.addAll(
                stocks.stream()
                        .map(StockDTO::getId)
                        .collect(Collectors.toList()));
    }

    @Override
    public boolean stockIsRegistered(String stockName) {
        logger.info("CachingServiceImpl.stockIsRegistered - Start - Stock name: {}.", stockName);
        /**
         * When validating a stock, whenever the cache is empty, it should populate it from stock-manager service
         */
        if (registeredStocks.isEmpty()) {
            populateCache();
        }
        /**
         * If cache is not empty, it should validate with cached data.
         */
        return registeredStocks.contains(stockName);
    }

    @Override
    public void clearCache() {
        logger.info("CachingServiceImpl.cleanCache - Start.");
        registeredStocks.clear();
        logger.info("CachingServiceImpl.cleanCache - End.");
    }

}
