package beans;

public class Likes {
	int idUser, idProduit;
	
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	public int getIdProduit() {
		return idProduit;
	}
	public void setIdProduit(int idProduit) {
		this.idProduit = idProduit;
	}
	public Likes(int idUser, int idProduit, boolean islike) {
		super();
		this.idUser = idUser;
		this.idProduit = idProduit;
	}
}
