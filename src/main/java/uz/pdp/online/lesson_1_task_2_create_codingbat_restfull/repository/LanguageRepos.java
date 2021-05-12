package uz.pdp.online.lesson_1_task_2_create_codingbat_restfull.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.online.lesson_1_task_2_create_codingbat_restfull.entity.Language;

public interface LanguageRepos extends JpaRepository<Language,Integer> {

    boolean existsByName(String name);

    boolean existsByNameAndIdNot(String name, Integer id);
}
