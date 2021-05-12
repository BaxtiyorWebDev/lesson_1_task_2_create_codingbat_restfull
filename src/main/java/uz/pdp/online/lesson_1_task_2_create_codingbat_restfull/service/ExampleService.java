package uz.pdp.online.lesson_1_task_2_create_codingbat_restfull.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.online.lesson_1_task_2_create_codingbat_restfull.entity.Example;
import uz.pdp.online.lesson_1_task_2_create_codingbat_restfull.entity.Task;
import uz.pdp.online.lesson_1_task_2_create_codingbat_restfull.payload.ApiResponse;
import uz.pdp.online.lesson_1_task_2_create_codingbat_restfull.payload.ExampleDto;
import uz.pdp.online.lesson_1_task_2_create_codingbat_restfull.repository.ExampleRepos;
import uz.pdp.online.lesson_1_task_2_create_codingbat_restfull.repository.TaskRepos;

import java.util.List;
import java.util.Optional;

@Service
public class ExampleService {
    @Autowired
    ExampleRepos exampleRepos;
    @Autowired
    TaskRepos taskRepos;


    public ApiResponse addExample(ExampleDto exampleDto) {
        boolean existsByTaskId = exampleRepos.existsByTaskId(exampleDto.getTaskId());
        if (existsByTaskId)
            return new ApiResponse("Bunday na'muna mavjud",false);
        Optional<Task> optionalTask = taskRepos.findById(exampleDto.getTaskId());
        if (!optionalTask.isPresent())
            return new ApiResponse("Bunday vazifa topilmadi",false);
        Example example = new Example();
        example.setText(exampleDto.getText());
        example.setTask(optionalTask.get());
        exampleRepos.save(example);
        return new ApiResponse("Ma'lumot saqlandi",true);
    }

    public List<Example> getExamples() {
        List<Example> examplesList = exampleRepos.findAll();
        return examplesList;
    }

    public Example getExampleById(Integer id) {
        Optional<Example> optionalExample = exampleRepos.findById(id);
        return optionalExample.orElse(null);
    }

    public ApiResponse editExample(Integer id, ExampleDto exampleDto) {
        boolean exists = exampleRepos.existsByTaskIdAndIdNot(exampleDto.getTaskId(), id);
        if (exists)
            return new ApiResponse("Bunday na'muna mavjud",false);
        Optional<Example> optionalExample = exampleRepos.findById(id);
        if (!optionalExample.isPresent())
            return new ApiResponse("Bunday na'muna topilmadi",false);
        Optional<Task> optionalTask = taskRepos.findById(exampleDto.getTaskId());
        if (!optionalTask.isPresent())
            return new ApiResponse("Bunday vazifa topilmadi",false);
        Example editingExample = optionalExample.get();
        editingExample.setText(exampleDto.getText());
        editingExample.setTask(optionalTask.get());
        exampleRepos.save(editingExample);
        return new ApiResponse("Ma'lumot saqlandi",true);
    }

    public ApiResponse deleteExample(Integer id) {
        try {
            exampleRepos.deleteById(id);
            return new ApiResponse("Ma'lumot o'chirildi",true);
        } catch (Exception e) {
            return new ApiResponse("Xatolik",false);
        }
    }
}
