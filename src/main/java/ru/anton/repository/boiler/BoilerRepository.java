package ru.anton.repository.boiler;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.anton.entity.boiler.BoilerQuestions;

public interface BoilerRepository extends JpaRepository<BoilerQuestions, Long> {
    BoilerQuestions findById(long id);
}
