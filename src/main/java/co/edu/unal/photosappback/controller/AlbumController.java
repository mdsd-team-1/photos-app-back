package co.edu.unal.photosappback.controller;

import co.edu.unal.photosappback.controller.exception.album.AlbumHasNoPhotosException;
import co.edu.unal.photosappback.controller.exception.album.AlbumNotCreatedException;
import co.edu.unal.photosappback.controller.exception.album.AlbumNotFoundException;
import co.edu.unal.photosappback.controller.exception.album.MissingParametersForNewAlbumException;
import co.edu.unal.photosappback.controller.exception.album.PhotosFromAlbumNotFoundException;
import co.edu.unal.photosappback.controller.exception.album.UserIdIsNotNumberException;
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

@CrossOrigin
@RestController
@RequestMapping("/album")
public class AlbumController {

	@Autowired
	AlbumRepository albumRepository;

	@Autowired
	PhotoRepository photoRepository;


	@RequestMapping(value = "/id/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> getAlbum(@PathVariable Long id) throws Exception {

		Album album = null;

		try {
			album = albumRepository.getOne(id.intValue());

		} catch(Exception e) {
			throw new AlbumNotFoundException();
		}

		if(album == null){
			throw new AlbumNotFoundException();
		}

		return new ResponseEntity<>(album, HttpStatus.OK);
	}


	@RequestMapping(value = "/{id}/photos", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> getPhotosFromAlbum(@PathVariable Long id) throws Exception {

		PhotoSpecification photosFromAlbumQuery = new PhotoSpecification(
				new SearchCriteria("albumId", ":", id));

		List<Photo> photos = photoRepository.findAll(photosFromAlbumQuery);

		if(photos == null){
			throw new PhotosFromAlbumNotFoundException();
		}

		if(photos.size() == 0){
			throw new AlbumHasNoPhotosException();
		}

		return new ResponseEntity<>(photos, HttpStatus.OK);
	}


	@PostMapping("/create")
	public ResponseEntity<?> createAlbum(@RequestBody Map<String, String> body) throws Exception {

		String userIdString = body.get("user_id");
		String name = body.get("name");

		if(userIdString == null || name == null) {
			throw new MissingParametersForNewAlbumException();
		}

		int userId = -1;

		try {
			userId = Integer.parseInt(userIdString);

		} catch(NumberFormatException exception) {
			throw new UserIdIsNotNumberException();
		}

		Album newAlbum = albumRepository.save(
				new Album(name, userId));

		if(newAlbum == null) {
			throw new AlbumNotCreatedException();
		}

		return new ResponseEntity<>(newAlbum, HttpStatus.CREATED);
	}


	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleException(Exception exception) {

		if(exception instanceof AlbumNotFoundException) {
			return new ResponseEntity<>("Album not found", HttpStatus.NOT_FOUND);

		} else if(exception instanceof PhotosFromAlbumNotFoundException) {
			return new ResponseEntity<>("Photos from album not found", HttpStatus.NOT_FOUND);

		} else if(exception instanceof AlbumHasNoPhotosException) {
			return new ResponseEntity<>("Album has no photos", HttpStatus.NOT_FOUND);

		} else if(exception instanceof MissingParametersForNewAlbumException) {
			return new ResponseEntity<>("Missing parameters for new album", HttpStatus.BAD_REQUEST);

		} else if(exception instanceof UserIdIsNotNumberException) {
			return new ResponseEntity<>("User Id is not a number", HttpStatus.BAD_REQUEST);

		} else if(exception instanceof AlbumNotCreatedException) {
			return new ResponseEntity<>("Album not created", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>("Error", HttpStatus.NOT_FOUND);
	}
}