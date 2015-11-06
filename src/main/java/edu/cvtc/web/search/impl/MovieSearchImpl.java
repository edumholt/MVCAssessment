package edu.cvtc.web.search.impl;

import java.util.Collections;
import java.util.List;

import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;

import edu.cvtc.web.comparators.LengthInMinutesComparator;
import edu.cvtc.web.comparators.SortBy;
import edu.cvtc.web.comparators.TitleComparator;
import edu.cvtc.web.dao.MovieDao;
import edu.cvtc.web.dao.impl.MovieDaoImpl;
import edu.cvtc.web.exception.MovieDatabaseException;
import edu.cvtc.web.exception.MovieSearchException;
import edu.cvtc.web.model.Movie;
import edu.cvtc.web.predicates.MatchesDirectorPredicate;
import edu.cvtc.web.predicates.MatchesTitlePredicate;
import edu.cvtc.web.search.MovieSearch;

/**
 * @author edumholt
 *
 */
public class MovieSearchImpl implements MovieSearch {
	
	private MovieDao movieDao = new MovieDaoImpl();

	@Override
	public List<Movie> retrieveMovieList(final String sortType) throws MovieSearchException {
		try {
			final List<Movie> movies = movieDao.retrieveMovies();
			if (null != sortType) {
				sortMovies(movies, sortType);
			}
			return movies;
		} catch (final Exception e) {
			e.printStackTrace();
			throw new MovieSearchException("Error retrieving movie list.");
		}
	}

	private void sortMovies(final List<Movie> movies, final String sortType) {
		switch (sortType) {
			case SortBy.TITLE:
				Collections.sort(movies, new TitleComparator());
				break;
			case SortBy.LENGTH:
				Collections.sort(movies, new LengthInMinutesComparator());
				break;
			default:
				break;
		}
	}
	
	@Override
	public List<Movie> findMoviesByTitle(final String title) throws MovieSearchException {
		try {
			final List<Movie> movies = retrieveMoviesFromDatabase();
			return Lists.newArrayList(Collections2.filter(movies, new MatchesTitlePredicate(title)));
		} catch (final Exception e) {
			e.printStackTrace();
			throw new MovieSearchException("Error finding movies by title.");
		}
	}
	
	@Override
	public List<Movie> findMoviesByDirector(final String director) throws MovieSearchException {
		try {
			final List<Movie> movies = retrieveMoviesFromDatabase();
			return Lists.newArrayList(Collections2.filter(movies, new MatchesDirectorPredicate(director)));
		} catch (final Exception e) {
			e.printStackTrace();
			throw new MovieSearchException("Error finding movies by director.");
		}
	}

	private List<Movie> retrieveMoviesFromDatabase() throws MovieDatabaseException {
		return movieDao.retrieveMovies();
	}

}
