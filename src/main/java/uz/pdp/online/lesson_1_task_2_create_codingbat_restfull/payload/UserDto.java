package uz.pdp.online.lesson_1_task_2_create_codingbat_restfull.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserDto {

    @NotNull
    private String email;

    @NotNull
    private String password;
}
