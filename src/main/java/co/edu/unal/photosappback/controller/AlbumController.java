package co.edu.unal.photosappback.controller;

import co.edu.unal.photosappback.model.Album;
import co.edu.unal.photosappback.model.Photo;
import co.edu.unal.photosappback.repository.AlbumRepository;
import co.edu.unal.photosappback.repository.PhotoRepository;
import co.edu.unal.photosappback.specification.PhotoSpecification;
import co.edu.unal.photosappback.specification.criteria.SearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
public class AlbumController {

    @Autowired
    AlbumRepository albumRepository;
    
    @Autowired
    PhotoRepository photoRepository;
    
    @GetMapping("/album")
    public Album getAlbum(int id) {
    	return albumRepository.getOne(id);
    }
    
    @GetMapping("/album/photos")
    public List<Photo> getPhotos(int id) {
    	PhotoSpecification photosPerAlbum = new PhotoSpecification(new SearchCriteria("album_id", ":", id));
    	List<Photo> results = photoRepository.findAll(photosPerAlbum);
    	return results;
    }
    
    @PostMapping("/album/create")
    public Album createAlbum(@RequestBody Map<String, String> body) {
		
    	// Read all parameters
        String userIdString = body.get("user_id");
        String name = body.get("name");
     
        // Convert them into adequate format
        int userId = Integer.parseInt(userIdString);
        
        // Perform validations on them
        // TODO: Implement this...
        
     
        
        Album newAlbum = new Album(name, userId);
        return albumRepository.save(newAlbum);
    }
}