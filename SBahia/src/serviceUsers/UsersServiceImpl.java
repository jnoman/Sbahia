package serviceUsers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import beans.BeanException;
import beans.Users;
import database.DaoException;
import database.DaoFactory;

public class UsersServiceImpl implements UsersService {
	
	private DaoFactory daoFactory;

	public UsersServiceImpl(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

	@Override
	public Users login(UserLogin userLogin) throws DaoException, BeanException {
		Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultat = null;
        Users user = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = connexion.prepareStatement("select id,nom_complet,Role from users where Email=? and password=?;");
            preparedStatement.setString(1, userLogin.getEmail());
            preparedStatement.setString(2, userLogin.getPassword());
            resultat = preparedStatement.executeQuery();
            if(resultat.next()) {
            	user = new Users(resultat.getInt(1), resultat.getString(2), userLogin.getEmail(), resultat.getInt(3));
            }
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
        return user;
	}
	
	
}
