package dev.gustavoteixeira.api.stockquotemanager.service;

import dev.gustavoteixeira.api.stockquotemanager.client.StockManagerClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import static dev.gustavoteixeira.api.stockquotemanager.testUtils.StockQuoteUtils.getStockList;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
class CachingServiceTests {

    @Autowired
    private CachingService cachingService;

    @MockBean
    private StockManagerClient stockManager;

    @Test
    void populateCache() {
        when(stockManager.getAllStocks()).thenReturn(getStockList());

        cachingService.populateCache();

        verify(stockManager, times(1)).getAllStocks();
    }

}
