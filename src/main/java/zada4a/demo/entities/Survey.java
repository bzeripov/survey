package zada4a.demo.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Survey extends BaseEntity{

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

//    @OneToMany(mappedBy = "survey", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Question> questions;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users author;
}
