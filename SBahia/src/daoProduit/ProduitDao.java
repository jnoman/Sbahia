package daoProduit;

import java.util.List;

import beans.BeanException;
import beans.Produit;
import database.DaoException;

public interface ProduitDao {
	int ajouterProduit(Produit produit) throws DaoException,BeanException;
	int modifierProduit(Produit produit) throws DaoException,BeanException;
	int supprimerProduit(int idProduit) throws DaoException;
	List<Produit> afficherProduits() throws DaoException,BeanException;
	Produit afficherProduit(int idProduit) throws DaoException,BeanException;
}
