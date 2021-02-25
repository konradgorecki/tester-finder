package com.tester.finder.search.util

import com.tester.finder.core.Bug
import com.tester.finder.core.Device
import com.tester.finder.core.Tester
import com.tester.finder.search.util.Repositories

class TestRepositories {

    static Repositories prepareRepositories(List<Tester> testers, List<Device> devices, List<Bug> bugs) {
        new Repositories()//TODO init in mem repositories here
    }

}
