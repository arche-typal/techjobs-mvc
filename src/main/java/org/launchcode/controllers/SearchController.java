package org.launchcode.controllers;

import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search") //Request path: /search
public class SearchController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String search(Model model) {
        model.addAttribute("columns", ListController.columnChoices);
        return "search";
    }

    /**@RequestMapping(value="results", method = RequestMethod.GET) //Request path: /search/results
    public String searchDisplay (Model model) {
        model.addAttribute("columns", ListController.columnChoices);
        return "search";
    }**/
    // TODO #1 - Create handler to process search request and display results
    @RequestMapping(value="results", method = RequestMethod.GET)
    public String searchResults(Model model, @RequestParam String searchType, @RequestParam String searchTerm) {
        //*if (type of search).equals("all")
        if (searchType.equals("all")) {
            model.addAttribute("columns", ListController.columnChoices);
            //*create hashmap, print all jobs with searchTerm value in them
            ArrayList<HashMap<String, String>> jobs = JobData.findByValue(searchTerm);
            //model.addAttribute("title", "All Jobs");
            model.addAttribute("jobs", jobs);
            //System.out.println("!!!!!!! ALL RESULTS SEARCH");
            return "search";
        } else {
            model.addAttribute("columns", ListController.columnChoices);
            //*Take selected column ("searchType") and searchTerm and display values in column with searchTerm
            ArrayList<HashMap<String, String>> jobs = JobData.findByColumnAndValue(searchType, searchTerm);
            //model.addAttribute("title", "All Jobs");
            model.addAttribute("jobs", jobs);
            //System.out.println("Successful search before return");
            return "search";
        }

    }

}