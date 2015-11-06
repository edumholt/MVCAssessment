package edu.cvtc.web.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.cvtc.web.dao.MovieDao;
import edu.cvtc.web.dao.impl.MovieDaoImpl;
import edu.cvtc.web.exception.MovieDatabaseException;

/**
 * Servlet implementation class PopulateDatabaseController
 */
@WebServlet("/PopulateDatabase")
public class PopulateDatabaseController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PopulateDatabaseController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		String target = null;
		try {
			final MovieDao movieDao = new MovieDaoImpl();
			final String fileName = request.getServletContext().getRealPath("/assets/spreadsheets/JavaWebProgramming.xlsx");
			movieDao.populate(fileName);
			request.setAttribute("success", "Database successfully populated.");
			target = "success.jsp";
		} catch (final MovieDatabaseException e) {
			e.printStackTrace();
			request.setAttribute("error", "Sorry, we were unable to complete your request.");
			target = "error.jsp";
		}
		
		final RequestDispatcher view = request.getRequestDispatcher(target);
		view.forward(request, response);
	}

}
