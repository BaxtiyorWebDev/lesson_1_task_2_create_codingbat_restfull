package uz.pdp.online.lesson_1_task_2_create_codingbat_restfull.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.online.lesson_1_task_2_create_codingbat_restfull.entity.Task;

public interface TaskRepos extends JpaRepository<Task,Integer> {

    boolean existsByNameAndCategoryId(String name, Integer category_id);

    boolean existsByNameAndCategoryIdAndIdNot(String name, Integer category_id, Integer id);
}
