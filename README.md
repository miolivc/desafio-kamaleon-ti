# Desafio: Kamaleon TI

## Descrição do Cenário e Orientações:

1. Precisamos de um CRUD de alguma entidade que possua obrigatoriamente atributos dos tipos: String, inteiro, ponto flutuante e data;  
<br>

2. Na inserção dos dados, e na atualização, os atributos de ponto flutuante e data devem ser enviados com máscara, ou seja, 1234.56 deve ser enviado como 1.234,56 e a data deve ser enviada no nosso formato (dd/mm/yyyy);  
<br>

3. Na listagem dos dados os campos do item 2 devem vir formatados. Também será possível listar os dados utilizando algum filtro. Por exemplo: kamaleon.com.br/app/list?nome=Fulano, neste caso deve retornar todos os registros do banco de dados onde o nome comece por Fulano. Se não passar nada como filtro, retorna todos os registros.  
<br>
Você deve hospedar seu código no github.com e passar o link do projeto. Lembrar de disponibilizar os códigos SQL para a criação das tabelas e os JSONs para envio de requisições.
<br>

## Entidade:

A entidade escolhida para a descrição do item 1 foi `Funcionario` e esta possui a seguinte representação no formato JSON:

```
{
    "id": 1,
    "nome": "Beatriz Aparecida Cristiane da Silva",
    "cpf": "045.024.969-79",
    "salario": "2.456,78",
    "admitidoEm": "09/03/2020"
}
```

Na entidade, a representação de um atributo inteiro foi utilizada `java.lang.Long` por possuir uma faixa de valores abrangidos maior do que um tipo `int` primitivo ou o wrapper `java.lang.Integer`.

Para o atributo do tipo flutuante foi utilizada a representação `java.math.BigDecimal` por tratar-se de uma representação que define cautelosamente a precisão dos valores, evitando eventuais problemas futuros.

Para a representação da data foi utilizado a `java.time.LocalDate` por tratar-se de uma representação focada em humanos, levando em consideração diferenças culturais na interpretação de datas.
<br>

## Validação dos campos da Entidade:

A entidade `Funcionario` possui em seus atributos as seguintes validações:

* **id**: Atributo possui validação definida pelo Hibernate (JPA) por tratar-se da chave primária da entidade;
* **nome**: Atributo possui validação do tipo ao menos duas Strings separadas por espaço (Nome Sobrenome), não permitindo caracteres especiais;
* **cpf**: Atributo deve ser uma string no formato do CPF brasileiro formatado e válido, além de ser único.
* **salario**: Valor deve aceitar máscara de moeda brasileira, separador de milhares `.` e separador de decimais `,`. Este valor foi definido com uma precisão de até 10 digitos com 2 casas decimais.
* **admitidoEm**: Valor deve ser correspondente ao formato de data brasileiro `dd/MM/yyyy` e deve ser uma data passada ou a atual;
<br>

## Banco de dados: 

O *schema* do banco de dados encontra-se definido da seguinte maneira:

```
CREATE SEQUENCE funcionario_seq;

CREATE TABLE funcionario (
    id BIGINT 
        DEFAULT nextval('funcionario_seq') 
        PRIMARY KEY,
    cpf VARCHAR(14) UNIQUE,
    nome VARCHAR NOT NULL,
    salario NUMERIC(10, 2) NOT NULL,
    admitidoEm DATE NOT NULL 
        CHECK (admitidoEm <= now())
);
```

#### Referências

- [Why You Should Never Use Float and Double for Monetary Calculations
](https://dzone.com/articles/never-use-float-and-double-for-monetary-calculatio)
- [Conheça a nova API de datas do Java 8 - Caelum](https://blog.caelum.com.br/conheca-a-nova-api-de-datas-do-java-8/)

