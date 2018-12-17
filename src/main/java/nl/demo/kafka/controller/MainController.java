package nl.demo.kafka.controller;

import nl.demo.kafka.model.NumberOfAccounts;
import nl.demo.kafka.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {
    @Autowired
    ProducerService producerService;

    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("NumberOfAccounts", new NumberOfAccounts());
        return "home";
    }

    @PostMapping("/")
    public String postHomePage(@ModelAttribute NumberOfAccounts numberOfAccounts) {
        producerService.createProducer(numberOfAccounts.getNumberOfAccounts());
        return "done";
    }
}
