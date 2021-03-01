package com.tester.finder.search.util

import com.tester.finder.core.Bug
import com.tester.finder.core.Device
import com.tester.finder.core.Tester

import java.util.stream.Collectors
import java.util.stream.IntStream

class BugsFactory {

    static List<Bug> create(List<Tester> testers, Device device, Integer bugsCount) {
        testers.stream()
                .flatMap(tester -> create(tester, device, bugsCount).stream())
                .collect(Collectors.toList())
    }

    static List<Bug> create(Tester tester, Device device, Integer bugsCount) {
        IntStream.range(0, bugsCount)
                .mapToObj(i -> new Bug(generateBugId(), device, tester))
                .collect(Collectors.toList())
    }

    /**
     * Generate random bug id, so that w don't have to take care of that in tests.
     * Since in the unit tests we are gonna be using small number of total bugs per test
     * this should give us a reasonably good dispersion
     *
     * @return random int in the range 0 - Integer.MAX_VALUE
     */
    static Integer generateBugId() {
        Random rand = new Random()
        rand.nextInt()
    }
}
