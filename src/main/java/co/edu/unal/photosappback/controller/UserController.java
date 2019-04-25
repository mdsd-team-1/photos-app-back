package co.edu.unal.photosappback.controller;

import co.edu.unal.photosappback.model.Album;
import co.edu.unal.photosappback.model.User;
import co.edu.unal.photosappback.repository.AlbumRepository;
import co.edu.unal.photosappback.repository.UserRespository;
import co.edu.unal.photosappback.specification.AlbumSpecification;
import co.edu.unal.photosappback.specification.criteria.SearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    UserRespository userRepository;
    
    @Autowired
    AlbumRepository albumRepository;
    
    @GetMapping("/user")
    public User getUser(int id) {
    	return userRepository.getOne(id);
    }
    
    @GetMapping("/user/albums")
    public List<Album> getAlbums(int id) {
    	AlbumSpecification albumsPerUser = new AlbumSpecification(new SearchCriteria("user_id", ":", id));
    	List<Album> results = albumRepository.findAll(albumsPerUser);
    	return results;
    }

    @PostMapping("/user/create")
    public User createUser(@RequestBody Map<String, String> body) {
        
    	// Read all parameters
    	String userName = body.get("user_name");
        String firstName = body.get("first_name");
        String lastName = body.get("last_name");
        String profileDescription = body.get("profile_description");
        String password = body.get("password");
        String email = body.get("email");
        
        // Perform validations on them
        // TODO: Implement this...
        
        
        User newUser = new User(userName, firstName, lastName, profileDescription, password, email);
        return userRepository.save(newUser);
    }
}