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

## Execução do Projeto:

* **Ambiente Local:**
    - realizar o download `.zip` ou clone deste repositório;
    - navegar até o diretório desta aplicação;
    - alterar o arquivo `application-local.properties` colocando os valores do datasource referente ao seu banco de dados postgresql;
    - Executar no diretório da aplicação o seguinte comando:  
        `gradle :bootRun -Dspring.profiles.active=local`.

    **Observação:** Necessário necessário possuir `java` e `gradle` configurados; Não é necessário executar o script de criação do *schema*.

* **Docker Compose:**
    - realizar o download `.zip` ou clone deste repositório;
    - Executar no diretório da aplicação o seguinte comando:  
        `docker-compose up -d` ou `sudo docker-compose up -d`.

    **Observação:** Necessário possuir `docker` e `docker-compose` instalados.

## Endpoints da API:

Os endpoints implementados são:

* **GET /api/v1/funcionarios** - Responsável por mostrar todos os funcionários cadastrados. No caso de não houverem dados.

* **GET /api/v1/funcionarios?nome={nomeSearch}** - Responsável por mostrar todos os funcionários cadastrados com nomes iniciados pelo atributo `nomeSearch`. Caso não encontre nenhum *match*, retorna a lista completa de funcionários. 

* **GET /api/v1/funcionario/{id}** - Responsável por trazer o funcionário cadastrado com atributo `id` correspondente ao enviado na requisição.

* **POST /api/v1/funcionario** - Responsável por salvar os dados de um funcionário. A requisição deve conter um JSON com os dados enviados por meio do RequestBody e retorna uma URI no header com a localização do recurso criado.

    Modelo de JSON:
   ```
   {
       "nome": "Caroline Joana Sebastiana Dias",
       "cpf": "620.278.300-14",
       "salario": "1.256,36",
       "admitidoEm": "10/11/2017"
   }
   ```
  
* **PUT /api/v1/funcionario/{id}** - Responsável por alterar os dados de um funcionário, a requisição deve conter um JSON com os dados enviados por meio do RequestBody e um `id ` correspondente ao funcionário a ser atualizado. Retorna um JSON com os dados atualizados do funcionário.

    Modelo de JSON:
   ```
   {
       "nome": "Caroline Joana Sebastiana Dias",
       "cpf": "620.278.300-14",
       "salario": "1.256,36",
       "admitidoEm": "10/11/2017"
   }
   ```    
<br>

* **DELETE /api/v1/funcionario/{id}** - Responsável por remover o funcionário cadastrado com atributo `id` correspondente ao enviado na requisição. Retorna um OK caso a remoção seja efetuada com sucesso.

#### Referências

- [Why You Should Never Use Float and Double for Monetary Calculations
](https://dzone.com/articles/never-use-float-and-double-for-monetary-calculatio)
- [Conheça a nova API de datas do Java 8 - Caelum](https://blog.caelum.com.br/conheca-a-nova-api-de-datas-do-java-8/)

