package br.edu.ifpb.cinema.dto;

import java.time.LocalDate;

public class DiretorDTO {
    private Integer idDiretor;
    private String nome;
    private LocalDate dataNascimento;
    private String email;
    private String telefone;
    private String nomeNacionalidade;

    public DiretorDTO() {}

    public Integer getIdDiretor() { return idDiretor; }
    public void setIdDiretor(Integer idDiretor) { this.idDiretor = idDiretor; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public LocalDate getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(LocalDate dataNascimento) { this.dataNascimento = dataNascimento; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public String getNomeNacionalidade() { return nomeNacionalidade; }
    public void setNomeNacionalidade(String nomeNacionalidade) { this.nomeNacionalidade = nomeNacionalidade; }
}