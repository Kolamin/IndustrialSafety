package ru.anton.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.anton.IndustrialSafetyApplication;
import ru.anton.entity.industrial.CorrectIndustAnswer;
import ru.anton.entity.industrial.IndustrialQuestions;
import ru.anton.entity.oil.OilQuestions;
import ru.anton.repository.industrial.CorrectAnswerIndustRepository;
import ru.anton.repository.industrial.IndustrialRepository;
import ru.anton.repository.oil.OilAllQuestRepo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

@Slf4j
@Configuration
public class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(IndustrialRepository indRepo,
                                   CorrectAnswerIndustRepository correctAnswerIndustRepository,
                                   OilAllQuestRepo oilAllQuestRepo) {
        return args -> {
            IndustrialSafetyApplication obj = new IndustrialSafetyApplication();

            InputStream inputIndustrialQuestions = obj
                    .getClass()
                    .getClassLoader()
                    .getResourceAsStream("static/A_1_tests.txt");

            InputStream inputIndustAnswer = obj
                    .getClass()
                    .getClassLoader()
                    .getResourceAsStream("static/A_1_answer.txt");

            //------------------------------------------------------------
            //----------------- For oil ----------------------------------

            InputStream inputOilQuestions = obj
                    .getClass()
                    .getClassLoader()
                    .getResourceAsStream("static/B_1_7_ALL.txt");

            //-----------------------------------------------------------

            String[] temp = getArray(inputIndustrialQuestions);
            String[] arrayQuestion = Arrays.copyOfRange(temp, 1, temp.length);

            for (String s : arrayQuestion) {
                String[] split = s.split("\\n");
                int length = split.length;
                log.info("Preload industrial question database " + indRepo
                        .save(new IndustrialQuestions(split[0],
                                Arrays.asList(Arrays.copyOfRange(split, 1, length)))));
            }

            temp = getArray(inputIndustAnswer);
            arrayQuestion = Arrays.copyOfRange(temp, 1, temp.length);
            for (String s : arrayQuestion) {
                String[] split = s.split("\\n");
                log.info("Preload answer for industrial" + correctAnswerIndustRepository
                        .save(new CorrectIndustAnswer(split[0] ,Arrays
                                .asList(Arrays.
                                        copyOfRange(split, 1, split.length)))));
            }

            //----------------------------------------------------------------------
            temp = getArray(inputOilQuestions);
            arrayQuestion = Arrays.copyOfRange(temp, 1, temp.length);
            for (String s : arrayQuestion) {
                String[] split = s.split("\\n");
                int length = split.length;
                log.info("Preload oil question database " + oilAllQuestRepo
                        .save(new OilQuestions(split[0],
                                Arrays.asList(Arrays.copyOfRange(split, 1, length)))));
            }
        };
    }

    private String[] getArray(InputStream inputStream) throws IOException {
        StringBuilder content;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            content = new StringBuilder();

            String st;

            while ((st = br.readLine()) != null) {
                content.append(st)
                        .append("\n");
            }
        }
        return content.toString()
                .split("(\\d+\\.\\s)|(Вопрос \\d+)");
    }
}
