# Desafio Técnico - Desenvolvedor de Sistemas Jr.

Solução dos três exercícios do desafio técnico, em Java. Cada exercício é um programa de console independente, dentro do mesmo projeto Maven.

| Exercício | O que faz | Classe para executar |
| --- | --- | --- |
| 1. Comissão | Lê as vendas de um JSON e calcula a comissão de cada vendedor | `br.com.lucas.comissao.ComissaoApp` |
| 2. Estoque | Lança movimentações de entrada e saída e informa o estoque final do produto | `br.com.lucas.estoque.EstoqueApp` |
| 3. Juros | Calcula os juros de um valor vencido, na data de hoje | `br.com.lucas.juros.JurosApp` |

## Tecnologias

- Java 21
- Maven
- Jackson (`jackson-databind`), para a leitura dos arquivos JSON

## Como executar

Pré-requisitos: JDK 21 e Maven instalados.

### Pelo IntelliJ IDEA

1. Abra a pasta do projeto. O IntelliJ reconhece o `pom.xml` e baixa as dependências.
2. Abra a classe do exercício desejado (`ComissaoApp`, `EstoqueApp` ou `JurosApp`).
3. Clique na seta verde ao lado do método `main`.

### Pelo terminal

Na raiz do projeto:

```
mvn compile

mvn exec:java "-Dexec.mainClass=br.com.lucas.comissao.ComissaoApp"
mvn exec:java "-Dexec.mainClass=br.com.lucas.estoque.EstoqueApp"
mvn exec:java "-Dexec.mainClass=br.com.lucas.juros.JurosApp"
```

## Estrutura do projeto

```
src/main
├── java/br/com/lucas
│   ├── comissao
│   │   ├── ComissaoApp.java             ponto de entrada do exercício 1
│   │   ├── CalculadoraDeComissao.java   regra de comissão de uma venda
│   │   ├── Venda.java                   record: uma venda do JSON
│   │   └── RegistroDeVendas.java        record: o arquivo de vendas
│   ├── estoque
│   │   ├── EstoqueApp.java              ponto de entrada do exercício 2
│   │   ├── ControleDeEstoque.java       lança as movimentações
│   │   ├── Produto.java                 produto e suas regras de entrada e saída
│   │   ├── Movimentacao.java            record: uma movimentação lançada
│   │   ├── TipoMovimentacao.java        enum: ENTRADA ou SAIDA
│   │   └── RegistroDeEstoque.java       record: o arquivo de estoque
│   └── juros
│       ├── JurosApp.java                ponto de entrada do exercício 3
│       └── CalculadoraDeJuros.java      regra de cálculo dos juros
└── resources
    ├── vendas.json
    └── estoque.json
```

Em cada pacote, a classe `...App` cuida da entrada e da saída no console, e as demais classes concentram a regra de negócio.

## Exercício 1 - Comissão de vendedores

O programa lê o `vendas.json`, aplica a regra de comissão em cada venda e soma o resultado por vendedor.

| Valor da venda | Comissão |
| --- | --- |
| Abaixo de R$ 100,00 | Não gera comissão |
| De R$ 100,00 a R$ 499,99 | 1% |
| A partir de R$ 500,00 | 5% |

Saída do programa:

```
Vendedor: João Silva, Comissão total: 495.68
Vendedor: Maria Souza, Comissão total: 465.95
Vendedor: Carlos Oliveira, Comissão total: 379.37
Vendedor: Ana Lima, Comissão total: 404.98
```

Decisões:

- **Limites das faixas.** Uma venda de exatamente R$ 100,00 não está "abaixo de R$ 100,00", então recebe 1%. Uma venda de exatamente R$ 500,00 está "a partir de R$ 500,00", então recebe 5%.
- **`BigDecimal` para valores em dinheiro.** O tipo `double` tem erro de arredondamento em casas decimais, o que poderia alterar o total em centavos.
- **Arredondamento só na exibição.** Os cálculos são feitos com precisão total, e o valor é arredondado para duas casas (`HALF_UP`) apenas ao ser mostrado.
- **Records para os dados do JSON.** `Venda` e `RegistroDeVendas` são imutáveis, porque uma venda lida do arquivo não muda. Os dados são validados na criação do objeto.
- **Ordem dos vendedores.** O resultado segue a ordem em que os vendedores aparecem no arquivo (`LinkedHashMap`).

## Exercício 2 - Movimentação de estoque

O programa carrega os produtos do `estoque.json`, mostra a lista e permite lançar movimentações pelo teclado. Cada movimentação recebe um número identificador único e uma descrição. Ao final de cada lançamento, o programa informa a quantidade final em estoque do produto movimentado.

Exemplo de uso:

```
5 produtos no estoque
Produto: Caneta Azul - Código: 101 - Estoque: 150
Produto: Caderno Universitário - Código: 102 - Estoque: 75
...
Digite o código do produto:
101
Digite o tipo de movimentação (ENTRADA ou SAIDA):
entrada
Digite a descrição da movimentação:
Compra de fornecedor
Digite a quantidade:
20
Quantidade final do produto 101: 170
Deseja lançar outra movimentação? (S/N)
```

Decisões:

- **Identificador único.** É um número sequencial, iniciado em 1. Ele só avança quando a movimentação é concluída, para a numeração não ter falhas.
- **Saída maior que o estoque.** A movimentação é recusada com uma mensagem, e o estoque não é alterado. O estoque nunca fica negativo.
- **Estoque em memória.** O enunciado pede o retorno da quantidade final, então as alterações valem durante a execução e o `estoque.json` não é regravado.
- **`Produto` como classe e `Movimentacao` como record.** O produto muda a cada movimentação. A movimentação é o registro de algo que aconteceu e não muda depois de criada.
- **`enum` para o tipo.** `TipoMovimentacao` aceita somente `ENTRADA` e `SAIDA`.
- **Regra no próprio produto.** Os métodos `darEntrada` e `darSaida` ficam na classe `Produto`, que é responsável pelo seu estoque.

Entradas inválidas são tratadas sem encerrar o programa:

| Situação | Comportamento |
| --- | --- |
| Texto no lugar de número | Informa que código e quantidade devem ser números inteiros |
| Tipo diferente de ENTRADA ou SAIDA | Informa os valores aceitos |
| Código de produto inexistente | Informa que o produto não foi encontrado |
| Quantidade zero ou negativa | Recusa a movimentação |
| Descrição vazia | Recusa a movimentação |
| Saída maior que o estoque | Recusa e informa o estoque atual |

## Exercício 3 - Cálculo de juros

O programa recebe um valor e uma data de vencimento e calcula os juros devidos na data de hoje, com taxa de 2,5% ao dia.

Exemplo de uso, executado em 05/10/2026:

```
Calculadora de Juros
Valor Inicial:
1000
Data de Vencimento (dd/MM/yyyy):
25/09/2026
Juros R$: 250.00
```

Decisões:

- **Juros simples.** O enunciado não especifica o regime. Considerei juros simples, por se tratar de uma multa por dia de atraso: `juros = valor × 0,025 × dias de atraso`.
- **Sem atraso, sem juros.** Se o vencimento é hoje ou uma data futura, o resultado é zero.
- **Data de hoje.** É obtida do relógio do sistema (`LocalDate.now()`). A classe `CalculadoraDeJuros` recebe a data como parâmetro, o que permite testar o cálculo com datas fixas.
- **Retorno.** O programa informa o valor dos juros, e não o total da dívida.
- **Entrada.** O valor aceita vírgula ou ponto como separador decimal. A data é informada no formato `dd/MM/yyyy`.

Entradas inválidas são tratadas com mensagem: valor que não é número, data fora do formato e valor negativo.

## Melhorias possíveis

- Testes automatizados com JUnit para as regras de comissão, de estoque e de juros.
- Gravação das movimentações e do estoque atualizado em arquivo.
- Validação rígida de datas inexistentes, como 31/02.