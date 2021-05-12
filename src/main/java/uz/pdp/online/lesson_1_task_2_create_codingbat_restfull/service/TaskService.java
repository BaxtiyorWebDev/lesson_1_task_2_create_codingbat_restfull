package uz.pdp.online.lesson_1_task_2_create_codingbat_restfull.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.online.lesson_1_task_2_create_codingbat_restfull.entity.Language;
import uz.pdp.online.lesson_1_task_2_create_codingbat_restfull.entity.Task;
import uz.pdp.online.lesson_1_task_2_create_codingbat_restfull.payload.ApiResponse;
import uz.pdp.online.lesson_1_task_2_create_codingbat_restfull.payload.TaskDto;
import uz.pdp.online.lesson_1_task_2_create_codingbat_restfull.repository.LanguageRepos;
import uz.pdp.online.lesson_1_task_2_create_codingbat_restfull.repository.TaskRepos;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    TaskRepos taskRepos;
    @Autowired
    LanguageRepos languageRepos;

    public ApiResponse addTask(TaskDto taskDto) {
        boolean existsByNameAndLanguageId = taskRepos.existsByNameAndLanguageId(taskDto.getName(),taskDto.getLanguageId());
        if (existsByNameAndLanguageId)
            return new ApiResponse("Bunday task mavjud",false);
        Optional<Language> optionalLanguage = languageRepos.findById(taskDto.getLanguageId());
        if (!optionalLanguage.isPresent())
            return new ApiResponse("Bunday til topilmadi",false);

        Task task = new Task();
        task.setName(taskDto.getName());
        task.setText(taskDto.getText());
        task.setSolution(taskDto.getSolution());
        task.setHint(taskDto.getHint());
        task.setAnswer(taskDto.getAnswer());
        task.setMethod(taskDto.getMethod());
        task.setHasStart(taskDto.isHasStart());
        task.setLanguage(optionalLanguage.get());
        taskRepos.save(task);
        return new ApiResponse("Ma'lumot saqlandi",true);
    }

    public List<Task> getTasks() {
        List<Task> tasksList = taskRepos.findAll();
        return tasksList;
    }

    public Task getTaskById(Integer id) {
        Optional<Task> optionalTask = taskRepos.findById(id);
        return optionalTask.orElse(null);
    }

    public ApiResponse editTask(Integer id, TaskDto taskDto) {
        boolean existsByNameAndLanguageIdAndIdNot = taskRepos.existsByNameAndLanguageIdAndIdNot(taskDto.getName(),taskDto.getLanguageId(), id);
        if (existsByNameAndLanguageIdAndIdNot)
            return new ApiResponse("Bunday task mavjud",false);
        Optional<Language> optionalLanguage = languageRepos.findById(taskDto.getLanguageId());
        if (!optionalLanguage.isPresent())
            return new ApiResponse("Bunday til topilmadi",false);


        Optional<Task> optionalTask = taskRepos.findById(id);
        Task editingTask = optionalTask.get();
        editingTask.setName(taskDto.getName());
        editingTask.setText(taskDto.getText());
        editingTask.setSolution(taskDto.getSolution());
        editingTask.setHint(taskDto.getHint());
        editingTask.setAnswer(taskDto.getAnswer());
        editingTask.setMethod(taskDto.getMethod());
        editingTask.setHasStart(taskDto.isHasStart());
        editingTask.setLanguage(optionalLanguage.get());
        taskRepos.save(editingTask);
        return new ApiResponse("Ma'lumot saqlandi",true);
    }

    public ApiResponse deleteTask(Integer id) {
        try {
            taskRepos.deleteById(id);
            return new ApiResponse("O'chirildi",true);
        } catch (Exception e) {
            return new ApiResponse("Xatolik",false);
        }
    }
}
