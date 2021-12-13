package ru.anton.repository.boiler;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.anton.entity.boiler.ConcreteBoilerAnswer;

public interface CorrectAnswerBoilerRepo extends JpaRepository<ConcreteBoilerAnswer, Long> {
    ConcreteBoilerAnswer findById(long id);
}
