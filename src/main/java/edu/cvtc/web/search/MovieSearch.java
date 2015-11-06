package edu.cvtc.web.search;

import java.util.List;

import edu.cvtc.web.exception.MovieSearchException;
import edu.cvtc.web.model.Movie;

/**
 * @author edumholt
 *
 */
public interface MovieSearch {

	List<Movie> retrieveMovieList(String sortType) throws MovieSearchException;

	List<Movie> findMoviesByTitle(String title) throws MovieSearchException;

	List<Movie> findMoviesByDirector(String director) throws MovieSearchException;

}
