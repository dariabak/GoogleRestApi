package utils;
import java.util.ArrayList;

import model.Film;

import java.sql.*;


public class FilmDAO {
	
	Film oneFilm = null;
	Connection conn = null;
    Statement stmt = null;
	String user = "bakdaria";
    String password = "qwirMter9";
    // Note none default port used, 6306 not 3306
    String url = "jdbc:mysql://mudfoot.doc.stu.mmu.ac.uk:6306/"+user;

	public FilmDAO() {}

	
	private void openConnection(){
		// loading jdbc driver for mysql
		try{
		    Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch(Exception e) { System.out.println(e); }

		// connecting to database
		try{
			// connection string for demos database, username demos, password demos
 			conn = DriverManager.getConnection(url, user, password);
		    stmt = conn.createStatement();
		} catch(SQLException se) { System.out.println(se); }	   
    }
	private void closeConnection(){
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private Film getNextFilm(ResultSet rs){
    	Film thisFilm=null;
		try {
			thisFilm = new Film(
					rs.getInt("id"),
					rs.getString("title"),
					rs.getInt("year"),
					rs.getString("director"),
					rs.getString("stars"),
					rs.getString("review"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return thisFilm;		
	}
	
	
	
   public ArrayList<Film> getAllFilms(){
	   
		ArrayList<Film> allFilms = new ArrayList<Film>();
		openConnection();
		
	    // Create select statement and execute it
		try{
		    String selectSQL = "select * from films";
		    ResultSet rs1 = stmt.executeQuery(selectSQL);
	    // Retrieve the results
		    while(rs1.next()){
		    	oneFilm = getNextFilm(rs1);
		    	allFilms.add(oneFilm);
		   }

		    stmt.close();
		    closeConnection();
		} catch(SQLException se) { System.out.println(se); }

	   return allFilms;
   }

   public Film getFilmByID(int id){
	   
		openConnection();
		oneFilm=null;
	    // Create select statement and execute it
		try{
		    String selectSQL = "select * from films where id="+id;
		    ResultSet rs1 = stmt.executeQuery(selectSQL);
	    // Retrieve the results
		    while(rs1.next()){
		    	oneFilm = getNextFilm(rs1);
		    }

		    stmt.close();
		    closeConnection();
		} catch(SQLException se) { System.out.println(se); }

	   return oneFilm;
   }
   
   public ArrayList<Film> getFilmsByName(String name) {
	   ArrayList<Film> films = new ArrayList<Film>();
	   openConnection();
	   try{
		    String selectSQL = "select * from films where films.title like '%" + name + "%'";
		    ResultSet rs1 = stmt.executeQuery(selectSQL);
	    // Retrieve the results
		    while(rs1.next()){
		    	oneFilm = getNextFilm(rs1);
		    	films.add(oneFilm);
		   }

		    stmt.close();
		    closeConnection();
		} catch(SQLException se) { System.out.println(se); }
	   
	   return films;
   }
   
   public void addNewFilm(Film film) {
	   openConnection();
	   String message = "";
	   try{
		    String selectSQL = "insert into films (id, title, year, director, stars, review) values (" + film.getId() + ",'" + film.getTitle() + "'," + film.getYear() + ",'" + film.getDirector() + "','" + film.getStars() + "','" + film.getReview() + "')";
		    int rs1 = stmt.executeUpdate(selectSQL);

		    stmt.close();
		   
		    closeConnection();
		} catch(SQLException se) { 
			System.out.println(se); 
			}
   }
   
   public void deleteFilm(String id) {
	   openConnection();
	   int idInt = Integer.parseInt(id);
	   try{
		    String selectSQL = "delete from films where id = " + idInt;
		    int rs1 = stmt.executeUpdate(selectSQL);

		    stmt.close();
		   
		    closeConnection();
		} catch(SQLException se) { 
			System.out.println(se); 
			}
   }
   
}
