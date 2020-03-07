package br.com.desafio.kamaleon.controller.v1;

import br.com.desafio.kamaleon.model.Funcionario;
import br.com.desafio.kamaleon.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping(value = "v1")
public class FuncionarioController {

    @Autowired
    private FuncionarioRepository repository;

    @GetMapping(value = "funcionarios")
    private ResponseEntity getAll() {
        final List<Funcionario> funcionarios = repository.findAll();

        if (funcionarios == null || funcionarios.isEmpty()) {
            return ResponseEntity
                    .noContent()
                    .build();
        }

        return ResponseEntity.ok(funcionarios);
    }

    @GetMapping(value = "funcionario/{id}")
    public ResponseEntity getOne(@PathVariable("id") Long idFuncionario) {

        if (idFuncionario == null || idFuncionario < 1) {
            return ResponseEntity
                    .badRequest()
                    .build();
        }

        Optional<Funcionario> funcionario = repository.findById(idFuncionario);

        if (funcionario.isPresent()) {
            return ResponseEntity.ok(funcionario);
        }

        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "funcionario")
    public ResponseEntity save(@RequestBody @Valid Funcionario funcionario) {

        final Funcionario saved = repository.save(funcionario);

//        TODO: Refatorar método para retornar URI do recurso

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

}
