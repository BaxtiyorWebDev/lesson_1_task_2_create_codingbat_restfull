package uz.pdp.online.lesson_1_task_2_create_codingbat_restfull.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.online.lesson_1_task_2_create_codingbat_restfull.entity.Category;
import uz.pdp.online.lesson_1_task_2_create_codingbat_restfull.entity.Task;
import uz.pdp.online.lesson_1_task_2_create_codingbat_restfull.payload.ApiResponse;
import uz.pdp.online.lesson_1_task_2_create_codingbat_restfull.payload.TaskDto;
import uz.pdp.online.lesson_1_task_2_create_codingbat_restfull.repository.CategoryRepos;
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
    @Autowired
    CategoryRepos categoryRepos;

    public ApiResponse addTask(TaskDto taskDto) {
        boolean existsByNameAndCategoryId = taskRepos.existsByNameAndCategoryId(taskDto.getName(),taskDto.getCategoryId());
        if (existsByNameAndCategoryId)
            return new ApiResponse("Bunday task mavjud",false);
        Optional<Category> optionalCategory = categoryRepos.findById(taskDto.getCategoryId());
        if (!optionalCategory.isPresent())
            return new ApiResponse("Bunday kategoriya topilmadi",false);
        Task task = new Task();
        task.setName(taskDto.getName());
        task.setText(taskDto.getText());
        task.setSolution(taskDto.getSolution());
        task.setHint(taskDto.getHint());
        task.setAnswer(taskDto.getAnswer());
        task.setMethod(taskDto.getMethod());
        task.setHasStart(taskDto.isHasStart());
        task.setCategory(optionalCategory.get());
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
        boolean existsByNameAndCategoryIdAndIdNot = taskRepos.existsByNameAndCategoryIdAndIdNot(taskDto.getName(),taskDto.getCategoryId(), id);
        if (existsByNameAndCategoryIdAndIdNot)
            return new ApiResponse("Bunday task mavjud",false);
        Optional<Category> optionalCategory = categoryRepos.findById(taskDto.getCategoryId());
        if (!optionalCategory.isPresent())
            return new ApiResponse("Bunday kategoriya topilmadi",false);

        Optional<Task> optionalTask = taskRepos.findById(id);
        Task editingTask = optionalTask.get();
        editingTask.setName(taskDto.getName());
        editingTask.setText(taskDto.getText());
        editingTask.setSolution(taskDto.getSolution());
        editingTask.setHint(taskDto.getHint());
        editingTask.setAnswer(taskDto.getAnswer());
        editingTask.setMethod(taskDto.getMethod());
        editingTask.setHasStart(taskDto.isHasStart());
        editingTask.setCategory(optionalCategory.get());
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
