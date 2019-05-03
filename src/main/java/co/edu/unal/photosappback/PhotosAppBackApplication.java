package co.edu.unal.photosappback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PhotosAppBackApplication {
	
	public static double version = 1.0;

	public static void main(String[] args) {
		SpringApplication.run(PhotosAppBackApplication.class, args);
	}
}