package uz.pdp.online.lesson_1_task_2_create_codingbat_restfull.payload;

import lombok.Data;

import java.util.List;

@Data
public class ProgressDto {
    private List<Integer> answerId;
}
