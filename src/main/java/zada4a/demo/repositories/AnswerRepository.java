package zada4a.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zada4a.demo.entities.Answer;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
    // Можно добавить кастомные методы, если необходимо
}
