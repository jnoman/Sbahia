package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.BeanException;
import beans.Users;
import daoUsers.UsersDao;
import database.DaoException;
import database.DaoFactory;

@WebServlet("/Inscription")
public class Inscription extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UsersDao usersDao;

	@Override
	public void init() throws ServletException {
		DaoFactory daoFactory = DaoFactory.getInstance();
		usersDao = daoFactory.getUsersDao();
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nom = request.getParameter("nomComplet");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		try {
			int i = usersDao.iscription(new Users(nom, email, password));
			if(i==1) {
				request.getSession().setAttribute("succes", "la création du compte est terminée avec succés");
			} else {
				request.getSession().setAttribute("erreur", "échec de l'inscription");
			}
			response.sendRedirect(request.getContextPath() + "/");
		} catch (DaoException e) {
			request.setAttribute("erreur", e.getMessage());
		} catch (BeanException e) {
			request.setAttribute("erreur", e.getMessage());
		}
	}

}
