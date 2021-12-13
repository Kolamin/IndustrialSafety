package ru.anton.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import ru.anton.IndustrialSafetyApplication;
import ru.anton.entity.boiler.BoilerQuestions;
import ru.anton.entity.boiler.ConcreteBoilerAnswer;
import ru.anton.entity.industrial.CorrectIndustAnswer;
import ru.anton.entity.industrial.IndustrialQuestions;
import ru.anton.entity.oil.CorrectOilAnswer;
import ru.anton.entity.oil.OilQuestions;
import ru.anton.repository.boiler.BoilerRepository;
import ru.anton.repository.boiler.CorrectAnswerBoilerRepo;
import ru.anton.repository.industrial.CorrectAnswerIndustRepository;
import ru.anton.repository.industrial.IndustrialRepository;
import ru.anton.repository.oil.CorrectAnswerOilRepo;
import ru.anton.repository.oil.OilAllQuestRepo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

@Slf4j
@Component
public class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(IndustrialRepository indRepo,
                                   CorrectAnswerIndustRepository correctAnswerIndustRepository,
                                   OilAllQuestRepo oilAllQuestRepo,
                                   CorrectAnswerOilRepo answerOilRepo,
                                   BoilerRepository boilerRepository,
                                   CorrectAnswerBoilerRepo correctAnswerBoilerRepo) {
        return args -> {

            //----------------------Load content-----------------------
            IndustrialSafetyApplication obj = new IndustrialSafetyApplication();

            InputStream inputIndustrialQuestions = obj
                    .getClass()
                    .getClassLoader()
                    .getResourceAsStream( "static/A_1_tests.txt" );

            InputStream inputIndustAnswer = obj
                    .getClass()
                    .getClassLoader()
                    .getResourceAsStream( "static/A_1_answer.txt" );

            //------------------------------------------------------------
            //----------------- For oil ----------------------------------

            InputStream inputOilQuestions = obj
                    .getClass()
                    .getClassLoader()
                    .getResourceAsStream( "static/B_1_7_ALL.txt" );


            InputStream inputOilAnswers = obj
                    .getClass()
                    .getClassLoader()
                    .getResourceAsStream( "static/B_1_7_ANSWERS.txt" );

            //-----------------------------------------------------------
            //-----------------------For boiler--------------------------

            InputStream inputBoilerQuestions = obj
                    .getClass()
                    .getClassLoader()
                    .getResourceAsStream( "static/B_8_1_ALL.txt" );

            InputStream inputBoilerAnswers = obj
                    .getClass()
                    .getClassLoader()
                    .getResourceAsStream( "static/B_8_1_ANSWER.txt" );

            //----------------Init Industrial database-------------------

            String[] temp = getArray( inputIndustrialQuestions );
            String[] arrayQuestion = Arrays.copyOfRange( temp, 1, temp.length );

            for (String s : arrayQuestion) {
                String[] split = s.split( "\\n" );
                int length = split.length;
                log.info( "Preload industrial question database " + indRepo
                        .save( new IndustrialQuestions( split[0],
                                Arrays.asList( Arrays.copyOfRange( split, 1, length ) ) ) ) );
            }

            temp = getArray( inputIndustAnswer );
            arrayQuestion = Arrays.copyOfRange( temp, 1, temp.length );
            for (String s : arrayQuestion) {
                String[] split = s.split( "\\n" );
                log.info( "Preload answer for industrial" + correctAnswerIndustRepository
                        .save( new CorrectIndustAnswer( split[0], Arrays
                                .asList( Arrays.
                                        copyOfRange( split, 1, split.length ) ) ) ) );
            }

            //----------------------------------------------------------------------
            //--------------------Init Oil database---------------------------------

            temp = getArray( inputOilQuestions );
            arrayQuestion = Arrays.copyOfRange( temp, 1, temp.length );
            for (String s : arrayQuestion) {
                String[] split = s.split( "\\n" );
                int length = split.length;
                log.info( "Preload oil question database " + oilAllQuestRepo
                        .save( new OilQuestions( split[0],
                                Arrays.asList( Arrays.copyOfRange( split, 1, length ) ) ) ) );
            }

            temp = getArray( inputOilAnswers );
            arrayQuestion = Arrays.copyOfRange( temp, 1, temp.length );
            for (String s : arrayQuestion) {
                String[] split = s.split( "\\n" );
                log.info( "Preload answer for oil" + answerOilRepo
                        .save( new CorrectOilAnswer( split[0], Arrays
                                .asList( Arrays.
                                        copyOfRange( split, 1, split.length ) ) ) ) );
            }

            //---------------------------------------------------------------------------
            //--------------------------Init database boiler------------------------------


            temp = getArray( inputBoilerQuestions );
            arrayQuestion = Arrays.copyOfRange( temp, 1, temp.length );
            for (String s : arrayQuestion) {
                String[] split = s.split( "\\n" );
                int length = split.length;
                log.info( "Preload boiler question database " + boilerRepository
                        .save( new BoilerQuestions( split[0],
                                Arrays.asList( Arrays.copyOfRange( split, 1, length ) ) ) ) );
            }

            temp = getArray( inputBoilerAnswers );
            arrayQuestion = Arrays.copyOfRange( temp, 1, temp.length );
            for (String s : arrayQuestion) {
                String[] split = s.split( "\\n" );
                log.info( "Preload answer for boiler" + correctAnswerBoilerRepo
                        .save( new ConcreteBoilerAnswer( split[0], Arrays
                                .asList( Arrays.
                                        copyOfRange( split, 1, split.length ) ) ) ) );
            }

        };
    }

    private String[] getArray(InputStream inputStream) throws IOException {
        StringBuilder content;
        try (BufferedReader br = new BufferedReader( new InputStreamReader( inputStream ) )) {
            content = new StringBuilder();

            String st;

            while ((st = br.readLine()) != null) {
                content.append( st )
                        .append( "\n" );
            }
        }
        return content.toString()
                .split( "(\\d+\\.\\s)|(Вопрос \\d+)" );
    }
}
