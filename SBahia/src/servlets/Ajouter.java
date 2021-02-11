package servlets;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import beans.BeanException;
import beans.Produit;
import beans.Role;
import daoProduit.ProduitDao;
import database.DaoException;
import database.DaoFactory;
import org.apache.commons.io.IOUtils;


@WebServlet("/Ajouter")
@MultipartConfig(maxFileSize = 16177215)
public class Ajouter extends HttpServlet {
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
				this.getServletContext().getRequestDispatcher("/WEB-INF/ajouter.jsp").forward(request, response);
			} else {
				this.getServletContext().getRequestDispatcher("/WEB-INF/refused.jsp").forward(request, response);
			}
			 
		} else {
			response.sendRedirect(request.getContextPath() + "/");
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nom = request.getParameter("nom");
		String description = request.getParameter("description");
		int quantite = Integer.parseInt(request.getParameter("quantite"));
		float prix = Float.parseFloat(request.getParameter("prix"));
		
		InputStream inputStream = null;
        Part filePart = request.getPart("image");
        if (filePart != null) {
            inputStream = filePart.getInputStream();
        } 
        
        byte[] image = IOUtils.toByteArray(inputStream);
        try {
			int ret = produitDao.ajouterProduit(new Produit(nom, description, image, quantite, prix));
			if(ret == 1) {
				request.setAttribute("succes", "ajouter l'article terminé avec succès");
			}
		} catch (DaoException e) {
			request.setAttribute("erreur", e.getMessage());
		} catch (BeanException e) {
			request.setAttribute("erreur", e.getMessage());
		}
        doGet(request, response);
	}

}
