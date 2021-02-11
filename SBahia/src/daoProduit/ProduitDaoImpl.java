package daoProduit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.BeanException;
import beans.Produit;
import database.DaoException;
import database.DaoFactory;

public class ProduitDaoImpl implements ProduitDao {
	
	private DaoFactory daoFactory;

	public ProduitDaoImpl(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

	@Override
	public int ajouterProduit(Produit produit) throws DaoException, BeanException {
		Connection connexion = null;
        PreparedStatement preparedStatement = null;
        int ret = 0;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = connexion.prepareStatement("insert into produit(nom,description,image,quantite,prix) values(?,?,?,?,?);");
            preparedStatement.setString(1, produit.getNom());
            preparedStatement.setString(2, produit.getDescription());
            preparedStatement.setBytes(3, produit.getImage());;
            preparedStatement.setInt(4, produit.getQuantite());
            preparedStatement.setFloat(5, produit.getPrix());
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
	public int modifierProduit(Produit produit) throws DaoException, BeanException {
		Connection connexion = null;
        PreparedStatement preparedStatement = null;
        int ret = 0;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = connexion.prepareStatement("update produit set nom=?,description=?,quantite=?,prix=? where id=?;");
            preparedStatement.setString(1, produit.getNom());
            preparedStatement.setString(2, produit.getDescription());
            preparedStatement.setInt(3, produit.getQuantite());
            preparedStatement.setFloat(4, produit.getPrix());
            preparedStatement.setInt(5, produit.getId());
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
	public int supprimerProduit(int idProduit) throws DaoException {
		Connection connexion = null;
        PreparedStatement preparedStatement = null;
        int ret = 0;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = connexion.prepareStatement("delete from produit where id=?;");
            preparedStatement.setInt(1, idProduit);
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
	public List<Produit> afficherProduits() throws DaoException, BeanException {
		Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultat = null;
        List<Produit> listProduits = new ArrayList<Produit>();

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = connexion.prepareStatement("select * from produit;");
            resultat = preparedStatement.executeQuery();
            while(resultat.next()) {
            	listProduits.add(new Produit(resultat.getInt(1), resultat.getString(2), resultat.getString(3), resultat.getBytes(4), resultat.getInt(5), resultat.getFloat(6)));
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
        return listProduits;
	}
	
	@Override
	public Produit afficherProduit(int idProduit) throws DaoException, BeanException {
		Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultat = null;
        Produit produit = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = connexion.prepareStatement("select * from produit where id = ?;");
            preparedStatement.setInt(1, idProduit);
            resultat = preparedStatement.executeQuery();
            if (resultat.next()) {
            	produit = new Produit(resultat.getInt(1), resultat.getString(2), resultat.getString(3), resultat.getBytes(4), resultat.getInt(5), resultat.getFloat(6));
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
        return produit;
	}

}
