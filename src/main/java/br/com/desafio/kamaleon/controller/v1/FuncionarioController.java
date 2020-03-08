package br.com.desafio.kamaleon.controller.v1;

import br.com.desafio.kamaleon.model.Funcionario;
import br.com.desafio.kamaleon.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "v1")
public class FuncionarioController {

    @Autowired
    private FuncionarioRepository repository;

    @GetMapping(value = "funcionarios")
    private ResponseEntity getAll(@RequestParam(value = "nome", required = false) String nomeSearch) {

        if (nomeSearch != null) {
            final List<Funcionario> funcionariosStartsWithNomeSearch =
                    repository.getAllFuncionariosNomeStartsWith(nomeSearch);

            if (funcionariosStartsWithNomeSearch != null
                    && (!funcionariosStartsWithNomeSearch.isEmpty())) {

                return ResponseEntity
                        .ok(funcionariosStartsWithNomeSearch);
            }
        }

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
    public ResponseEntity save(@Valid @RequestBody Funcionario funcionario) {

        Funcionario saved = repository.save(funcionario);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.getId()).toUri();

        return ResponseEntity
                .created(location)
                .build();
    }

    @PutMapping(value = "funcionario/{id}")
    public ResponseEntity edit(@PathVariable("id") Long idFuncionario,
                               @Valid @RequestBody Funcionario funcionario) {

        if (idFuncionario == null || funcionario == null || idFuncionario < 1) {
            return ResponseEntity
                    .badRequest()
                    .build();
        }

        Funcionario found = repository.getOne(idFuncionario);

        if (funcionario == null) {
            return ResponseEntity
                    .badRequest()
                    .build();
        }

        found.setNome(funcionario.getNome());
        found.setSalario(funcionario.getSalario());
        found.setAdmitidoEm(funcionario.getAdmitidoEm());

        found = repository.save(found);

        return ResponseEntity.ok(found);
    }

    @DeleteMapping(value = "funcionario/{id}")
    public ResponseEntity delete(@PathVariable("id") Long idFuncionario) {

        if (idFuncionario == null || idFuncionario < 1) {
            return ResponseEntity
                    .badRequest()
                    .build();
        }

        repository.deleteById(idFuncionario);

        return ResponseEntity
                .ok()
                .build();
    }

}
