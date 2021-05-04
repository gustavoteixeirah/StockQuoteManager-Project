package dev.gustavoteixeira.api.stockquotemanager.testUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.gustavoteixeira.api.stockquotemanager.dto.StockQuote;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class StockQuoteUtils {

    public static final String ID = "nflx";
    public static final String NONEXISTENT_ID = "xpto";

    public static StockQuote getStockQuote(String id) {
        return StockQuote.builder()
                .id(id)
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

    public static Set<StockQuote> getStockQuotesSet() {
        Set<StockQuote> quotes = new HashSet<>();
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
