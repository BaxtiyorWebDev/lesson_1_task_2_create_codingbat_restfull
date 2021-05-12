package uz.pdp.online.lesson_1_task_2_create_codingbat_restfull.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String text;

    @OneToOne(optional = false)
    private Task task;

    @ManyToOne
    private User user;

    private boolean isCorrect;

    private Timestamp timestamp;
}
