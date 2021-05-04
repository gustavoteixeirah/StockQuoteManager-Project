package dev.gustavoteixeira.api.stockquotemanager.service.impl;

import dev.gustavoteixeira.api.stockquotemanager.dto.StockQuoteDTO;
import dev.gustavoteixeira.api.stockquotemanager.entity.QuoteEntity;
import dev.gustavoteixeira.api.stockquotemanager.exception.StockNotFoundException;
import dev.gustavoteixeira.api.stockquotemanager.repository.QuoteRepository;
import dev.gustavoteixeira.api.stockquotemanager.service.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.groupingBy;

@Service
public class DatabaseServiceImpl implements DatabaseService {

    @Autowired
    private QuoteRepository quoteRepository;


    @Override
    public void persistStockQuote(StockQuoteDTO newStockQuote) {
        logger.info("DatabaseServiceImpl.persistStockQuote - Start - Stock quote name: {}.", newStockQuote.getId());

        newStockQuote.getQuotes()
                .forEach((k, v) -> {
                    /**
                     * Converts from StockQuoteDTO to QuoteEntity
                     * Obs:
                     *      k = LocalDate
                     *      v = price
                     */
                    QuoteEntity newQuote = QuoteEntity.builder()
                            .stockId(newStockQuote.getId())
                            .date(k)
                            .price(v).build();
                    /**
                     * Saves it into the database
                     */
                    quoteRepository.save(newQuote);
                });
    }

    @Override
    public Set<StockQuoteDTO> getAllStockQuotes() {
        logger.info("DatabaseServiceImpl.getAllStockQuotes - Start.");
        List<QuoteEntity> quoteEntities = quoteRepository.findAll();
        Set<StockQuoteDTO> stockQuoteSet = getStockQuoteDTOS(quoteEntities);
        return stockQuoteSet;
    }

    private Set<StockQuoteDTO> getStockQuoteDTOS(List<QuoteEntity> quoteEntities) {
        Set<StockQuoteDTO> stockQuoteSet = new HashSet<>();
        quoteEntities.stream()
                .collect(groupingBy(QuoteEntity::getStockId))
                .forEach((k, v) -> {
                    String stockId = k;
                    HashMap<LocalDate, String> quotes = new HashMap<>();
                    v.forEach((quoteEntity) -> {
                        quotes.put(quoteEntity.getDate(), quoteEntity.getPrice());
                    });
                    stockQuoteSet.add(
                            StockQuoteDTO.builder()
                                    .id(stockId)
                                    .quotes(quotes).build());
                });
        return stockQuoteSet;
    }


    @Override
    public StockQuoteDTO getStockQuoteById(String stockId) {
        logger.info("DatabaseServiceImpl.getStockQuoteById - Start - Stock quote name: {}.", stockId);

        List<QuoteEntity> quoteEntityList = quoteRepository.findByStockId(stockId);
        Set<StockQuoteDTO> stockQuoteDTOS = getStockQuoteDTOS(quoteEntityList);

        return stockQuoteDTOS.stream().findFirst().orElseThrow(StockNotFoundException::new);
    }

}
