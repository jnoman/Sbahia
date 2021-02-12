package serviceUsers;

import beans.BeanException;

public class UserLogin {
	String email;
	String password;
	
	public String getEmail() {
		return email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public UserLogin(String email, String password) throws BeanException {
		this.email = email;
		if (password.length() < 8) {
            throw new BeanException("La mot de passe est trop petite ! (8 caractères minimum)");
        } else {
    		this.password = password;
        }
	}
}
