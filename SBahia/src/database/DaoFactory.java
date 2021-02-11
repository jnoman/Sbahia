package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import daoLikes.LikesDao;
import daoLikes.LikesDaoImpl;
import daoProduit.ProduitDao;
import daoProduit.ProduitDaoImpl;
import daoRole.RoleDao;
import daoRole.RoleDaoImpl;
import daoUsers.UsersDao;
import daoUsers.UsersDaoImpl;
import serviceUsers.UsersService;
import serviceUsers.UsersServiceImpl;

public class DaoFactory {
	private String url;
    private String username;
    private String password;

    DaoFactory(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public static DaoFactory getInstance() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {

        }

        DaoFactory instance = new DaoFactory(
                "jdbc:postgresql://localhost/SBahia", "postgres", "secret");
        return instance;
    }

    public Connection getConnection() throws SQLException {
    	Connection connexion = DriverManager.getConnection(url, username, password);
        connexion.setAutoCommit(false);
        return connexion; 
    }
    
    public UsersDao getUsersDao() {
    	return new UsersDaoImpl(this);
    }
    
    public RoleDao getRoleDao() {
    	return new RoleDaoImpl(this);
    }
    
    public ProduitDao getProduitDao() {
    	return new ProduitDaoImpl(this);
    }
    
    public LikesDao getLikesDao() {
    	return new LikesDaoImpl(this);
    }
    
    public UsersService getUsersService() {
    	return new UsersServiceImpl(this);
    }
}
