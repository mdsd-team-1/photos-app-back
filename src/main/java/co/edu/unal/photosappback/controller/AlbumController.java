package co.edu.unal.photosappback.controller;

import co.edu.unal.photosappback.model.Album;
import co.edu.unal.photosappback.model.Photo;
import co.edu.unal.photosappback.repository.AlbumRepository;
import co.edu.unal.photosappback.repository.PhotoRepository;
import co.edu.unal.photosappback.specification.PhotoSpecification;
import co.edu.unal.photosappback.specification.criteria.SearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
public class AlbumController {

	@Autowired
	AlbumRepository albumRepository;

	@Autowired
	PhotoRepository photoRepository;


	@RequestMapping(value = "/album/id/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> getAlbum(@PathVariable Long id) {

		Album album = albumRepository.getOne(id.intValue());

		if(album == null){
			return new ResponseEntity<>("Album not found", HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(album, HttpStatus.OK);
	}


	@RequestMapping(value = "/album/{id}/photos", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> getPhotosFromAlbum(@PathVariable Long id) {

		PhotoSpecification photosFromAlbumQuery = new PhotoSpecification(
				new SearchCriteria("albumId", ":", id));

		List<Photo> photos = photoRepository.findAll(photosFromAlbumQuery);

		if(photos == null){
			return new ResponseEntity<>("Photos from album not found", HttpStatus.NOT_FOUND);
		}

		if(photos.size() == 0){
			return new ResponseEntity<>("Album has no photos", HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(photos, HttpStatus.OK);
	}


	@PostMapping("/album/create")
	public ResponseEntity<?> createAlbum(@RequestBody Map<String, String> body) {

		String userIdString = body.get("user_id");
		String name = body.get("name");

		if(userIdString == null || name == null) {
			return new ResponseEntity<>("Missing parameters", HttpStatus.BAD_REQUEST);
		}

		int userId = 0;
		try {
			userId = Integer.parseInt(userIdString);
		}
		catch(NumberFormatException eception) {
			return new ResponseEntity<>("UserID is not a valid number", HttpStatus.BAD_REQUEST);
		}


		Album newAlbum = albumRepository.save(
				new Album(name, userId));

		if(newAlbum == null) {
			return new ResponseEntity<>("Album not created", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>(newAlbum, HttpStatus.ACCEPTED);
	}
}