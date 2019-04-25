package co.edu.unal.photosappback.controller;

import co.edu.unal.photosappback.model.Album;
import co.edu.unal.photosappback.model.User;
import co.edu.unal.photosappback.repository.AlbumRepository;
import co.edu.unal.photosappback.repository.UserRespository;
import co.edu.unal.photosappback.specification.AlbumSpecification;
import co.edu.unal.photosappback.specification.criteria.SearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {

	@Autowired
	UserRespository userRepository;

	@Autowired	
	AlbumRepository albumRepository;


	@RequestMapping(value = "/user/id/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> getUser(@PathVariable Integer id) {

		User user = userRepository.getOne(id);

		if(user == null){
			return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(user, HttpStatus.OK);
	}


	@RequestMapping(value = "/user/{id}/albums", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> getAlbumsFromUser(@PathVariable Integer id) {

		AlbumSpecification albumsFromUserQuery = new AlbumSpecification(
				new SearchCriteria("user_id", ":", id));

		List<Album> albums = albumRepository.findAll(albumsFromUserQuery);

		if(albums == null){
			return new ResponseEntity<>("Albums from user not found", HttpStatus.NOT_FOUND);
		}

		if(albums.size() == 0){
			return new ResponseEntity<>("User has no albums", HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(albums, HttpStatus.OK);
	}


	@PostMapping("/user/create")
	public ResponseEntity<?> createUser(@RequestBody Map<String, String> body) {

		String firstName = body.get("first_name");
		String lastName = body.get("last_name");
		String profileDescription = body.get("profile_description");
		String userName = body.get("user_name");
		String password = body.get("password");
		String email = body.get("email");

		if(firstName == null || lastName == null || profileDescription == null ||
				userName == null || password == null || email == null) {
			
			return new ResponseEntity<>("Missing parameters", HttpStatus.BAD_REQUEST);
		}

		User newUser = userRepository.save(
				new User(userName, firstName, lastName, profileDescription, password, email));

		if(newUser == null){
			return new ResponseEntity<>("User not created", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>(newUser, HttpStatus.ACCEPTED);
	}
}