package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.BeanException;
import beans.Role;
import beans.Users;
import daoRole.RoleDao;
import database.DaoException;
import database.DaoFactory;
import serviceUsers.UserLogin;
import serviceUsers.UsersService;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UsersService usersService;
	private RoleDao roleDao;
	
	@Override
	public void init() throws ServletException {
		DaoFactory daoFactory = DaoFactory.getInstance();
		usersService = daoFactory.getUsersService();
		roleDao = daoFactory.getRoleDao();	
	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		try {
			Users user = usersService.login(new UserLogin(email, password));
			if(user != null) {
				HttpSession session = request.getSession();
				session.setAttribute("logged", user);
				Role role = roleDao.getRole(user.getRole());
				session.setAttribute("role", role);
				if(role.getNomRole().equals("admin")) {
					response.sendRedirect(request.getContextPath() + "/home");
				} else {
					response.sendRedirect(request.getContextPath() + "/index");
				}
			} else {
				request.getSession().setAttribute("erreur", "Email ou mot de passe est incorrect");
				response.sendRedirect(request.getContextPath() + "/");
			}
		} catch (DaoException e) {
			request.getSession().setAttribute("erreur", e.getMessage());
		} catch (BeanException e) {
			request.getSession().setAttribute("erreur", e.getMessage());
		}
	}

}
