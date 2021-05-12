package uz.pdp.online.lesson_1_task_2_create_codingbat_restfull.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AnswerDto {

    @NotNull
    private String text;

    @NotNull
    private Integer taskId;

    @NotNull
    private Integer userId;

    @NotNull
    private boolean isCorrect;
}
