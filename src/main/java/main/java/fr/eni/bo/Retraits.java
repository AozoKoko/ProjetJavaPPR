package main.java.fr.eni.bo;

public class Retraits {

    private int noArticle;
    private  String rue;
    private String codePostal;
    private String ville;


    public Retraits(int noArticle, String rue, String codePostal, String ville) {
        this.noArticle = noArticle;
        this.rue = rue;
        this.codePostal = codePostal;
        this.ville = ville;
    }

    public Retraits(String rue, String codePostal, String ville) {
        this.rue = rue;
        this.codePostal = codePostal;
        this.ville = ville;
    }

    public int getNoArticle() {
        return noArticle;
    }

    public void setNoArticle(int noArticle) {
        this.noArticle = noArticle;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    @Override
    public String toString() {
        return "Retraits{" +
                "noArticle=" + noArticle +
                ", rue='" + rue + '\'' +
                ", codePostal='" + codePostal + '\'' +
                ", ville='" + ville + '\'' +
                '}';
    }
}
