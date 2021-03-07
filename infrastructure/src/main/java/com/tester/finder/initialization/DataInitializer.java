package com.tester.finder.initialization;

import com.tester.finder.initialization.initializer.DataInitializationFacade;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.File;
import java.time.Duration;
import java.time.Instant;

@Component
@AllArgsConstructor
public class DataInitializer {

    private static final Logger LOG = LoggerFactory.getLogger(DataInitializer.class);

    private final DataInitializationFacade dataInitializationFacade;
    private final SourceFilesProperties sourceFilesProperties;

    /**
     * Initialize data on application context startup.
     */
    @EventListener
    public void onContextStartup(ContextRefreshedEvent event) {
        LOG.info("Data initialization started.");
        Instant start = Instant.now();

        File testersFile = new File(sourceFilesProperties.getTestersFilePath());
        File devicesFile = new File(sourceFilesProperties.getDevicesFilePath());
        File testerDevicesMappingsFile = new File(sourceFilesProperties.getTesterDevicesMappingFilePath());
        File bugsFile = new File(sourceFilesProperties.getBugsFilePath());
        dataInitializationFacade.initData(testersFile, devicesFile, testerDevicesMappingsFile, bugsFile);

        Instant stop = Instant.now();
        LOG.info("Data initialization finished in: " + Duration.between(start, stop).toMillis() + " ms");
    }
}
