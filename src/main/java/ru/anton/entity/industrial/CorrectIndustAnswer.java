package ru.anton.entity.industrial;

import lombok.*;
import ru.anton.entity.AbstractIdentifiableObject;

import javax.persistence.*;
import java.util.List;

@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "correct_indust_answer")
public class CorrectIndustAnswer extends AbstractIdentifiableObject {
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
