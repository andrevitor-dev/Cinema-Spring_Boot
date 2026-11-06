package br.edu.ifpb.cinema.dao;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import br.edu.ifpb.cinema.dto.DiretorDTO;
import br.edu.ifpb.cinema.model.Diretor;

@Repository 
public class DiretorDAO {

    private final JdbcClient jdbcClient;

    public DiretorDAO(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public List<Diretor> listar() {
        return this.jdbcClient.sql("SELECT id_diretor, nome, data_nascimento, email, telefone, id_nacionalidade FROM diretor")
                    .query(Diretor.class).list();
    }

    public Integer salvar(Diretor diretor) {
        return jdbcClient
                .sql("INSERT INTO diretor (nome, data_nascimento, email, telefone, id_nacionalidade) " +
                     "VALUES (:nome, :dataNascimento, :email, :telefone, :idNacionalidade)")
                .param("nome", diretor.getNome())
                .param("dataNascimento", diretor.getDataNascimento() != null ? Date.valueOf(diretor.getDataNascimento()) : null)
                .param("email", diretor.getEmail())
                .param("telefone", diretor.getTelefone())
                .param("idNacionalidade", diretor.getIdNacionalidade())
                .update();
    }

    public Diretor buscarPorId(Integer id) {
        String sql = "SELECT id_diretor, nome, data_nascimento, email, telefone, id_nacionalidade FROM diretor WHERE id_diretor = :id";
        return jdbcClient.sql(sql)
                .param("id", id)
                .query(Diretor.class)
                .optional()
                .orElse(null);
    }

    public void atualizar(Diretor diretor) {
        String sql = "UPDATE diretor SET nome = :nome, data_nascimento = :dataNascimento, email = :email, telefone = :telefone, id_nacionalidade = :idNacionalidade WHERE id_diretor = :idDiretor";
        jdbcClient.sql(sql)
                .param("nome", diretor.getNome())
                .param("dataNascimento", diretor.getDataNascimento() != null ? Date.valueOf(diretor.getDataNascimento()) : null)
                .param("email", diretor.getEmail())
                .param("telefone", diretor.getTelefone())
                .param("idNacionalidade", diretor.getIdNacionalidade())
                .param("idDiretor", diretor.getIdDiretor())
                .update();
    }

    public void deletar(Integer id) {
        jdbcClient.sql("DELETE FROM diretor WHERE id_diretor = :id")
                .param("id", id)
                .update();
    }

    public List<DiretorDTO> listarDiretoresComNacionalidade() {
        String sql = "SELECT d.id_diretor, d.nome AS nome_diretor, d.data_nascimento, d.email, d.telefone, d.id_nacionalidade, n.nome AS nome_nacionalidade " +
                     "FROM diretor d INNER JOIN nacionalidade n ON d.id_nacionalidade = n.id_nacionalidade";

        return this.jdbcClient.sql(sql).query(rowMapper).list();
    }

    private final RowMapper<DiretorDTO> rowMapper = (rs, rowNum) -> {
        DiretorDTO dto = new DiretorDTO();
        dto.setIdDiretor(rs.getInt("id_diretor"));
        dto.setNome(rs.getString("nome_diretor"));
        dto.setDataNascimento(rs.getObject("data_nascimento", LocalDate.class));
        dto.setEmail(rs.getString("email"));
        dto.setTelefone(rs.getString("telefone"));
        dto.setNomeNacionalidade(rs.getString("nome_nacionalidade"));
        return dto;
    };
}