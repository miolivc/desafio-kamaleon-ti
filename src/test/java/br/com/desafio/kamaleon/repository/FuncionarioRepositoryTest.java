package br.com.desafio.kamaleon.repository;

import br.com.desafio.kamaleon.model.Funcionario;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;

@DataJpaTest
@ExtendWith(SpringExtension.class)
public class FuncionarioRepositoryTest {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
     private EntityManager entityManager;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Test
    public void testDataSourceNotNull(){
        assertThat(dataSource, notNullValue());
    }

    @Test
    public void testJDBCTemplateNotNull() {
        assertThat(jdbcTemplate, notNullValue());
    }

    @Test
    public void testEntityManagerNotNull() {
        assertThat(entityManager, notNullValue());
    }

    @Test
    public void testFuncionarioRepositoryNotNull() {
        assertThat(funcionarioRepository, notNullValue());
    }

    @Test
    public void testListFuncionarios() {
        assertThat(funcionarioRepository.findAll(), notNullValue());
    }

    @Test
    public void testSaveFuncionario() {
        Funcionario funcionario = new Funcionario();
        funcionario.setNome("João da Silva");
        funcionario.setCpf("357.775.592-04");
        funcionario.setSalario(new BigDecimal("2456.78"));
        funcionario.setAdmitidoEm(LocalDate.now());
        assertThat(funcionarioRepository.save(funcionario), notNullValue());
    }

    @Test
    @Sql(scripts = {"classpath:data.sql"})
    public void testListStartWithFuncionario() {
        List<Funcionario> funcionarios = funcionarioRepository.getAllFuncionariosNomeStartsWith("C");
        Assertions.assertFalse(funcionarios.isEmpty());
    }

}
