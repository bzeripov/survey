package zada4a.demo.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

// Answer.java
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Answer extends BaseEntity {
    @Column(nullable = false)
    private String text;

    @Column(nullable = false)
    private boolean correct;

    @ManyToOne
    private Question question;
}

