package uz.pdp.online.lesson_1_task_2_create_codingbat_restfull.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.online.lesson_1_task_2_create_codingbat_restfull.entity.Example;

public interface ExampleRepos extends JpaRepository<Example,Integer> {

    boolean existsByTaskId(Integer task_id);

    boolean existsByTaskIdAndIdNot(Integer task_id, Integer id);
}
