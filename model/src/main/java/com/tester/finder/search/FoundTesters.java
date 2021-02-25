package com.tester.finder.search;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.List;

@Value
@AllArgsConstructor
public class FoundTesters {
    List<FoundTester> testers;
}
