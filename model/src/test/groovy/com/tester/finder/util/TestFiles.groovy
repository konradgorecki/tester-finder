package com.tester.finder.util

class TestFiles {
    private final File testersTestFile
    private final File devicesTestFile
    private final File testerDeviceMatchingFile
    private final File bugsFile

    TestFiles(File testersTestFile, File devicesTestFile, File testerDeviceMatchingFile, File bugsFile) {
        this.testersTestFile = testersTestFile
        this.devicesTestFile = devicesTestFile
        this.testerDeviceMatchingFile = testerDeviceMatchingFile
        this.bugsFile = bugsFile
    }

    File getTestersTestFile() {
        return testersTestFile
    }

    File getDevicesTestFile() {
        return devicesTestFile
    }

    File getTesterDeviceMatchingFile() {
        return testerDeviceMatchingFile
    }

    File getBugsFile() {
        return bugsFile
    }
}
