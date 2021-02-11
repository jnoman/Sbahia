package daoRole;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import beans.BeanException;
import beans.Role;
import database.DaoException;
import database.DaoFactory;

public class RoleDaoImpl implements RoleDao {
	
	private DaoFactory daoFactory;

	public RoleDaoImpl(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

	@Override
	public Role getRole(int idRole) throws DaoException, BeanException {
		Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultat = null;
        Role role = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = connexion.prepareStatement("select nomRole from role where id = ?;");
            preparedStatement.setInt(1, idRole);
            resultat = preparedStatement.executeQuery();
            if(resultat.next()) {
            	role = new Role(idRole, resultat.getString(1));
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
        return role;
	}

}
