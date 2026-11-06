package br.edu.ifpb.cinema.model;

public class Nacionalidade {
    private Integer idNacionalidade;
    private String nome;

    public Nacionalidade() {}

    public Nacionalidade(Integer idNacionalidade, String nome) {
        this.idNacionalidade = idNacionalidade;
        this.nome = nome;
    }

    public Integer getIdNacionalidade() {
        return idNacionalidade;
    }

    public void setIdNacionalidade(Integer idNacionalidade) {
        this.idNacionalidade = idNacionalidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}