package co.edu.unal.photosappback.controller;

import co.edu.unal.photosappback.model.Album;
import co.edu.unal.photosappback.model.Photo;
import co.edu.unal.photosappback.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
public class PhotoController {

	@Autowired
	PhotoRepository photoRepository;


	@RequestMapping(value = "/photo/id/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> getPhoto(@PathVariable Integer id) {

		Photo photo = photoRepository.getOne(id);

		if(photo == null){
			return new ResponseEntity<>("Photo not found", HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(photo, HttpStatus.OK);
	}


	// TODO: Not fully implemented yet...
	@PostMapping("/photo/upload")
	public Photo uploadPhoto(@RequestBody Map<String, String> body) {

		// Read all parameters
		String userIdString = body.get("user_id");
		String url = body.get("url");
		String name = body.get("name");

		// Convert them into adequate format
		int userId = Integer.parseInt(userIdString);

		// Perform validations on them
		// TODO: Implement this...



		// TODO: First we create a default Album for user, if it does not exist...
		Album newDefaultAlbum = new Album("Default", userId);

		Photo newPhoto = new Photo(name, url, newDefaultAlbum.getId());
		return photoRepository.save(newPhoto);
	}
}