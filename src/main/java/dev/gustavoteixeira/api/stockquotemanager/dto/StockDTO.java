package dev.gustavoteixeira.api.stockquotemanager.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockDTO {

    private String id;
    private String description;

}
