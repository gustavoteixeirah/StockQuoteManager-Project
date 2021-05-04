package dev.gustavoteixeira.api.stockquotemanager.controller;

import dev.gustavoteixeira.api.stockquotemanager.dto.StockQuote;
import dev.gustavoteixeira.api.stockquotemanager.service.CachingService;
import dev.gustavoteixeira.api.stockquotemanager.service.StockQuoteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static dev.gustavoteixeira.api.stockquotemanager.testUtils.StockQuoteUtils.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class StockCacheControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CachingService cachingService;

    @Test
    void creatingNewStockQuoteShouldReturnStatusIsCreated() throws Exception {
        doNothing().when(cachingService).clearCache();

        mvc.perform(delete("/stockcache"))
                .andExpect(status().isOk());
    }


}
