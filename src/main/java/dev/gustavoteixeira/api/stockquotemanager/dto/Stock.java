package dev.gustavoteixeira.api.stockquotemanager.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Stock {

    private String id;
    private String description;

}
