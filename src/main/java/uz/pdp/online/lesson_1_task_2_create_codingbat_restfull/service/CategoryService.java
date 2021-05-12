package uz.pdp.online.lesson_1_task_2_create_codingbat_restfull.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.online.lesson_1_task_2_create_codingbat_restfull.entity.Category;
import uz.pdp.online.lesson_1_task_2_create_codingbat_restfull.payload.ApiResponse;
import uz.pdp.online.lesson_1_task_2_create_codingbat_restfull.payload.CategoryDto;
import uz.pdp.online.lesson_1_task_2_create_codingbat_restfull.repository.CategoryRepos;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    CategoryRepos categoryRepos;

    public ApiResponse addCategory(CategoryDto categoryDto) {
        boolean existsByName = categoryRepos.existsByName(categoryDto.getName());
        if (existsByName)
            return new ApiResponse("Bunday kategoriya mavjud",false);
        Category category = new Category();
        category.setName(categoryDto.getName());
        categoryRepos.save(category);
        return new ApiResponse("Ma'lumot saqlandi",true);
    }

    public List<Category> getCategories() {
        List<Category> categoryList = categoryRepos.findAll();
        return categoryList;
    }

    public Category getCategoryById(Integer id) {
        Optional<Category> optionalCategory = categoryRepos.findById(id);
        return optionalCategory.orElse(null);
    }

    public ApiResponse editCategory(Integer id, CategoryDto categoryDto) {
        boolean exists = categoryRepos.existsByNameAndIdNot(categoryDto.getName(), id);
        if (exists)
            return new ApiResponse("Bunday kategoriya mavjud",false);
        Optional<Category> optionalCategory = categoryRepos.findById(id);
        Category editingCategory = optionalCategory.get();
        editingCategory.setName(categoryDto.getName());
        categoryRepos.save(editingCategory);
        return new ApiResponse("Ma'lumot saqlandi",true);
    }

    public ApiResponse deleteCategory(Integer id) {
        try {
            categoryRepos.deleteById(id);
            return new ApiResponse("Ma'lumot o'chirildi",true);
        } catch (Exception e) {
            return new ApiResponse("Xatolik",false);
        }
    }
}
