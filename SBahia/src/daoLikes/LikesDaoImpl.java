package daoLikes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import beans.BeanException;
import beans.Likes;
import database.DaoException;
import database.DaoFactory;

public class LikesDaoImpl implements LikesDao {
	
	private DaoFactory daoFactory;

	public LikesDaoImpl(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

	@Override
	public int ajouterLike(Likes like) throws DaoException, BeanException {
		Connection connexion = null;
        PreparedStatement preparedStatement = null;
        int ret = 0;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = connexion.prepareStatement("INSERT into likes values(?,?);");
            preparedStatement.setInt(1, like.getIdUser());
            preparedStatement.setInt(2, like.getIdProduit());
            ret = preparedStatement.executeUpdate();
            connexion.commit();
        } catch (SQLException e) {
            throw new DaoException("Impossible de communiquer avec la base de données");
        }
        finally {
            try {
                if (connexion != null) {
                    connexion.close();  
                }
            } catch (SQLException e) {
                throw new DaoException("Impossible de communiquer avec la base de données");
            }
        }
        return ret;
	}

	@Override
	public int supprimerLike(int idUser, int idProduit) throws DaoException{
		Connection connexion = null;
        PreparedStatement preparedStatement = null;
        int ret = 0;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = connexion.prepareStatement("delete from likes where idUser=? and idProduit=?;");
            preparedStatement.setInt(1, idUser);
            preparedStatement.setInt(2, idProduit);
            ret = preparedStatement.executeUpdate();
            connexion.commit();
        } catch (SQLException e) {
            throw new DaoException("Impossible de communiquer avec la base de données");
        }
        finally {
            try {
                if (connexion != null) {
                    connexion.close();  
                }
            } catch (SQLException e) {
                throw new DaoException("Impossible de communiquer avec la base de données");
            }
        }
        return ret;
	}

	@Override
	public boolean dejaLike(int idUser, int idProduit) throws DaoException{
		Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultat = null;
        boolean ret = false;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = connexion.prepareStatement("select count(*) from likes where idUser=? and idProduit=?;");
            preparedStatement.setInt(1, idUser);
            preparedStatement.setInt(2, idProduit);
            resultat = preparedStatement.executeQuery();
            if(resultat.next()) {
            	if(resultat.getInt(1) != 0) {
            		ret = true;
            	}
            }
        } catch (SQLException e) {
            throw new DaoException("Impossible de communiquer avec la base de données");
        }
        finally {
            try {
                if (connexion != null) {
                    connexion.close();  
                }
            } catch (SQLException e) {
                throw new DaoException("Impossible de communiquer avec la base de données");
            }
        }
        return ret;
	}

	@Override
	public int nombreLikes(int idProduit) throws DaoException {
		Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultat = null;
        int ret = 0;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = connexion.prepareStatement("select count(*) from likes whereidProduit=?;");
            preparedStatement.setInt(1, idProduit);
            resultat = preparedStatement.executeQuery();
            resultat.next();
            ret = resultat.getInt(1);
        } catch (SQLException e) {
            throw new DaoException("Impossible de communiquer avec la base de données");
        }
        finally {
            try {
                if (connexion != null) {
                    connexion.close();  
                }
            } catch (SQLException e) {
                throw new DaoException("Impossible de communiquer avec la base de données");
            }
        }
        return ret;
	}

}
