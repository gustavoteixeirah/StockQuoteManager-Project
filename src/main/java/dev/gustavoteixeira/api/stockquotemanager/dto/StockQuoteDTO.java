package dev.gustavoteixeira.api.stockquotemanager.dto;


import lombok.*;

import java.time.LocalDate;
import java.util.HashMap;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockQuoteDTO {

    private String id;
    private HashMap<LocalDate, String> quotes;

}
