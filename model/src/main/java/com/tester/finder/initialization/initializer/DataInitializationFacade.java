package com.tester.finder.initialization.initializer;

import java.io.File;

public interface DataInitializationFacade {
    void initData(File testersFile, File devicesFile, File testerDevicesFile, File bugsFile);
}
