package app.Controller;

import app.Model.User;
import app.Repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class RegistrationController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/registration")
    public String loadRegistrationPage() {
        return "registration";
    }

    @PostMapping("/registration")
    public String doRegistration(@RequestParam Map<String, String> form, Model model) {
        User newUser = new User();
        if (Utils.validate(form)) {
            newUser.setUserName(form.get("userName"));
            newUser.setPassword(form.get("password"));
            newUser.setName(form.get("firstName"));
            newUser.setLastName(form.get("lastName"));
        }
        userRepository.save(newUser);
        model.addAttribute("message", "Пользователь " + form.get("userName") + " зарегистрирован!");
        return "redirect:/";
    }
}
