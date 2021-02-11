package beans;

public class Users {
	int id;
	String nomComplet;
	String email;
	int role;
	String password;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNomComplet() {
		return nomComplet;
	}
	public void setNomComplet(String nomComplet) {
		this.nomComplet = nomComplet;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Users(String nomComplet, String email, String password) throws BeanException {
		super();
		if (nomComplet.length() < 4) {
            throw new BeanException("Le nom est trop petit ! (4 caractères minimum)");
        }
        else {
            this.nomComplet = nomComplet; 
        }
		this.email = email;
		if (password.length() < 8) {
            throw new BeanException("La mot de passe est trop petite ! (8 caractères minimum)");
        } else {
    		this.password = password;
        }
	}
	public Users(int id, String nomComplet, String email, int role) throws BeanException {
		super();
		this.id = id;
		if (nomComplet.length() < 4) {
            throw new BeanException("Le nom est trop petit ! (4 caractères minimum)");
        }
        else {
            this.nomComplet = nomComplet; 
        }
		this.email = email;
		this.role = role;
	}
}
