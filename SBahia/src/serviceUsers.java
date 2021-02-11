import beans.BeanException;
import beans.Users;
import database.DaoException;

public interface serviceUsers {
	Users login(String email, String password) throws DaoException,BeanException;
}
