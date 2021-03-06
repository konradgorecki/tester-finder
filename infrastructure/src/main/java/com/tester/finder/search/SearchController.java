package com.tester.finder.search;

import com.tester.finder.search.finder.SearchFacade;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.BeanParam;

@RestController
@AllArgsConstructor
@RequestMapping(path = "api/search/")
public class SearchController {

    private final SearchFacade searchFacade;

    @ResponseBody
    @GetMapping(path = "")
    public FoundTesters findTesters(@BeanParam TesterSearchCriteria searchCriteria) {
        return searchFacade.findTesters(searchCriteria);
    }
}
