package daoUsers;

import beans.BeanException;
import beans.Users;
import database.DaoException;

public interface UsersDao {
	int iscription(Users user) throws DaoException,BeanException;
}
