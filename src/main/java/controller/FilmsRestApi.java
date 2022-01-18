package controller;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import model.Film;
import utils.FilmDAO;
import utils.FilmUtils;

public class FilmsRestApi {

	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	String id;

	public FilmsRestApi(UriInfo uriInfo, Request request, String id) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.id = id;
	}
	
	@GET
	@Produces(MediaType.TEXT_XML)
	public Film getFilmByIDXML() {
		FilmDAO dao = new FilmDAO();
		Film film = dao.getFilmByID(Integer.parseInt(id));
		return film;
	}
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getFilmByIDString() {
		FilmDAO dao = new FilmDAO();
		Film film = dao.getFilmByID(Integer.parseInt(id));
		return FilmUtils.convertFilmToString(film);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Film getFilmByIDJson() {
		FilmDAO dao = new FilmDAO();
		Film film = dao.getFilmByID(Integer.parseInt(id));
		return film;
	}
	
	@DELETE
	public String deleteFilm() {
		FilmDAO dao = new FilmDAO();
		dao.deleteFilm(id);
		return "";
	}
}
