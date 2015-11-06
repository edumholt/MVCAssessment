package edu.cvtc.web.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.cvtc.web.dao.MovieDao;
import edu.cvtc.web.dao.impl.MovieDaoImpl;
import edu.cvtc.web.exception.MovieDatabaseException;
import edu.cvtc.web.model.Movie;
import edu.cvtc.web.model.Star;

/**
 * Servlet implementation class AddMovieController
 */
@WebServlet("/AddMovie")
public class AddMovieController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private MovieDao movieDao = new MovieDaoImpl();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddMovieController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	    String target = null;
	    
	    final String title = request.getParameter("title");
        final String director = request.getParameter("director");
        final String mins = request.getParameter("minutes");
        
        final String star1 = request.getParameter("star_1");
        final String star2 = request.getParameter("star_2");
        final String star3 = request.getParameter("star_3");
        
        List<Star> stars = new ArrayList<>();
        stars.add(new Star(star1, null));
        stars.add(new Star(star2, null));
        stars.add(new Star(star3, null));
        
        if(title.isEmpty() || director.isEmpty() || mins.isEmpty() || stars.isEmpty()){
            request.setAttribute("error", "Please enter data for all fields");
            target = "error.jsp";
        } else {
        
        
	    
	    try {
	        final int minutes = Integer.parseInt(mins);
	        
            Movie movie = new Movie(0, title, director, minutes, stars);
	        
            getMovieDao().insertMovie(movie);
            
            request.setAttribute("success", "Movie successfully added to database.");
            target = "success.jsp";
            
        } catch (final MovieDatabaseException e) {
            e.printStackTrace();
            request.setAttribute("error", "Sorry, there was a problem adding this movie to the database.");
            target = "error.jsp";
        } catch (final NumberFormatException e) {
            e.printStackTrace();
            request.setAttribute("error", "Please enter an integer for minutes");
            target = "error.jsp";
        }
        }
	    request.getRequestDispatcher(target).forward(request,  response);
	    
	}
	
	public MovieDao getMovieDao() {
        if(null == movieDao) {
            movieDao = new MovieDaoImpl();
        }
        return movieDao;
    }

}
