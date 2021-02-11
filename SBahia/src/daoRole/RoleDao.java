package daoRole;

import beans.BeanException;
import beans.Role;
import database.DaoException;

public interface RoleDao {
	Role getRole(int idRole) throws DaoException,BeanException;
}
