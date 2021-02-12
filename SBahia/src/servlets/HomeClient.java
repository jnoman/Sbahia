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
import beans.Likes;
import beans.Produit;
import beans.Role;
import beans.Users;
import daoLikes.LikesDao;
import daoProduit.ProduitDao;
import database.DaoException;
import database.DaoFactory;

/**
 * Servlet implementation class HomeClient
 */
@WebServlet("/HomeClient")
public class HomeClient extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProduitDao produitDao;
	private LikesDao likesDao;
	private Users user;

	public void init() throws ServletException {
		DaoFactory daoFactory = DaoFactory.getInstance();
		produitDao = daoFactory.getProduitDao();
		likesDao = daoFactory.getLikesDao();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("logged") != null) {
			user = (Users) session.getAttribute("logged");
			Role role = (Role) session.getAttribute("role");
			if(role.getNomRole().equals("client")) {
				try {
					List<Produit> listProduit = produitDao.afficherProduits();
					request.setAttribute("listProduit", listProduit);
					List<String> listImage = new ArrayList<String>();
					List<String> nombreLikes = new ArrayList<String>();
					List<Boolean> dejaLike = new ArrayList<Boolean>();
					for (Produit produit : listProduit) {
						byte[] imageBytes = produit.getImage();
						listImage.add(Base64.getEncoder().encodeToString(imageBytes));
						
						nombreLikes.add(Integer.toString(likesDao.nombreLikes(produit.getId())));
						
						dejaLike.add(likesDao.dejaLike(user.getId(), produit.getId()));
					}
					request.setAttribute("images", listImage);
					request.setAttribute("nombreLikes", nombreLikes);
					request.setAttribute("dejaLike", dejaLike);
				} catch (DaoException e) {
					request.setAttribute("erreur", e.getMessage());
				} catch (BeanException e) {
					request.setAttribute("erreur", e.getMessage());
				}
				this.getServletContext().getRequestDispatcher("/WEB-INF/homeClient.jsp").forward(request, response);
			} else {
				this.getServletContext().getRequestDispatcher("/WEB-INF/refused.jsp");
			}
			 
		} else {
			response.sendRedirect(request.getContextPath() + "/");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("articleId") != null) {
			int idProduit = Integer.parseInt(request.getParameter("articleId"));
			String nombreLikes = "";
			HttpSession session = request.getSession();
			user = (Users) session.getAttribute("logged");
			try {
				if(likesDao.dejaLike(user.getId(), idProduit)) {
					likesDao.supprimerLike(user.getId(), idProduit);
				} else {
					likesDao.ajouterLike(new Likes(user.getId(), idProduit));
				}
				nombreLikes = Integer.toString(likesDao.nombreLikes(idProduit));
			} catch (DaoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BeanException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response.setContentType("text/xml");
		    response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("<message>"+nombreLikes+"</message>");
		} 
	}

}
