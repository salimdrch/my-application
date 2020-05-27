package com.example.jeux;

public class JoueurData {
    private int idJoueur;
    private String name;
    private String firstname;
    private String mdp;

    public JoueurData(int idJoueur, String name, String firstname, String mdp) {
        this.idJoueur = idJoueur;
        this.name = name;
        this.firstname = firstname;
        this.mdp = mdp;
    }

    public int getIdJoueur() {
        return idJoueur;
    }

    public void setIdJoueur(int idJoueur) {
        this.idJoueur = idJoueur;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    @Override
    public String toString() {
        return "JoueurData{" +
                "idJoueur=" + idJoueur +
                ", name='" + name + '\'' +
                ", firstname='" + firstname + '\'' +
                ", mdp='" + mdp + '\'' +
                '}';
    }
}
