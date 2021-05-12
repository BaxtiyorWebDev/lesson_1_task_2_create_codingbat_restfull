package uz.pdp.online.lesson_1_task_2_create_codingbat_restfull.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.online.lesson_1_task_2_create_codingbat_restfull.entity.Answer;

public interface AnswerRepos extends JpaRepository<Answer, Integer> {

}
