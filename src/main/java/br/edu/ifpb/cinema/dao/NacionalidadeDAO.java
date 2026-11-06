package br.edu.ifpb.cinema.dao;

import java.util.List;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import br.edu.ifpb.cinema.model.Nacionalidade;

@Repository 
public class NacionalidadeDAO {

    private final JdbcClient jdbcClient;

    public NacionalidadeDAO(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public List<Nacionalidade> listar() {
        return this.jdbcClient.sql("SELECT id_nacionalidade, nome FROM nacionalidade")
                    .query(Nacionalidade.class).list();
    }

    public Integer salvar(Nacionalidade nacionalidade) {
        return jdbcClient
                .sql("INSERT INTO nacionalidade (nome) VALUES (:nome)")
                .param("nome", nacionalidade.getNome())
                .update();
    }
    
    public void atualizar(Nacionalidade nacionalidade) {
        jdbcClient.sql("UPDATE nacionalidade SET nome = :nome WHERE id_nacionalidade = :id")
                .param("nome", nacionalidade.getNome())
                .param("id", nacionalidade.getIdNacionalidade())
                .update();
    }

    public void deletar(Integer id) {
        jdbcClient.sql("DELETE FROM nacionalidade WHERE id_nacionalidade = :id")
                .param("id", id)
                .update();
    }

    public Nacionalidade buscar(Integer id) {
        return jdbcClient
                .sql("SELECT id_nacionalidade, nome FROM nacionalidade WHERE id_nacionalidade = :id")
                .param("id", id)
                .query(Nacionalidade.class)
                .optional().orElse(null);
    }
}