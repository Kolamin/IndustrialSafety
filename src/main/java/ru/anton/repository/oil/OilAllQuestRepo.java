package ru.anton.repository.oil;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.anton.entity.oil.OilQuestions;

import java.util.Optional;

public interface OilAllQuestRepo extends JpaRepository<OilQuestions, Long> {
    Optional<OilQuestions> findById(Long id);
}
