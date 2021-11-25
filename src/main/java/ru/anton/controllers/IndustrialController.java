package ru.anton.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.anton.entity.industrial.CorrectIndustAnswer;
import ru.anton.entity.industrial.IndustrialQuestions;
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
    public String getAllIndustQuestions(Model model) {
        model.addAttribute("industrquest", industrialRepository.findAll());
        return "industall";
    }

    @GetMapping("/answerall")
    public String getAllAnswerIndustQuest(Model model) {
        model.addAttribute("industAnswer", correctAnswerIndustRepository.findAll());
        return "answerall";
    }

    @GetMapping("/concreteindust/{id}")
    public String getSingleIndustQuestion(Model model, @PathVariable("id") long id) {
        model.addAttribute("question", industrialRepository.findById(id));
        return "industQuestion";
    }

    @PostMapping("/answer/{id}")
    public String getAnswer(Model model, @ModelAttribute IndustrialQuestions answer, @PathVariable("id") long id) {
        model.addAttribute("answer", answer);
        CorrectIndustAnswer answerIndustRepositoryById = correctAnswerIndustRepository.findById(id);

        String resultAnswer = "";
        for (String s : answerIndustRepositoryById.getCorrectAnswer()) {
            resultAnswer = resultAnswer + s + ",";
        }
        model.addAttribute("correctAnswer", resultAnswer.substring(0, resultAnswer.length() - 1));
        model.addAttribute("id", id);
        return "industAnswer";
    }
}
