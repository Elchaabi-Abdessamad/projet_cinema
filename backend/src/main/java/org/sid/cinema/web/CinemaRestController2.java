package org.sid.cinema.web;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.sid.cinema.dao.FilmRepository;
import org.sid.cinema.entities.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CinemaRestController2 {
	@Autowired
	private FilmRepository filmRepository;
	
	@GetMapping(path="/imageFilm/{id}",produces = MediaType.IMAGE_JPEG_VALUE)
	private byte[] image (@PathVariable(name="id") Long id) throws Exception {
		Film film=filmRepository.findById(id).get();
		String name=film.getPhoto();
		File file=new File(System.getProperty("user.home")+"/cinema/images/"+name);
		Path path=Paths.get(file.toURI());
		return Files.readAllBytes(path);
	}
	

}
