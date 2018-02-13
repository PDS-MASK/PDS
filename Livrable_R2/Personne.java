public class Personne {

    private String nom;
    private String prenom;
    private int age;

    /**
     * Constructeur par initialisations.
     */
    public Personne(String nom, String prenom, int age) {
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
    }

    /**
     * Récupère le nom de la personne
     */
    public String getNom() {
        return nom;
    }

    /**
     * Récupère le prénom de la personne.
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Récupère l'âge de la personne.
     */
    public int getAge() {
        return age;
    }

    /**
     * Convertit l'objet courant en chaîne de caractères.
     */
    public String toString() {
        return nom + " " + prenom + " (" + age + "an(s))";
    }

}