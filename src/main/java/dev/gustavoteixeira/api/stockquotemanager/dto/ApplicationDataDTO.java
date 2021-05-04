package dev.gustavoteixeira.api.stockquotemanager.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApplicationDataDTO {

    private String host;
    private int port;

}
