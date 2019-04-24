package co.edu.unal.photosappback.controller;

import co.edu.unal.photosappback.model.Album;
import co.edu.unal.photosappback.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
public class AlbumController {

    @Autowired
    AlbumRepository albumRepository;
    
    @GetMapping("/album")
    public Album getAlbum(int id) {
    	return albumRepository.getOne(id);
    }
    
    @PostMapping("/album/create")
    public Album createAlbum(@RequestBody Map<String, String> body) {
		
        String name = body.get("name");
        String userIdString = body.get("user_id");
        
        int userId = Integer.parseInt(userIdString);
        
        Album newAlbum = new Album(name, userId);
        
        return albumRepository.save(newAlbum);
    }
}