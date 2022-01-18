package controller;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;

import model.Film;

import utils.FilmDAO;
import utils.FilmUtils;

@Api(name = "all-films")
@Path("all-films")
public class FilmRestApi {
	
	@Context
	UriInfo uriInfo;
	@Context
	Request request;

	@ApiMethod(name = "all-films")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getFilmsText() {
		FilmDAO dao = new FilmDAO();
		
		ArrayList<Film> films = dao.getAllFilms();
		String response = FilmUtils.convertArrayListToString(films);

		return response;
	}

	@GET
	@Produces(MediaType.TEXT_XML)
	public List<Film> getXMLFilms() {
		FilmDAO dao = new FilmDAO();
		List<Film> films = dao.getAllFilms();
		return films;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Film> getJsonFilms() {
		FilmDAO dao = new FilmDAO();
		List<Film> films = dao.getAllFilms();
		return films;
	}
	
	@Path("new-film")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String addNewFilmJson(Film film) throws Exception {
		FilmDAO dao = new FilmDAO();
		//System.out.print("JSON");
		dao.addNewFilm(film);
		
		return "";
	}
	
	@Path("new-film-xml")
	@POST
	@Produces(MediaType.TEXT_XML)
	@Consumes(MediaType.TEXT_XML)
	public Film addNewFilmXml(Film film) throws Exception {
		FilmDAO dao = new FilmDAO();
		//System.out.println("XML");
		dao.addNewFilm(film);
		
		return film;
	}
	
	@Path("new-film-string")
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.TEXT_PLAIN)
	public String addNewFilmString(String filmString) throws Exception {
		FilmDAO dao = new FilmDAO();
		Film film = FilmUtils.createFilmFromString(filmString); 
		dao.addNewFilm(film);
		
		return "";
	}
	
	@Path("update")
	@PUT
	@Consumes(MediaType.TEXT_XML)
	public String updateFilmXml(Film film) {
		return "";
	}
	
	
	@Path("name/{name}")
	public FilmsRestApiName getRestApiName(@PathParam("name") String name) {
		return new FilmsRestApiName(uriInfo, request, name);
	}
	
	@Path("id/{id}")
	public FilmsRestApi getRestApiId(
	@PathParam("id") String id) {
	return new FilmsRestApi(uriInfo, request, id);
	}
	

	
}
