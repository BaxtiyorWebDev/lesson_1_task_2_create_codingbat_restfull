package uz.pdp.online.lesson_1_task_2_create_codingbat_restfull.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class TaskDto {

    @NotNull
    private String name;

    @NotNull
    private String text;

    @NotNull
    private String solution;

    @NotNull
    private String hint;

    @NotNull
    private String answer;

    @NotNull
    private String method;

    private boolean hasStart;

    @NotNull
    private Integer languageId;
}
