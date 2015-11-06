package edu.cvtc.web.dao.impl;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Strings;

import edu.cvtc.web.dao.MovieDao;
import edu.cvtc.web.exception.MovieDatabaseException;
import edu.cvtc.web.model.Movie;
import edu.cvtc.web.model.Star;
import edu.cvtc.web.util.DBUtils;
import edu.cvtc.web.util.WorkBookUtility;

/**
 * @author edumholt
 *
 */
public class MovieDaoImpl implements MovieDao {

	private static final String DROP_TABLE_MOVIE = "drop table if exists movie";
	private static final String CREATE_TABLE_MOVIE = "create table movie (id integer primary key autoincrement, title text, director text, minutes integer, star1 text, star2 text, star3 text);";
	private static final String SELECT_FROM_MOVIE = "select * from movie";
		
	@Override
    public Integer insertMovie(Movie movie) throws MovieDatabaseException {
	    Connection connection = null;
        PreparedStatement insertStatement = null;
        
        try {
            connection = DBUtils.createConnection(DBUtils.CONNECTION);
            
            final String insert = "insert into movie ( title, director, minutes, star1, star2, star3) values(?, ?, ?, ?, ?, ?)";
            insertStatement = connection.prepareStatement(insert);
            
            insertStatement.setString(1, movie.getTitle());
            insertStatement.setString(2, movie.getDirector());
            insertStatement.setInt(3, movie.getMinutes());
            insertStatement.setString(4, movie.getStars().get(0).getName());
            insertStatement.setString(5, movie.getStars().get(1).getName());
            insertStatement.setString(6, movie.getStars().get(2).getName());
            
            insertStatement.setQueryTimeout(DBUtils.TIMEOUT);
                        
            return insertStatement.executeUpdate();                                      
        } catch (Exception e) {
            e.printStackTrace();
            throw new MovieDatabaseException("Error inserting movie into database.");
        } finally {
            DBUtils.closeConnections(connection, insertStatement);
        }
        
    }

	@Override
	public List<Movie> retrieveMovies() throws MovieDatabaseException {
		final List<Movie> movies = new ArrayList<Movie>();
		Connection connection = null;
		Statement statement = null;
		
		try {
			connection = DBUtils.createConnection(DBUtils.CONNECTION);
			statement = connection.createStatement();
			statement.setQueryTimeout(DBUtils.TIMEOUT);
			
			final ResultSet results = statement.executeQuery(SELECT_FROM_MOVIE);
			buildMovieList(movies, results);
			
			results.close();
		
		} catch (final Exception e) {
			e.printStackTrace();
			throw new MovieDatabaseException("Error selecting movies from database.");
		} finally {
			DBUtils.closeConnections(connection, statement);
		}
		return movies;
		
	}
	
	@Override
	public List<Movie> retrieveMoviesByTitle(final String titleToSearchFor) throws MovieDatabaseException {
		
		final List<Movie> movies = new ArrayList<Movie>();
		Connection connection = null;
		Statement statement = null;
		
		try {
			connection = DBUtils.createConnection(DBUtils.CONNECTION);
			statement = connection.createStatement();
			statement.setQueryTimeout(DBUtils.TIMEOUT);
			
			final String sql = "select * from movie where title = '" + titleToSearchFor + "'";
			final ResultSet results = statement.executeQuery(sql);
			
			buildMovieList(movies, results);
			
			results.close();
			
		} catch (final Exception e) {
			e.printStackTrace();
			throw new MovieDatabaseException("Error retrieving movies by title.");
		} finally {
			DBUtils.closeConnections(connection, statement);
		}
		return movies;
	}
	
	@Override
	public List<Movie> retrieveMoviesByDirector(final String directorToSearchFor) throws MovieDatabaseException {
		
		final List<Movie> movies = new ArrayList<Movie>();
		Connection connection = null;
		Statement statement = null;
		
		try {
			connection = DBUtils.createConnection(DBUtils.CONNECTION);
			statement = connection.createStatement();
			statement.setQueryTimeout(DBUtils.TIMEOUT);
			
			final String sql = "select * from movie where director = '" + directorToSearchFor + "'";
			final ResultSet results = statement.executeQuery(sql);
			
			buildMovieList(movies, results);
			
			results.close();
			
		} catch (final Exception e) {
			e.printStackTrace();
			throw new MovieDatabaseException("Error retrieving movies by director.");
		} finally {
			DBUtils.closeConnections(connection, statement);
		}
		return movies;
	}
	
	private void buildMovieList(final List<Movie> movies, final ResultSet results) throws SQLException {
		
		while (results.next()) {
			final int id = 0;
			final String title = results.getString("title");
			final String director = results.getString("director");
			final int minutes = results.getInt("minutes");
			final String star1 = results.getString("star1");
			final String star2 = results.getString("star2");
			final String star3 = results.getString("star3");
			
			final List<Star> stars = new ArrayList<Star>();
			if (!Strings.isNullOrEmpty(star1)) {
				stars.add(new Star(star1, null));
			}
			if (!Strings.isNullOrEmpty(star2)) {
				stars.add(new Star(star2, null));
			}
			if (!Strings.isNullOrEmpty(star3)) {
				stars.add(new Star(star3, null));
			}
			final Movie movie = new Movie(id, title, director, minutes, stars);
			movies.add(movie);
		}
		
	}
	
	@Override
	public void populate(final String filePath) throws MovieDatabaseException {
		
		Connection connection = null;
		Statement statement = null;
		
		try {
			
			connection = DBUtils.createConnection(DBUtils.CONNECTION);
			statement = connection.createStatement();
			statement.setQueryTimeout(DBUtils.TIMEOUT);
			
			statement.executeUpdate(DROP_TABLE_MOVIE);
			statement.executeUpdate(CREATE_TABLE_MOVIE);
			
			final List<Movie> movies = WorkBookUtility.retrieveMoviesFromWorkBook(new File(filePath));
			
			for (final Movie movie : movies) {
				String insertValues = "insert into movie (title, director, minutes, star1, star2, star3) values('" 
						+ movie.getTitle() + "', '" 
						+ movie.getDirector() + "', "
						+ movie.getMinutes();
				for (final Star star : movie.getStars()) {
					final String starName = null != star.getName() ? star.getName() : "";
					insertValues += ", '" + starName + "'";
				}
				if (movie.getStars().isEmpty()) {
					insertValues += ", '', '', ''";
				} else if (movie.getStars().size() == 1) {
					insertValues += ", '', ''";
				} else if (movie.getStars().size() == 2) {
					insertValues += ", ''";
				} else if (movie.getStars().size() == 3) {
				    insertValues += "";
				}
				insertValues += ");";
				System.out.println(insertValues);
				statement.executeUpdate(insertValues);
			}
		} catch (final Exception e) {
			e.printStackTrace();
			throw new MovieDatabaseException("Error encountered while populating database.");
		} finally {
			DBUtils.closeConnections(connection, statement);
		}
	}
	
}

