package br.edu.ifpb.cinema.model;

import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

public class Diretor {
    private Integer idDiretor;
    private String nome;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataNascimento;
    
    private String email;
    private String telefone;
    private Integer idNacionalidade;

    public Diretor() {}

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

    public Integer getIdNacionalidade() { return idNacionalidade; }
    public void setIdNacionalidade(Integer idNacionalidade) { this.idNacionalidade = idNacionalidade; }
}