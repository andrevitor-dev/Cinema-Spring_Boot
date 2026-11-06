package br.edu.ifpb.cinema.model;

import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

public class Filme {
    private Integer idFilme;
    private String titulo;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataLancamento;
    
    private String genero;

    public Filme() {}

    public Integer getIdFilme() { return idFilme; }
    public void setIdFilme(Integer idFilme) { this.idFilme = idFilme; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public LocalDate getDataLancamento() { return dataLancamento; }
    public void setDataLancamento(LocalDate dataLancamento) { this.dataLancamento = dataLancamento; }

    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }
}