package utils;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;


import model.Film;


public class FilmUtils {

	public static String convertArrayListToString(ArrayList<Film> films) {
		String response = "\n";
		for(Film film: films){
			response += film.getId() + "#" + film.getTitle() + "#" + film.getYear() + "#" + film.getDirector() + "#" + film.getStars() + "#" + film.getReview() + "\n";
		}
		
		return response;
	}
	
	public static String convertFilmToString(Film film) {
		String str = "\n" + film.getId() + "#" + film.getTitle() + "#" + film.getYear() + "#" + film.getDirector() + "#" + film.getStars() + "#" + film.getReview() + "\n";
		return str;
	}
	
	public static Film createFilmFromString(String filmString) {
		Film film = new Film();
		filmString = filmString.replace("\n", "");
		String[] row = filmString.split("#");
		int id = Integer.valueOf(row[0], 10);
		int year = Integer.valueOf(row[2], 10);
		film.setId(id);
		film.setTitle(row[1]);
		film.setYear(year);
		film.setDirector(row[3]);
		film.setStars(row[4]);
		film.setReview(row[5]);
		
		return film;
	}

}
