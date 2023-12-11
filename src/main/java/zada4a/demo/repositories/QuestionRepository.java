package zada4a.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zada4a.demo.entities.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
}
