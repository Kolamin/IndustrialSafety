package ru.anton.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.anton.repository.boiler.BoilerRepository;
import ru.anton.repository.boiler.CorrectAnswerBoilerRepo;

@Controller
@RequestMapping("boiler")
public class BoilerController {
    private final BoilerRepository boilerRepository;
    private final CorrectAnswerBoilerRepo correctAnswerBoilerRepo;

    public BoilerController(BoilerRepository boilerRepository, CorrectAnswerBoilerRepo correctAnswerBoilerRepo) {
        this.boilerRepository = boilerRepository;
        this.correctAnswerBoilerRepo = correctAnswerBoilerRepo;
    }


    @GetMapping("/boilerall")
    public String getBoilerAllQuestions(Model model){
        model.addAttribute( "questions", boilerRepository.findAll() );
        return "boilerall";
    }
}
