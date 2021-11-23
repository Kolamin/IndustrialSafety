package ru.anton.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.anton.entity.industrial.CorrectIndustAnswer;

public interface CorrectAnswerIndustRepository extends JpaRepository<CorrectIndustAnswer, Long> {
    CorrectIndustAnswer findById(long id);
}
