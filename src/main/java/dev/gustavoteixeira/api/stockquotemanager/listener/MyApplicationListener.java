package dev.gustavoteixeira.api.stockquotemanager.listener;

import dev.gustavoteixeira.api.stockquotemanager.client.StockManagerClient;
import dev.gustavoteixeira.api.stockquotemanager.dto.ApplicationDataDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(0)
class MyApplicationListener
        implements ApplicationListener<ApplicationReadyEvent> {

    private static final Logger logger = LoggerFactory.getLogger(MyApplicationListener.class);

    @Autowired
    private StockManagerClient stockManager;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        logger.info("MyApplicationListener.onApplicationEvent - Start - Registering this application on stock-manager...");
        ApplicationDataDTO applicationData = ApplicationDataDTO.builder()
                .host("localhost")
                .port(8081).build();
        stockManager.notify(applicationData);
        logger.info("MyApplicationListener.onApplicationEvent - End - Registered this application on stock-manager.");
    }

}
