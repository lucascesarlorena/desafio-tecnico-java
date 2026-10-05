package br.com.lucas.estoque;

public record Movimentacao(int id, int codigoProduto, TipoMovimentacao tipo, String descricao, int quantidade) {
    public Movimentacao{
        if(quantidade <= 0){
            throw new IllegalArgumentException("A quantidade deve ser maior que zero.");
        }
        if (descricao == null || descricao.isBlank()){
            throw new IllegalArgumentException("A descrição não pode ser nula ou vazia.");
        }
    }
}
