package org.launchcode.techjobs.mvc.controllers;

import org.launchcode.techjobs.mvc.models.Job;
import org.launchcode.techjobs.mvc.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

import static org.launchcode.techjobs.mvc.controllers.ListController.columnChoices;


/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @GetMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
        return "search";
    }

    // TODO #3 - Create a handler to process a search request and render the updated search view.
    //form = post
    //take model param
    //2 param type of search, search term from search.html
    @PostMapping("results")
    public String displaySearchResults(Model model, @RequestParam String searchType,
                                       @RequestParam String searchTerm) {

        //if all is entered or box is empty call findAll() method from JobData
        //Otherwise send search info to findByColumnAndValue
        //Either or, store results in jobs ArrayList

        //Pass jobs into search.html via model param
        //Pass ListController.columnChoices into view like search handler

    ArrayList<Job> jobs;
    if (searchTerm.equals("all") || searchTerm.isEmpty()) {
        jobs = JobData.findAll();

        model.addAttribute("title", "All Jobs");
    } else {
        jobs = JobData.findByColumnAndValue(searchType, searchTerm);
    }
        model.addAttribute("jobs", jobs);
        model.addAttribute("columns", columnChoices);

        return "search";
    }
}
