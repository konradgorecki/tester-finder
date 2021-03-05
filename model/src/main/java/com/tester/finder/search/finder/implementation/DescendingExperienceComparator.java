package com.tester.finder.search.finder.implementation;

import com.tester.finder.search.FoundTester;

import java.util.Comparator;

public class DescendingExperienceComparator implements Comparator<FoundTester> {

    @Override
    public int compare(FoundTester o1, FoundTester o2) {
        return (o1.getExperience() < o2.getExperience()) ? 1 : (o1.getExperience().equals(o2.getExperience()) ? 0 : -1);
    }
}
