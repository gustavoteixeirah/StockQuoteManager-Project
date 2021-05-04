package dev.gustavoteixeira.api.stockquotemanager.controller;


import dev.gustavoteixeira.api.stockquotemanager.service.CachingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/stockcache")
public class StockCacheController {

    private static final Logger logger = LoggerFactory.getLogger(StockCacheController.class);

    @Autowired
    private CachingService cachingService;

    @DeleteMapping
    public ResponseEntity<Void> cleanCache() {
        logger.info("StockCacheController.cleanCache - Start - Cleaning cache.");
        cachingService.cleanCache();


        logger.info("StockCacheController.cleanCache - End - Finished cleaning cache.");
        return ResponseEntity.ok().build();
    }

}
