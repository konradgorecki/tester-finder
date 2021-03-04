package com.tester.finder.initialization.initializer;

import java.io.File;

public interface DataInitializer {
    void initData(File testersFile, File devicesFile, File testerDevicesFile, File bugsFile);
}
