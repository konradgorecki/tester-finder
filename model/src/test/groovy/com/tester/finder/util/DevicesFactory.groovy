package com.tester.finder.util

import com.tester.finder.core.Device

class DevicesFactory {
    static Device create(Integer deviceId) {
        new Device(deviceId, String.valueOf(deviceId))
    }
}
