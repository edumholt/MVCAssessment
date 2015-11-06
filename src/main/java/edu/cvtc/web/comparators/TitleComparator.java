package edu.cvtc.web.comparators;

import java.util.Comparator;

import edu.cvtc.web.model.Movie;

/**
 * @author edumholt
 *
 */
public class TitleComparator implements Comparator<Movie> {

	@Override
	public int compare(final Movie movie1, final Movie movie2) {
		return movie1.getTitle().compareTo(movie2.getTitle());
	}

}
