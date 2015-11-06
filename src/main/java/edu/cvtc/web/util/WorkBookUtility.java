package edu.cvtc.web.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import edu.cvtc.web.model.Movie;
import edu.cvtc.web.model.Star;

/**
 * @author edumholt
 *
 */
public class WorkBookUtility {
	
	public static List<Movie> retrieveMoviesFromWorkBook(final File file) throws InvalidFormatException, IOException {
		final Workbook workbook = WorkbookFactory.create(file);
		final List<Movie> movies = new ArrayList<Movie>();

		final Sheet sheet = workbook.getSheetAt(0);
		
		for (final Row row : sheet) {
			
			final Cell title = row.getCell(0);
			final Cell director = row.getCell(1);
			final Cell length = row.getCell(2);
			final Cell star1 = row.getCell(3);
			final Cell star2 = row.getCell(4);
			final Cell star3 = row.getCell(5);
			
			final List<Star> stars = new ArrayList<Star>();
			
			if (null != title) {
				
				if (null != star1) {
					stars.add(new Star(star1.getStringCellValue(), null));
				}
				if (null != star2) {
					stars.add(new Star(star2.getStringCellValue(), null));
				}
				if (null != star3) {
					stars.add(new Star(star3.getStringCellValue(), null));
				}
				
				final String titleString = null != title ? title.getStringCellValue().trim(): "";
				final String directorString = null != director ? director.getStringCellValue().trim(): "";
				final int lengthValue = (int) (null != length ? length.getNumericCellValue() : 0);
				final Movie movie = new Movie(
						0,
						titleString, 
						directorString, 
						lengthValue, 
						stars);
				movies.add(movie);
				
			}
			
		}
		return movies;
	}
	
}
