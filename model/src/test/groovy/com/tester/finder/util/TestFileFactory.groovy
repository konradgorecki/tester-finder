package com.tester.finder.util

class TestFileFactory {

    static TestFiles create() {
        def testersTestFile = getTestFile("testers.csv")
        def devicesTestFile = getTestFile("devices.csv")
        def testerDeviceMatchingFile = getTestFile("tester_device.csv")
        def bugsFile = getTestFile("bugs.csv")

        new TestFiles(testersTestFile, devicesTestFile, testerDeviceMatchingFile, bugsFile)
    }

    private static File getTestFile(String fileName) {
        ClassLoader classLoader = TestFileFactory.class.getClassLoader()

        URL resource = classLoader.getResource(fileName)
        if (resource == null) {
            throw new IllegalArgumentException("file not found! " + fileName)
        }

        //File file = new File(resource.getFile())
        new File(resource.toURI())
    }
}
