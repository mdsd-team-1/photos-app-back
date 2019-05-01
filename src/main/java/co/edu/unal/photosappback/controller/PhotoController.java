package co.edu.unal.photosappback.controller;

import co.edu.unal.photosappback.controller.amazon.AmazonClient;
import co.edu.unal.photosappback.controller.exception.photo.PhotoNotFoundException;
import co.edu.unal.photosappback.model.Album;
import co.edu.unal.photosappback.model.Photo;
import co.edu.unal.photosappback.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/storage/")
public class PhotoController {
	
	private AmazonClient amazonClient;

	@Autowired
	PhotoRepository photoRepository;
	
    @Autowired
    PhotoController(AmazonClient amazonClient) {
        this.amazonClient = amazonClient;
    }
    
    @PostMapping("/uploadFile")
    public String uploadFile(@RequestPart(value = "file") MultipartFile file) {
    	// TODO: HERE!!!!
        return this.amazonClient.uploadFile(file);
    }
    
    @DeleteMapping("/deleteFile")
    public String deleteFile(@RequestPart(value = "url") String fileUrl) {
        return this.amazonClient.deleteFileFromS3Bucket(fileUrl);
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


	/*
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
	*/


	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleException(Exception exception) {

		if(exception instanceof PhotoNotFoundException) {
			return new ResponseEntity<>("Photo not found", HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
	}
}