package dev.gustavoteixeira.api.stockquotemanager.service;

import dev.gustavoteixeira.api.stockquotemanager.dto.StockQuoteDTO;
import dev.gustavoteixeira.api.stockquotemanager.exception.StockNotFoundException;
import dev.gustavoteixeira.api.stockquotemanager.exception.StockNotRegisteredException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.Set;

import static dev.gustavoteixeira.api.stockquotemanager.testUtils.StockQuoteUtils.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
public class StockQuoteServiceTests {

    @Autowired
    private StockQuoteService stockQuoteService;

    @MockBean
    private CachingService cachingService;

    @MockBean
    private DatabaseService databaseService;

    @Test
    void getAllStockQuotes() {
        when(databaseService.getAllStockQuotes()).thenReturn(getStockQuotesSet());
        Set<StockQuoteDTO> result = stockQuoteService.getAllStockQuotes();

        verify(databaseService, times(1)).getAllStockQuotes();
        assertThat(result).isNotNull();
    }

    @Test
    void createNewStockQuote() {
        when(cachingService.stockIsRegistered(any())).thenReturn(true);
        doNothing().when(databaseService).persistStockQuote(getStockQuote());

        stockQuoteService.createNewStockQuote(getStockQuote());

        verify(cachingService, times(1)).stockIsRegistered(any());
        verify(databaseService, times(1)).persistStockQuote(any());
    }

    @Test
    void createNewStockQuoteThatIsNotRegisteredShouldThrowStockNotRegisteredException() {
        when(cachingService.stockIsRegistered(any())).thenReturn(false);

        RuntimeException exception = Assertions.assertThrows(StockNotRegisteredException.class,
                () -> stockQuoteService.createNewStockQuote(getStockQuote()));

        assertThat(exception).isInstanceOf(StockNotRegisteredException.class);
    }

    @Test
    void getStockQuoteById() {
        when(databaseService.getStockQuoteById(anyString())).thenReturn(getStockQuote());

        StockQuoteDTO result = stockQuoteService.getStockQuoteById(ID);

        verify(databaseService, times(1)).getStockQuoteById(any());
        assertThat(result).isNotNull();
    }

    @Test
    void getStockQuoteByIdWithNonexistentIdShouldThrowStockNotFoundException() {
        doThrow(StockNotFoundException.class).when(databaseService).getStockQuoteById(NONEXISTENT_ID);

        RuntimeException exception = Assertions.assertThrows(StockNotFoundException.class,
                () -> stockQuoteService.getStockQuoteById(NONEXISTENT_ID));

        assertThat(exception).isInstanceOf(StockNotFoundException.class);
    }

}
