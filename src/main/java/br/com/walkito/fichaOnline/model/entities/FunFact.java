package br.com.walkito.fichaOnline.model.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "fun_facts")
public class FunFact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(columnDefinition = "TEXT")
    private String fact;

    public FunFact() {
    }

    public FunFact(String fact) {
        this.fact = fact;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFact() {
        return fact;
    }

    public void setFact(String fact) {
        this.fact = fact;
    }
}
