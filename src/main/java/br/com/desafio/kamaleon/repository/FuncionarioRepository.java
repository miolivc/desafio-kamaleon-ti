package br.com.desafio.kamaleon.repository;

import br.com.desafio.kamaleon.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

    @Query(value = "SELECT f FROM Funcionario f WHERE UPPER(f.nome) LIKE CONCAT(UPPER(:nomeSearch), '%')")
    List<Funcionario> getAllFuncionariosNomeStartsWith(@Param("nomeSearch") String nomeSearch);

}
