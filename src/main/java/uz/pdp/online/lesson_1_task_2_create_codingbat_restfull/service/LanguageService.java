package uz.pdp.online.lesson_1_task_2_create_codingbat_restfull.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.online.lesson_1_task_2_create_codingbat_restfull.entity.Language;
import uz.pdp.online.lesson_1_task_2_create_codingbat_restfull.payload.ApiResponse;
import uz.pdp.online.lesson_1_task_2_create_codingbat_restfull.payload.LanguageDto;
import uz.pdp.online.lesson_1_task_2_create_codingbat_restfull.repository.LanguageRepos;

import java.util.List;
import java.util.Optional;

@Service
public class LanguageService {
    @Autowired
    LanguageRepos languageRepos;

    public ApiResponse addLanguage(LanguageDto languageDto) {
        boolean existsByName = languageRepos.existsByName(languageDto.getName());
        if (existsByName)
            return new ApiResponse("Bunday til mavjud",false);
        Language language = new Language();
        language.setName(languageDto.getName());
        languageRepos.save(language);
        return new ApiResponse("Ma'lumot saqlandi",true);
    }
    
    public List<Language> getLanguages() {
        List<Language> languageList = languageRepos.findAll();
        return languageList;
    }
    
    public Language getLanguageById(Integer id) {
        Optional<Language> optionalLanguage = languageRepos.findById(id);
        return optionalLanguage.orElse(null);
    }
    
    public ApiResponse editLanguage(Integer id, LanguageDto languageDto) {
        boolean exists = languageRepos.existsByNameAndIdNot(languageDto.getName(), id);
        if (exists)
            return new ApiResponse("Bunday til mavjud",false);
        Optional<Language> optionalLanguage = languageRepos.findById(id);
        Language editingLanguage = optionalLanguage.get();
        editingLanguage.setName(languageDto.getName());
        languageRepos.save(editingLanguage);
        return new ApiResponse("Ma'lumot saqlandi",true);
    }

    public ApiResponse deleteLanguage(Integer id) {
        try {
            languageRepos.deleteById(id);
            return new ApiResponse("O'chirildi",true);
        } catch (Exception e) {
            return new ApiResponse("Xatolik",false);
        }
    }
}
