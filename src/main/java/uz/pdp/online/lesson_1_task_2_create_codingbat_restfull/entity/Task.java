package uz.pdp.online.lesson_1_task_2_create_codingbat_restfull.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String text;

    private String solution;

    private String hint;

    private String answer;

    private String method;

    private boolean hasStart;

    @ManyToOne(optional = false)
    private Language language;
}
