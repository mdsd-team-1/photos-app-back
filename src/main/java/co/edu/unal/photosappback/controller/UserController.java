package co.edu.unal.photosappback.controller;

import co.edu.unal.photosappback.model.User;
import co.edu.unal.photosappback.repository.UserRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    UserRespository userRepository;
    
    @GetMapping("/user")
    public User getUser(int id) {
    	return userRepository.getOne(id);
    }

    @PostMapping("/user/create")
    public User createUser(@RequestBody Map<String, String> body) {
        
    	String userName = body.get("user_name");
        String firstName = body.get("first_name");
        String lastName = body.get("last_name");
        String profileDescription = body.get("profile_description");
        String pass = body.get("password");
        String email = body.get("email");
        
        User newUser = new User(userName, firstName, lastName, profileDescription, pass, email);
        
        return userRepository.save(newUser);
    }
}