package dev.gustavoteixeira.api.stockquotemanager.controller;

import dev.gustavoteixeira.api.stockquotemanager.dto.StockQuoteDTO;
import dev.gustavoteixeira.api.stockquotemanager.exception.StockNotFoundException;
import dev.gustavoteixeira.api.stockquotemanager.exception.StockNotRegisteredException;
import dev.gustavoteixeira.api.stockquotemanager.service.StockQuoteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static dev.gustavoteixeira.api.stockquotemanager.testUtils.StockQuoteUtils.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class StockQuoteControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private StockQuoteService stockQuoteService;

    @Test
    void creatingNewStockQuoteShouldReturnStatusIsCreated() throws Exception {
        StockQuoteDTO requestBody = getStockQuote(ID);

        doNothing().when(stockQuoteService).createNewStockQuote(any(StockQuoteDTO.class));

        mvc.perform(post("/stock-quote")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapToJson(requestBody)))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(header().string("Location", "http://localhost/stock-quote/nflx"));
    }

    @Test
    void creatingNewStockQuoteThatDoesNotExistShouldReturnStatusBadRequest() throws Exception {
        StockQuoteDTO requestBody = getStockQuote(NONEXISTENT_ID);

        doThrow(StockNotRegisteredException.class)
                .when(stockQuoteService).createNewStockQuote(any(StockQuoteDTO.class));

        mvc.perform(post("/stock-quote")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapToJson(requestBody)))
                .andExpect(status().isBadRequest());
    }


    @Test
    void getStockQuoteByIdShouldReturnStatusOk() throws Exception {
        when(stockQuoteService.getStockQuoteById(ID)).thenReturn(getStockQuote(ID));

        mvc.perform(get("/stock-quote/" + ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getStockQuoteByIdWithNonexistentIdShouldReturnStatusNotFound() throws Exception {
        doThrow(StockNotFoundException.class)
                .when(stockQuoteService).getStockQuoteById(NONEXISTENT_ID);

        mvc.perform(get("/stock-quote/" + NONEXISTENT_ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void getStockQuoteShouldReturnStatusOk() throws Exception {
        when(stockQuoteService.getAllStockQuotes()).thenReturn(getStockQuotesSet());

        mvc.perform(get("/stock-quote")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
