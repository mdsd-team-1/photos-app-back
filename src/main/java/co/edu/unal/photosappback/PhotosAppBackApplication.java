package co.edu.unal.photosappback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;

@SpringBootApplication
public class PhotosAppBackApplication {

	public static void main(String[] args) {
		
		// This will allow us to start the server from a bash script
		//SpringApplication application = new SpringApplication(PhotosAppBackApplication.class);
		//application.addListeners(new ApplicationPidFileWriter("./photos.pid"));
		//application.run();
		
		// Default
		SpringApplication.run(PhotosAppBackApplication.class, args);
	}
}