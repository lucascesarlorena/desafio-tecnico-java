package br.com.lucas.estoque;

public class Produto {

    private int codigoProduto;
    private String descricaoProduto;
    private int estoque;

    public int getCodigoProduto() {
        return codigoProduto;
    }

    public void setCodigoProduto(int codigoProduto) {
        this.codigoProduto = codigoProduto;
    }

    public String getDescricaoProduto() {
        return descricaoProduto;
    }

    public void setDescricaoProduto(String descricaoProduto) {
        this.descricaoProduto = descricaoProduto;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

    public void darEntrada(int quantidade) {
        this.estoque += quantidade;
    }

    public void darSaida(int quantidade) {
        if (quantidade > this.estoque){
            throw new IllegalArgumentException("Não é possível dar saída de " + quantidade + " unidades do produto " + this.descricaoProduto + ". Estoque atual: " + this.estoque);
        }
        this.estoque -= quantidade;
    }
}
