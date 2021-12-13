package ru.anton.entity.boiler;

import lombok.*;
import ru.anton.entity.AbstractIdentifiableObject;

import javax.persistence.*;
import java.util.List;

@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "correct_boiler_answer")
public class ConcreteBoilerAnswer extends AbstractIdentifiableObject {
    @Getter
    @Setter
    @NonNull
    @Column(length = 4000)
    private String nameAnswer;

    @Getter
    @Setter
    @NonNull
    @Column(length = 4000)
    @ElementCollection(fetch = FetchType.LAZY)
    private List<String> correctAnswer;
}
