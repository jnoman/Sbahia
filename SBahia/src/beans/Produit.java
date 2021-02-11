package beans;


public class Produit {
	int id;
	String nom;
	String description;
	byte[] image;
	int quantite;
	float prix;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	public int getQuantite() {
		return quantite;
	}
	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
	public float getPrix() {
		return prix;
	}
	public void setPrix(float prix) {
		this.prix = prix;
	}
	public Produit(int id, String nom, String description, byte[] image, int quantite, float prix) throws BeanException {
		super();
		this.id = id;
		if (nom.length() < 4) {
            throw new BeanException("Le nom est trop petit ! (4 caractères minimum)");
        }
        else {
            this.nom = nom; 
        }
		if (description.length() < 20) {
            throw new BeanException("La description est trop petite ! (20 caractères minimum)");
        }
        else {
    		this.description = description;
        }
		this.image = image;
		if (quantite > 100 || quantite < 0) {
            throw new BeanException("la quantité doit être comprise entre 0 et 100");
        }
        else {
    		this.quantite = quantite;
        }
		if (prix < 1) {
            throw new BeanException("le prix doit être superieur a 1");
        }
        else {
    		this.prix = prix;
        }
	}
	public Produit(String nom, String description, byte[] image, int quantite, float prix) throws BeanException {
		super();
		if (nom.length() < 4) {
            throw new BeanException("Le nom est trop petit ! (4 caractères minimum)");
        }
        else {
            this.nom = nom; 
        }
		if (description.length() < 20) {
            throw new BeanException("La description est trop petite ! (20 caractères minimum)");
        }
        else {
    		this.description = description;
        }
		this.image = image;
		if (quantite > 100 || quantite < 0) {
            throw new BeanException("la quantité doit être comprise entre 0 et 100");
        }
        else {
    		this.quantite = quantite;
        }
		if (prix < 1) {
            throw new BeanException("le prix doit être superieur a 1");
        }
        else {
    		this.prix = prix;
        }
	}
	
}
