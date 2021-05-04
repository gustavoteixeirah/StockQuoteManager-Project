package dev.gustavoteixeira.api.stockquotemanager.controller;


import dev.gustavoteixeira.api.stockquotemanager.service.CachingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/stockcache")
public class StockCacheController {

    @Autowired
    private CachingService cachingService;

    @DeleteMapping
    public ResponseEntity<Void> cleanCache(){

        cachingService.cleanCache();

        return ResponseEntity.ok().build();
    }

}
