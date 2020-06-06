package com.example.jeux.type;

class Reponse {
    private int id;
    private String reponse;
    private int bonneReponse;

    public Reponse(String reponse, int bonneReponse) {
        this.reponse = reponse;
        this.bonneReponse = bonneReponse;
    }

    public Reponse(int id, String reponse, int bonneReponse) {
        this.id = id;
        this.reponse = reponse;
        this.bonneReponse = bonneReponse;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReponse() {
        return reponse;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }

    public int getBonneReponse() {
        return bonneReponse;
    }

    public void setBonneReponse(int bonneReponse) {
        this.bonneReponse = bonneReponse;
    }

    @Override
    public String toString() {
        return "Reponse{" +
                "id=" + id +
                ", reponse='" + reponse + '\'' +
                ", bonneReponse=" + bonneReponse +
                '}';
    }
}
