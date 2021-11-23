package ru.anton.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.anton.repository.CorrectAnswerIndustRepository;
import ru.anton.repository.IndustrialRepository;

@Controller
public class IndustrialController {

    private final IndustrialRepository industrialRepository;
    private final CorrectAnswerIndustRepository correctAnswerIndustRepository;

    public IndustrialController(IndustrialRepository industrialRepository, CorrectAnswerIndustRepository correctAnswerIndustRepository) {
        this.industrialRepository = industrialRepository;
        this.correctAnswerIndustRepository = correctAnswerIndustRepository;
    }

    @GetMapping("/industall")
    public String getAllIndustQuestions(Model model){
        model.addAttribute("industrquest", industrialRepository.findAll());
        return "industall";
    }

    @GetMapping("/answerall")
    public String getAllAnswerIndustQuest(Model model){
        model.addAttribute("industAnswer", correctAnswerIndustRepository.findAll());
        return "answerall";
    }
}
