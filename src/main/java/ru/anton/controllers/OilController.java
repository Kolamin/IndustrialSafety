package ru.anton.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.anton.repository.oil.OilAllQuestRepo;

@Controller
@RequestMapping("oil")
public class OilController {
    private final OilAllQuestRepo oilAllQuestRepo;

    public OilController(OilAllQuestRepo oilAllQuestRepo) {
        this.oilAllQuestRepo = oilAllQuestRepo;
    }

    @GetMapping("/oilall")
    public String getOilAllQuestions(Model model){
        model.addAttribute("oilquestions", oilAllQuestRepo.findAll());
        return "oilallquest";
    }
}
