package uz.pdp.online.lesson_1_task_2_create_codingbat_restfull.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CategoryDto {

    @NotNull(message = "kategoriya bo'sh bo'lmasligi kerak")
    private String name;

    @NotNull
    private String description;

    @NotNull
    private Integer languageId;
}
