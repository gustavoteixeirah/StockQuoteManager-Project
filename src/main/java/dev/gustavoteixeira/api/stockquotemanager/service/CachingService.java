package dev.gustavoteixeira.api.stockquotemanager.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface CachingService {

    Logger logger = LoggerFactory.getLogger(CachingService.class);

    void populateCache();

    boolean stockIsRegistered(String stockName);

    void clearCache();
}
