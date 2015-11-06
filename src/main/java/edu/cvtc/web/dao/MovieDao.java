package edu.cvtc.web.dao;

import java.util.List;

import edu.cvtc.web.exception.MovieDatabaseException;
import edu.cvtc.web.model.Movie;

/**
 * @author edumholt
 *
 */
public interface MovieDao {

    Integer insertMovie(Movie movie) throws MovieDatabaseException;
	
	List<Movie> retrieveMovies() throws MovieDatabaseException;

	List<Movie> retrieveMoviesByTitle(String titleToSearchFor) throws MovieDatabaseException;

	List<Movie> retrieveMoviesByDirector(String directorToSearchFor) throws MovieDatabaseException;
	
	void populate(String fileName) throws MovieDatabaseException;
	
}
