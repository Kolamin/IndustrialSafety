package ru.anton.repository.oil;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.anton.entity.oil.CorrectOilAnswer;

import java.util.List;
import java.util.Optional;

public interface CorrectAnswerOilRepo extends JpaRepository<CorrectOilAnswer, Long> {

    Optional<CorrectOilAnswer> findById(Long id);
}
