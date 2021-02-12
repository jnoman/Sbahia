package serviceUsers;
import beans.BeanException;
import beans.Users;
import database.DaoException;

public interface UsersService {
	Users login(UserLogin userLogin) throws DaoException,BeanException;
}
