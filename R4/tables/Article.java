
package tables;

/**
 *
 * @author ahamdi
 */
public class Article {

    private int id_article;
    private String nom_article;
    private String categorie;
    private int quantite;


    public int getId_Article() {
        return this.id_article;
    }

    public String getNom_Article() {
        return this.nom_article;
    }

    public String getCategorie() {
        return this.categorie;
    }

    public int getQuantite() {
        return this.quantite;
    }


    public void setId_Article(int id_article) {
        this.id_article = id_article;
    }

    public void setNom_Aricle(String nom_article) {
        this.nom_article = nom_article;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
}
