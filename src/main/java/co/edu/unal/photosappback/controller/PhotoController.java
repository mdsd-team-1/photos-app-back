package co.edu.unal.photosappback.controller;

import co.edu.unal.photosappback.model.Photo;
import co.edu.unal.photosappback.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
public class PhotoController {

    @Autowired
    PhotoRepository photoRepository;
    
    @GetMapping("/photo")
    public Photo getPhoto(int id) {
    	return photoRepository.getOne(id);
    }
    
    @PostMapping("/photo/upload")
    public Photo uploadPhoto(@RequestBody Map<String, String> body) {
        
    	String url = body.get("url");
        String name = body.get("name");
        String albumIdString = body.get("album_id");
        
        int albumId = Integer.parseInt(albumIdString);
        
        Photo newPhoto = new Photo(name, url, albumId);
        
        return photoRepository.save(newPhoto);
    }
}