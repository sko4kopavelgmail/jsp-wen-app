package app.Controller;

import app.Model.Dream;
import app.Model.Node;
import app.Model.User;
import app.Repo.DreamRepository;
import app.Repo.NodeRepository;
import app.Repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
public class GreetingController {

    private User user;

    private Dream dream;

    private Node node;

    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DreamRepository dreamRepository;

    @Autowired
    private NodeRepository nodeRepository;

    /*Страница с приветствием и логином*/
    @GetMapping("/")
    public String loadGreeting(Model model) {
        return "greeting";
    }

    @PostMapping("/")
    public String login(
            @RequestParam Map<String, String> map,
            Model model
    ) {
        user = userRepository.findByUserName(map.get("userName"));
        if (user == null)
            model.addAttribute("message", "Даннаго пользователя в базе нет!");
        else if (user.getPassword().equals(map.get("password")))
            return "redirect:/main";
        else {
            user = null;
        }
        return "greeting";
    }

    @GetMapping("/main")
    public String loadMain(Model model) {
        user.setCount(user.getCount() + 1);
        userRepository.save(user);
        loadModel(model);
        return "main";
    }

    @PostMapping("/main")
    public String updateUser(
            Model model,
            @RequestParam Map<String, String> map
    ) {
        if (!map.get("name").isEmpty())
            user.setName(map.get("name"));
        if (!map.get("password").isEmpty())
            user.setPassword(map.get("password"));
        if (!map.get("userName").isEmpty() && userRepository.findByUserName(map.get("userName")) == null)
            user.setUserName(map.get("userName"));
        if (!map.get("lastName").isEmpty())
            user.setLastName(map.get("lastName"));
        userRepository.save(user);
        loadModel(model);
        return "main";
    }

    @PostMapping("/addDream")
    public String addDream(
            @RequestParam("file") MultipartFile file,
            @RequestParam Map<String, String> map,
            Model model
    ) throws IOException {
        dream = new Dream();
        if (Utils.validate(map)) {
            dream.setDescription(map.get("description"));
            dream.setStartYear(Integer.parseInt(map.get("Year")));
            if (map.size() > 2)
                if (map.get("isDone").equals("on")) {
                    dream.setDone(true);
                } else
                    dream.setDone(false);
        }
        try {//загрузка файла
            if (file != null) {
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) {
                    uploadDir.mkdir();
                }
                String fileUUID = UUID.randomUUID().toString(); //задание уникального имени
                fileUUID += "." + file.getOriginalFilename();
                file.transferTo(new File(uploadPath + "/" + fileUUID));
                dream.setFilename(fileUUID);
            }

        }catch (Exception ex){
            model.addAttribute("message",ex.toString());
        }finally {
            dreamRepository.save(dream);
            node = new Node(user, dream);
            nodeRepository.save(node);
            loadModel(model);
        }
        return "main";
    }

    private void loadModel(Model model) {
        List<Node> list = nodeRepository.findAllByUser(user);
        model.addAttribute("nodes", list);
        model.addAttribute("time", new Date().toString());
        model.addAttribute("user", user);
    }
}