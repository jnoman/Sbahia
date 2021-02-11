package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.BeanException;
import beans.Produit;
import beans.Role;
import daoProduit.ProduitDao;
import database.DaoException;
import database.DaoFactory;

/**
 * Servlet implementation class HomeAdmin
 */
@WebServlet("/HomeAdmin")
public class HomeAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProduitDao produitDao;

	@Override
	public void init() throws ServletException {
		DaoFactory daoFactory = DaoFactory.getInstance();
		produitDao = daoFactory.getProduitDao();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("logged") != null) {
			Role role = (Role) session.getAttribute("role");
			if(role.getNomRole().equals("admin")) {
				try {
					List<Produit> listProduit = produitDao.afficherProduits();
					request.setAttribute("listProduit", listProduit);
					List<String> listImage = new ArrayList<String>();
					for (Produit produit : listProduit) {
						byte[] imageBytes = produit.getImage();
						listImage.add(Base64.getEncoder().encodeToString(imageBytes));
					}
					request.setAttribute("images", listImage);
				} catch (DaoException e) {
					request.setAttribute("erreur", e.getMessage());
				} catch (BeanException e) {
					request.setAttribute("erreur", e.getMessage());
				}
				this.getServletContext().getRequestDispatcher("/WEB-INF/homeAdmin.jsp").forward(request, response);
			} else {
				this.getServletContext().getRequestDispatcher("/WEB-INF/refused.jsp");
			}
			 
		} else {
			response.sendRedirect(request.getContextPath() + "/");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
