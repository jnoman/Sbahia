package serviceUsers;
import beans.BeanException;
import beans.Users;
import database.DaoException;

public interface UsersService {
	Users login(String email, String password) throws DaoException,BeanException;
}
