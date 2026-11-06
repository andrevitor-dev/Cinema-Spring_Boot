package br.edu.ifpb.cinema.dao;

import java.sql.Date;
import java.util.List;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import br.edu.ifpb.cinema.model.Filme;

@Repository 
public class FilmeDAO {

    private final JdbcClient jdbcClient;

    public FilmeDAO(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public List<Filme> listar() {
        return this.jdbcClient.sql("SELECT id_filme, titulo, dataLancamento, genero FROM filme")
                    .query(Filme.class).list();
    }

    public Integer salvar(Filme filme) {
        return jdbcClient
                .sql("INSERT INTO filme (titulo, dataLancamento, genero) VALUES (:titulo, :dataLancamento, :genero)")
                .param("titulo", filme.getTitulo())
                .param("dataLancamento", filme.getDataLancamento() != null ? Date.valueOf(filme.getDataLancamento()) : null)
                .param("genero", filme.getGenero())
                .update();
    }

    public Filme buscarPorId(Integer id) {
        String sql = "SELECT id_filme, titulo, dataLancamento, genero FROM filme WHERE id_filme = :id";
        return jdbcClient.sql(sql)
                .param("id", id)
                .query(Filme.class)
                .optional()
                .orElse(null);
    }

    public void atualizar(Filme filme) {
        String sql = "UPDATE filme SET titulo = :titulo, dataLancamento = :dataLancamento, genero = :genero WHERE id_filme = :idFilme";
        jdbcClient.sql(sql)
                .param("titulo", filme.getTitulo())
                .param("dataLancamento", filme.getDataLancamento() != null ? Date.valueOf(filme.getDataLancamento()) : null)
                .param("genero", filme.getGenero())
                .param("idFilme", filme.getIdFilme())
                .update();
    }

    public void deletar(Integer id) {
        jdbcClient.sql("DELETE FROM filme WHERE id_filme = :id")
                .param("id", id)
                .update();
    }
}