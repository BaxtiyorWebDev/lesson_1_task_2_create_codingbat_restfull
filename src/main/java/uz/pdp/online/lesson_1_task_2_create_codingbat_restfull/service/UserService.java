package uz.pdp.online.lesson_1_task_2_create_codingbat_restfull.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.online.lesson_1_task_2_create_codingbat_restfull.entity.User;
import uz.pdp.online.lesson_1_task_2_create_codingbat_restfull.payload.ApiResponse;
import uz.pdp.online.lesson_1_task_2_create_codingbat_restfull.payload.UserDto;
import uz.pdp.online.lesson_1_task_2_create_codingbat_restfull.repository.UserRepos;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepos userRepos;

    public ApiResponse addUser(UserDto userDto) {
        boolean existsByEmail = userRepos.existsByEmail(userDto.getEmail());
        if (existsByEmail)
            return new ApiResponse("Bunday user mavjud",false);
        User user = new User();
        user.setEmail(userDto.getEmail());
        userRepos.save(user);
        return new ApiResponse("Ma'lumot saqlandi",true);
    }

    public List<User> getUsers() {
        List<User> userList = userRepos.findAll();
        return userList;
    }

    public User getUserById(Integer id) {
        Optional<User> optionalUser = userRepos.findById(id);
        return optionalUser.orElse(null);
    }

    public ApiResponse editUser(Integer id, UserDto userDto) {
        boolean exists = userRepos.existsByEmailAndIdNot(userDto.getEmail(), id);
        if (exists)
            return new ApiResponse("Bunday user mavjud",false);
        Optional<User> optionalUser = userRepos.findById(id);
        User editingUser = optionalUser.get();
        editingUser.setEmail(userDto.getEmail());
        userRepos.save(editingUser);
        return new ApiResponse("Ma'lumot saqlandi",true);
    }

    public ApiResponse deleteUser(Integer id) {
        try {
            userRepos.deleteById(id);
            return new ApiResponse("O'chirildi",true);
        } catch (Exception e) {
            return new ApiResponse("Xatolik",false);
        }
    }
}
