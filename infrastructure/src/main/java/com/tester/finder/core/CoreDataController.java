package com.tester.finder.core;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "api/core/")
public class CoreDataController {

    private final CoreDataFacade coreDataFacade;

    @ResponseBody
    @GetMapping(path = "countries")
    public List<Country> getCountries() {
        return coreDataFacade.findCountries(true);
    }

    @ResponseBody
    @GetMapping(path = "devices")
    public List<Device> getDevices() {
        return coreDataFacade.findDevices(true);
    }

}
