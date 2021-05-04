package dev.gustavoteixeira.api.stockquotemanager.service;

import java.util.ArrayList;
import java.util.List;

public interface CachingService {

    List<String> registeredStocks = new ArrayList<>();

    void populateCache();

    boolean stockIsRegistered(String stockName);

    void cleanCache();
}
