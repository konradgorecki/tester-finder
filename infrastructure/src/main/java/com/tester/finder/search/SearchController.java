package com.tester.finder.search;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tester.finder.search.finder.SearchFacade;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping(path = "api/search/")
public class SearchController {

    private final SearchFacade searchFacade;

    @ResponseBody
    @GetMapping(path = "results/{searchId}")
    public FoundTesters findTesters(@PathVariable("searchId") UUID searchId) {
        return searchFacade.executeSearch(searchId);
    }

    @ResponseBody
    @PostMapping(path = "create")
    public UUID createSearch(@RequestBody TesterSearchCriteriaJson searchCriteria) {
        return searchFacade.createSearch(searchCriteria.toTesterSearchCriteria());
    }

    public static class TesterSearchCriteriaJson {
        private List<String> countryCodes;
        private List<Integer> deviceIds;

        public TesterSearchCriteriaJson(
                @JsonProperty("countryCodes") List<String> countryCodes,
                @JsonProperty("deviceIds") List<Integer> deviceIds) {
            this.countryCodes = countryCodes;
            this.deviceIds = deviceIds;
        }

        TesterSearchCriteria toTesterSearchCriteria() {
            return new TesterSearchCriteria(countryCodes, deviceIds);
        }
    }
}
