package uz.pdp.online.lesson_1_task_2_create_codingbat_restfull.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class LanguageDto {

    @NotNull(message = "til nomi bo'sh bo'lmasligi kerak")
    private String name;

}
