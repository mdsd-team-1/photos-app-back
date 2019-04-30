package co.edu.unal.photosappback.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unal.photosappback.PhotosAppBackApplication;

@RestController
public class HomeController {

	@RequestMapping("/")
	public String home(){
		return "Photos API v." + PhotosAppBackApplication.version;
	}

	@ExceptionHandler(Exception.class)
	public String error(HttpServletRequest req, Exception ex) {
		return "Photos API v." + PhotosAppBackApplication.version;
	}
}