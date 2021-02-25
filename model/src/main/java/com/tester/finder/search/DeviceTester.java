package com.tester.finder.search;

import com.tester.finder.core.Device;
import com.tester.finder.core.Tester;
import lombok.Value;

@Value
public class DeviceTester {
    Tester tester;
    Device device;
    Integer experience;
}
