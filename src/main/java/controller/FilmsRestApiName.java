package controller;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import model.Film;
import utils.FilmDAO;
import utils.FilmUtils;

public class FilmsRestApiName {

	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	String name;
	
	public FilmsRestApiName(UriInfo uriInfo, Request request, String name) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.name = name;
	}
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getFilmsByNameString() {
		FilmDAO dao = new FilmDAO();
		name = name.toUpperCase();
		String str = FilmUtils.convertArrayListToString(dao.getFilmsByName(name));
		return str;
	}
	
	@GET
	@Produces(MediaType.TEXT_XML)
	public List<Film> getFilmsByNameXML() {
		FilmDAO dao = new FilmDAO();
		name = name.toUpperCase();
		List<Film> films = dao.getFilmsByName(name);
		return films;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Film> getFilmsByNameJson() {
		FilmDAO dao = new FilmDAO();
		name = name.toUpperCase();
		List<Film> films = dao.getFilmsByName(name);
		return films;
	}
}
