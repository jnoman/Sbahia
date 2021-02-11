package daoLikes;

import beans.BeanException;
import beans.Likes;
import database.DaoException;

public interface LikesDao {
	int ajouterLike(Likes like) throws DaoException,BeanException;
	int supprimerLike(int idUser, int idProduit) throws DaoException;
	boolean dejaLike(int idUser, int idProduit) throws DaoException;
	int nombreLikes(int idProduit) throws DaoException;
}
