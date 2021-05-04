package dev.gustavoteixeira.api.stockquotemanager.client;

import dev.gustavoteixeira.api.stockquotemanager.dto.ApplicationDataDTO;
import dev.gustavoteixeira.api.stockquotemanager.dto.Stock;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(value = "stock", url = "localhost:8080")
public interface StockManagerClient {

    @GetMapping("/stock")
    List<Stock> getAllStocks();

    @PostMapping("/notification")
    void notify(@RequestBody ApplicationDataDTO applicationData);

}
