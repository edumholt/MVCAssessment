package edu.cvtc.web.predicates;

import com.google.common.base.Predicate;

import edu.cvtc.web.model.Movie;

/**
 * @author edumholt
 *
 */
public class MatchesDirectorPredicate implements Predicate<Movie> {
	
	private String director;

	public MatchesDirectorPredicate(final String director) {
		super();
		this.director = director;
	}

	@Override
	public boolean apply(final Movie movie) {
		return movie.getDirector().equals(director);
	}

}
