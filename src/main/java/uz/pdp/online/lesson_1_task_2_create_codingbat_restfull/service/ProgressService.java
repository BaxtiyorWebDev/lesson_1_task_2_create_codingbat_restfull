package uz.pdp.online.lesson_1_task_2_create_codingbat_restfull.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.online.lesson_1_task_2_create_codingbat_restfull.entity.Answer;
import uz.pdp.online.lesson_1_task_2_create_codingbat_restfull.entity.Progress;
import uz.pdp.online.lesson_1_task_2_create_codingbat_restfull.payload.ApiResponse;
import uz.pdp.online.lesson_1_task_2_create_codingbat_restfull.payload.ProgressDto;
import uz.pdp.online.lesson_1_task_2_create_codingbat_restfull.repository.AnswerRepos;
import uz.pdp.online.lesson_1_task_2_create_codingbat_restfull.repository.ProgressRepos;

import java.util.List;
import java.util.Optional;

@Service
public class ProgressService {
    @Autowired
    ProgressRepos progressRepos;
    @Autowired
    AnswerRepos answerRepos;

    public ApiResponse addProgress(ProgressDto progressDto) {
        List<Answer> allById = answerRepos.findAllById(progressDto.getAnswerId());
        Progress progress = new Progress();
        progress.setAnswer(allById);
        progressRepos.save(progress);
        return new ApiResponse("Ma'lumot saqlandi",true);
    }

    public List<Progress> getProgressList() {
        List<Progress> progressList = progressRepos.findAll();
        return progressList;
    }

    public Progress getProgress(Integer id) {
        Optional<Progress> optionalProgress = progressRepos.findById(id);
        return optionalProgress.orElse(null);
    }
}
