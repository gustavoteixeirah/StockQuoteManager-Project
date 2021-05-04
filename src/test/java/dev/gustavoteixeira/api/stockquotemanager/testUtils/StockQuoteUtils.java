package dev.gustavoteixeira.api.stockquotemanager.testUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.gustavoteixeira.api.stockquotemanager.dto.StockDTO;
import dev.gustavoteixeira.api.stockquotemanager.dto.StockQuoteDTO;

import java.time.LocalDate;
import java.util.*;

public class StockQuoteUtils {

    public static final String ID = "nflx";
    public static final String NONEXISTENT_ID = "xpto";

    public static StockQuoteDTO getStockQuote(String id) {
        return StockQuoteDTO.builder()
                .id(id == null ? ID : id)
                .quotes(getQuotes())
                .build();
    }

    public static StockQuoteDTO getStockQuote() {
        return StockQuoteDTO.builder()
                .id(ID)
                .quotes(getQuotes())
                .build();
    }

    public static HashMap<LocalDate, String> getQuotes() {
        HashMap<LocalDate, String> quotes = new HashMap<>();
        quotes.put(LocalDate.now().minusDays(1), "10");
        quotes.put(LocalDate.now().minusDays(2), "12");
        quotes.put(LocalDate.now().minusDays(3), "9");
        return quotes;
    }

    public static List<StockDTO> getStockList() {
        List<StockDTO> stocks = new ArrayList<>();
        stocks.add(StockDTO.builder().id(ID).build());
        stocks.add(StockDTO.builder().id(ID.concat("1")).build());
        stocks.add(StockDTO.builder().id(ID.concat("2")).build());
        stocks.add(StockDTO.builder().id(ID.concat("3")).build());
        return stocks;
    }

    public static Set<StockQuoteDTO> getStockQuotesSet() {
        Set<StockQuoteDTO> quotes = new HashSet<>();
        quotes.add(getStockQuote(ID.concat("1")));
        quotes.add(getStockQuote(ID.concat("2")));
        quotes.add(getStockQuote(ID.concat("3")));
        return quotes;
    }

    public static String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }


}
