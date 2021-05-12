package uz.pdp.online.lesson_1_task_2_create_codingbat_restfull.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.online.lesson_1_task_2_create_codingbat_restfull.entity.Task;

public interface TaskRepos extends JpaRepository<Task,Integer> {

    boolean existsByNameAndLanguageId(String name, Integer language_id);

    boolean existsByNameAndLanguageIdAndIdNot(String name, Integer language_id, Integer id);
}
