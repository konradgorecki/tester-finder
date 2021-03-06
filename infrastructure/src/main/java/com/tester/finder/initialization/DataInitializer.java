package com.tester.finder.initialization;

import com.tester.finder.initialization.initializer.DataInitializationFacade;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DataInitializer {

    private static final Logger LOG = LoggerFactory.getLogger(DataInitializer.class);

    private final DataInitializationFacade dataInitializationFacade;

    /**
     * Initialize data on application context startup.
     */
    @EventListener
    public void onContextStartup(ContextRefreshedEvent event) {
//        dataInitializationFacade.initData();
        //TODO yml config file
    }
}
