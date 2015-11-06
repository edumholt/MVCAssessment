package edu.cvtc.web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.cvtc.web.exception.MovieSearchException;
import edu.cvtc.web.model.Movie;
import edu.cvtc.web.search.MovieSearch;
import edu.cvtc.web.search.impl.MovieSearchImpl;

/**
 * Servlet implementation class ViewAllController
 */
@WebServlet("/ViewAll")
public class ViewAllController extends HttpServlet {
	private static final long serialVersionUID = 1L;

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
	    
	    try{
	        final MovieSearch movieSearch = new MovieSearchImpl();
	        final String sortType = request.getParameter("sort");
	        List<Movie> movies = movieSearch.retrieveMovieList(sortType);
	        
	        request.setAttribute("movies", movies);
	        target = "view-all.jsp";
	        
	    } catch (MovieSearchException e){
	        
	        e.printStackTrace();
	        request.setAttribute("error", "Sorry, we were unable to complete your request");
	        target = "error.jsp";
	    }
	    
	    request.getRequestDispatcher(target).forward(request, response);
	    
	}

}
