package com.example.hellospring;

import com.example.hellospring.model.User;
import com.example.hellospring.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class HelloController {

    @Autowired
    private UserRepo userRepo;


    @GetMapping("/hello")
    public String hello(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Map<String, Object> model) {
        model.put("name", name);
        return "hello";
    }

    @GetMapping
    public String main(Map<String, Object> model) {
        Iterable<User> users = userRepo.findAll();

        model.put("users", users);

        return "main";
    }

    @GetMapping("/add")
    public String add(Map<String, Object> model) {
        Iterable<User> users = userRepo.findAll();

        model.put("users", users);

        return "add";
    }


    @PostMapping
    public String add(@RequestParam String firstName, @RequestParam String secondName, Map<String, Object> model) {
        User user = new User(firstName, secondName);

        userRepo.save(user);

        Iterable<User> users = userRepo.findAll();

        model.put("users", users);

        return "add";
    }

    @PostMapping("filter")
    public String filter(@RequestParam String filter, Map<String, Object> model) {
        Iterable<User> users;

        if (filter != null && !filter.isEmpty()) {
            users = userRepo.findBySecondName(filter);
        } else {
            users = userRepo.findAll();
        }

        model.put("users", users);

        return "main";
    }


}
