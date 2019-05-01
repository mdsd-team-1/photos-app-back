package co.edu.unal.photosappback.controller;

import co.edu.unal.photosappback.controller.amazon.AmazonClient;
import co.edu.unal.photosappback.controller.exception.photo.PhotoNotCreatedException;
import co.edu.unal.photosappback.controller.exception.photo.PhotoNotDeletedException;
import co.edu.unal.photosappback.controller.exception.photo.PhotoNotFoundException;
import co.edu.unal.photosappback.controller.exception.photo.PhotoUploadErrorException;
import co.edu.unal.photosappback.model.Photo;
import co.edu.unal.photosappback.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class PhotoController {

	private AmazonClient amazonClient;

	@Autowired
	PhotoRepository photoRepository;

	@Autowired
	PhotoController(AmazonClient amazonClient) {
		this.amazonClient = amazonClient;
	}


	@RequestMapping(value = "/photo/id/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> getPhoto(@PathVariable Long id) throws Exception {

		Photo photo = null;

		try {
			photo = photoRepository.getOne(id.intValue());

		} catch(Exception e) {
			throw new PhotoNotFoundException();
		}

		if(photo == null){
			throw new PhotoNotFoundException();
		}

		return new ResponseEntity<>(photo, HttpStatus.OK);
	}


	@PostMapping("/photo/upload")
	public ResponseEntity<?> uploadPhoto(@RequestPart(value = "file") MultipartFile file) throws Exception {

		// TODO get data from Request
		int albumId = 0;
		String photoName = "Default";

		String photoUrl = null;

		try {
			photoUrl = this.amazonClient.uploadFile(file);

		} catch(Exception e) {
			throw new PhotoUploadErrorException();
		}

		Photo addedPhoto = null;

		try {
			addedPhoto = photoRepository.save(
					new Photo(photoName, photoUrl, albumId));

		} catch(Exception e) {
			throw new PhotoNotCreatedException();
		}

		if(addedPhoto == null) {
			throw new PhotoNotCreatedException();
		}

		return new ResponseEntity<>(addedPhoto, HttpStatus.ACCEPTED);
	}


	@PostMapping("/photo/delete")
	public String deleteFile(@RequestPart(value = "url") String fileUrl) throws Exception {

		try {
			return this.amazonClient.deleteFileFromS3Bucket(fileUrl);

		} catch(Exception e) {
			throw new PhotoNotDeletedException();
		}
	}


	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleException(Exception exception) {

		if(exception instanceof PhotoNotFoundException) {
			return new ResponseEntity<>("Photo not found", HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
	}
}