package com.pluralsight.conference.controller;

import com.pluralsight.conference.model.User;
import com.pluralsight.conference.model.UserRecord;
import com.pluralsight.conference.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {
    private UserService userService;
   @Autowired
   public UserController(UserService userService){
       this.userService = userService;
   }

//        @GetMapping("/user")
//    public User getUser(@RequestParam(value="firstname", defaultValue="Alvron") String firstName,
//                        @RequestParam(value = "lastname", defaultValue="Kato") String lastName,
//                        @RequestParam(value = "age", defaultValue= "6") int age){
//        User user = new User();
//        user.setFirstName(firstName);
//        user.setLastName(lastName);
//        user.setAge(age);
//        return user;
//    }
// Using record which works like a class
    @GetMapping("/user")
    public List<UserRecord> getUser() {
        List<UserRecord> users = new ArrayList<>();
        users.add(new UserRecord("Grace", "Mwemeke", 60));
        return users;
    }
    @PostMapping("/user")
    public User addUser(@RequestBody User user) {
        System.out.println("FirstName:" + user.getFirstName() + " LastName:" + user.getLastName() + " Age:" + user.getAge());
        userService.save(user);
        return user;
    }

    // Using text blocks
    @GetMapping("/user/{firstname}")
    public String get(@PathVariable String firstname){
        String response = """
                {
                "user" : {
                 "firstName":"William"
                 "lastName": "Mwemeke"
                 "age": 62
                }
                }
                """;
        return response;
    }
}
