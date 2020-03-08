package br.com.desafio.kamaleon.model;

import br.com.desafio.kamaleon.json.converter.BigDecimalToStringConverter;
import br.com.desafio.kamaleon.json.converter.StringToBigDecimalConverter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@SequenceGenerator(name = "funcionario_seq", sequenceName = "funcionario_seq")
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "funcionario_seq")
    private Long id;

    @Pattern(
            regexp = "[A-z]+([ ][A-z]+)*",
            message = "O nome do funcionário deve atender ao padrão (nome completo)"
    )
    @NotBlank(message = "O nome do funcionário não pode estar vazio")
    @JsonProperty(required = true)
    private String nome;

    @NotNull(message = "O salário do funcionário não pode ser nulo")
    @Positive(message = "O salário do funcionário não pode ter um valor negativo ou zero")
    @JsonProperty(required = true)
    @JsonSerialize(converter = BigDecimalToStringConverter.class)
    @JsonDeserialize(converter = StringToBigDecimalConverter.class)
    @Column(precision = 10, scale = 2)
    private BigDecimal salario;

    @NotNull(message = "A data de admissão não pode ser nula")
    @PastOrPresent(message = "A data de admissão não pode ser superior ao dia atual")
    @JsonProperty(required = true)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate admitidoEm;

}