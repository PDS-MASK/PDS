package indicateur;

public class Emplacement {
private String nom;
private String surface;
public Emplacement (String n,String s) {
	nom = n;
	surface =s;
}
public String getNom() {
	return nom;
}
public void setNom(String nom) {
	this.nom = nom;
}
public String getSurface() {
	return surface;
}
public void setSurface(String surface) {
	this.surface = surface;
}

}
