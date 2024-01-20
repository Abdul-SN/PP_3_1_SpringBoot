package preproject.PP_3_1_1_SpringBoot.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import preproject.PP_3_1_1_SpringBoot.model.User;
import preproject.PP_3_1_1_SpringBoot.service.UserService;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    //Получение списка ВСЕХ пользователей
    @GetMapping("/users")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.index());
        return "users";
    }

    //Создаем пустой объект пользователя, передаем модель user в post метод (create)
    @GetMapping("/users/add")
    public String addUser(@ModelAttribute("user") User user) {
        return "add";
    }

    //Получаем из html формы объект User и добавляем его в БД
    @PostMapping("/users")
    public String create(@ModelAttribute("user") User user) {
        userService.addUser(user);
        return "redirect:/users";
    }

    //Отправка формы для редактирования  пользователя
    @GetMapping("/users/update")
    public String editUser(@RequestParam(value = "id") int id, Model model) {
        model.addAttribute("user", userService.getById(id));
        return "update";
    }

    //Получение User`а с edit метода, изменение полей, и вывод
    @PostMapping("/users/update")
    public String updateUser(@RequestParam(value = "id") int id,
                             @ModelAttribute("user") User user) {
        user.setId(id);
        userService.updateUser(user);
        return "redirect:/users";
    }

    //Удаление пользователя по id
    @PostMapping("users/delete")
    public String deleteUser(@RequestParam(value = "id") int id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }

}
