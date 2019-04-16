package co.edu.unal.photosappback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    UserRespository userRespository;

    @PostMapping("/user/create")
    public User createUser(@RequestBody Map<String, String> body) {
        String userName = body.get("userName");
        String firstName = body.get("firstName");
        String lastName = body.get("lastName");
        String profileDescription = body.get("profileDescription");
        String pass = body.get("pass");
        String email = body.get("email");
        return userRespository.save(new User(userName, firstName, lastName, profileDescription, pass, email));
    }

}