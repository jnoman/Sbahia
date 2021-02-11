package servlets;

import java.io.IOException;
import java.util.Base64;

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
 * Servlet implementation class Detail
 */
@WebServlet("/Detail")
public class Detail extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProduitDao produitDao;
	
	public void init() throws ServletException {
		DaoFactory daoFactory = DaoFactory.getInstance();
		produitDao = daoFactory.getProduitDao();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("logged") != null) {
			Role role = (Role) session.getAttribute("role");
			if(role.getNomRole().equals("admin")) {
				if(request.getParameter("id") != null) {
					try {
						Produit produit = produitDao.afficherProduit(Integer.parseInt(request.getParameter("id")));
						request.setAttribute("article", produit);
						String image = "";
						if (produit != null) {
							byte[] imageBytes = produit.getImage();
							image = Base64.getEncoder().encodeToString(imageBytes);
						}
						request.setAttribute("image", image);
						
					} catch (DaoException e) {
						request.setAttribute("erreur", e.getMessage());
					} catch (BeanException e) {
						request.setAttribute("erreur", e.getMessage());
					}
					this.getServletContext().getRequestDispatcher("/WEB-INF/detail.jsp").forward(request, response);
				}else {
					response.sendRedirect("home");
				}
				
			} else {
				this.getServletContext().getRequestDispatcher("/WEB-INF/refused.jsp");
			}
			 
		} else {
			response.sendRedirect(request.getContextPath() + "/");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		if (request.getParameter("modifier") != null) {
			String nom = request.getParameter("nom");
			String description = request.getParameter("description");
			int quantite = Integer.parseInt(request.getParameter("quantite"));
			float prix = Float.parseFloat(request.getParameter("prix"));
			int ret;
			try {
				ret = produitDao.modifierProduit(new Produit(id, nom, description, null, quantite, prix));
				if(ret == 1) {
					request.setAttribute("succes", "la modification d'article terminé avec succès");
				}
			} catch (DaoException e) {
				request.setAttribute("erreur", e.getMessage());
			} catch (BeanException e) {
				request.setAttribute("erreur", e.getMessage());
			}
			doGet(request, response);
		} else if (request.getParameter("supprimer") != null) {
			try {
				int ret = produitDao.supprimerProduit(id);
				if(ret == 1) {
					request.getSession().setAttribute("succes", "la suppression d'article terminé avec succès");
				}
			} catch (DaoException e) {
				request.getSession().setAttribute("erreur", e.getMessage());
			} 
			response.sendRedirect(request.getContextPath() + "/home");
		}
	}

}
