package org.launchcode.javawebdevtechjobsmvc.controllers;

import org.launchcode.javawebdevtechjobsmvc.models.Job;
import org.launchcode.javawebdevtechjobsmvc.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

import static org.launchcode.javawebdevtechjobsmvc.controllers.ListController.columnChoices;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
        return "search";
    }

    // TODO #3 - Create a handler to process a search request and render the updated search view.

    //copied from List Controller
    @PostMapping(value = "/results") //Try with and without slash
    public String displaySearchResults(Model model, @RequestParam String searchTerm, @RequestParam String searchType) {
        ArrayList jobs = new ArrayList();
        if (searchTerm.equals("all") || searchTerm.equals("")){
            jobs = JobData.findAll();

        } else {
            jobs = JobData.findByColumnAndValue(searchType, searchTerm);

        }
        model.addAttribute("jobs", jobs);
        model.addAttribute("columns", columnChoices); //not sure

        return "search";
    }

}
