package uz.pdp.online.lesson_1_task_2_create_codingbat_restfull.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.online.lesson_1_task_2_create_codingbat_restfull.entity.Answer;
import uz.pdp.online.lesson_1_task_2_create_codingbat_restfull.entity.Task;
import uz.pdp.online.lesson_1_task_2_create_codingbat_restfull.entity.User;
import uz.pdp.online.lesson_1_task_2_create_codingbat_restfull.payload.AnswerDto;
import uz.pdp.online.lesson_1_task_2_create_codingbat_restfull.payload.ApiResponse;
import uz.pdp.online.lesson_1_task_2_create_codingbat_restfull.repository.AnswerRepos;
import uz.pdp.online.lesson_1_task_2_create_codingbat_restfull.repository.TaskRepos;
import uz.pdp.online.lesson_1_task_2_create_codingbat_restfull.repository.UserRepos;

import java.util.List;
import java.util.Optional;

@Service
public class AnswerService {
    @Autowired
    AnswerRepos answerRepos;
    @Autowired
    TaskRepos taskRepos;
    @Autowired
    UserRepos userRepos;

    public ApiResponse addAnswer(AnswerDto answerDto) {
        Optional<Task> optionalTask = taskRepos.findById(answerDto.getTaskId());
        Optional<User> optionalUser = userRepos.findById(answerDto.getUserId());
        if (!optionalTask.isPresent())
            return new ApiResponse("Bunday vazifa topilmadi", false);
        if (!optionalTask.isPresent())
            return new ApiResponse("Bunday user topilmadi", false);
        Answer answer = new Answer();
        answer.setText(answerDto.getText());
        answer.setTask(optionalTask.get());
        answer.setUser(optionalUser.get());
        answerRepos.save(answer);
        return new ApiResponse("Ma'lumot saqlandi", true);
    }

    public List<Answer> getAnswers() {
        List<Answer> answerList = answerRepos.findAll();
        return answerList;
    }

    public Answer getAnswerById(Integer id) {
        Optional<Answer> optionalAnswer = answerRepos.findById(id);
        return optionalAnswer.orElse(null);
    }
}
