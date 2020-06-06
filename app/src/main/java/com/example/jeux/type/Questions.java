package com.example.jeux.type;

import java.util.ArrayList;

public class Questions {
    private int id;
    private String question;
    private ArrayList<Reponse> reponses;

    public Questions(int id, String question, ArrayList<Reponse> reponses) {
        this.id = id;
        this.question = question;
        this.reponses = reponses;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public ArrayList<Reponse> getReponses() {
        return reponses;
    }

    public void setReponses(ArrayList<Reponse> reponses) {
        this.reponses = reponses;
    }

    @Override
    public String toString() {
        return "Questions{" +
                "id=" + id +
                ", question='" + question + '\'' +
                ", reponses=" + reponses +
                '}';
    }
}
