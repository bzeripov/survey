package zada4a.demo.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;

// Question.java
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Question extends BaseEntity {
    @Column(nullable = false)
    private String title;

    @ManyToOne
    private Survey survey;

    @Column
    private String description;

//    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Answer> answers;

}

