package daoUsers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import beans.BeanException;
import beans.Users;
import database.DaoException;
import database.DaoFactory;

public class UsersDaoImpl implements UsersDao {
	
	private DaoFactory daoFactory;

	public UsersDaoImpl(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

	@Override
	public int iscription(Users user) throws DaoException, BeanException {
		Connection connexion = null;
        PreparedStatement preparedStatement = null;
        int ret = 0;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = connexion.prepareStatement("INSERT into users(nom_complet,Email,password) values(?,?,?);");
            preparedStatement.setString(1, user.getNomComplet());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            ret = preparedStatement.executeUpdate();
            connexion.commit();
        } catch (SQLException e) {
            throw new DaoException("Impossible de communiquer avec la base de donn�es");
        }
        finally {
            try {
                if (connexion != null) {
                    connexion.close();  
                }
            } catch (SQLException e) {
                throw new DaoException("Impossible de communiquer avec la base de donn�es");
            }
        }
        return ret;
	}

	
	
}
