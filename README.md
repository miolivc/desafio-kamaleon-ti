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
    "nome": "Michelle Oliveira da Costa",
    "salario": "2.456,78",
    "admitidoEm": "09/03/2020"
}
```

Para a entidade, o atributo do tipo flutuante foi utilizada a representação `java.math.BigDecimal` por tratar-se de uma representação que define cautelosamente a precisão dos valores, evitando eventuais problemas futuros.

Para a representação da data foi utilizado a `java.time.LocalDate` por tratar-se de uma representação focada em humanos, levando em consideração diferenças culturais na interpretação de datas.
<br>

#### Referências

* [Why You Should Never Use Float and Double for Monetary Calculations
](https://dzone.com/articles/never-use-float-and-double-for-monetary-calculatio)
* [Conheça a nova API de datas do Java 8 - Caelum](https://blog.caelum.com.br/conheca-a-nova-api-de-datas-do-java-8/)

