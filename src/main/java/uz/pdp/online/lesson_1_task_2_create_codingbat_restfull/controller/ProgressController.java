package uz.pdp.online.lesson_1_task_2_create_codingbat_restfull.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.online.lesson_1_task_2_create_codingbat_restfull.entity.Progress;
import uz.pdp.online.lesson_1_task_2_create_codingbat_restfull.payload.ApiResponse;
import uz.pdp.online.lesson_1_task_2_create_codingbat_restfull.payload.ProgressDto;
import uz.pdp.online.lesson_1_task_2_create_codingbat_restfull.service.ProgressService;

import java.util.List;

@RestController
@RequestMapping("/api/progress")
public class ProgressController {
    @Autowired
    ProgressService progressService;

    @PostMapping
    public HttpEntity<?> addProgress(ProgressDto progressDto) {
        ApiResponse apiResponse = progressService.addProgress(progressDto);
        return ResponseEntity.status(apiResponse!=null?202:409).body(apiResponse);
    }

    @GetMapping
    public ResponseEntity<List<Progress>> getProgressList() {
        List<Progress> progress = progressService.getProgressList();
        return ResponseEntity.ok(progress);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Progress> getProgress(@PathVariable Integer id) {
        Progress progress = progressService.getProgress(id);
        return ResponseEntity.status(progress!=null?202:409).body(progress);
    }

}
