package dev.gustavoteixeira.api.stockquotemanager.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public interface CachingService {

    Logger logger = LoggerFactory.getLogger(CachingService.class);

    List<String> registeredStocks = new ArrayList<>();

    void populateCache();

    boolean stockIsRegistered(String stockName);

    void clearCache();
}
