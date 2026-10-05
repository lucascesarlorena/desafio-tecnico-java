package br.com.lucas.estoque;
import java.util.ArrayList;
import java.util.List;

public class ControleDeEstoque {

    private List<Produto> produtos;
    private List<Movimentacao> movimentacoes = new ArrayList<>();
    private int proximoId = 1;

    public ControleDeEstoque(List<Produto> produtos) {
        this.produtos = produtos;
    }

    private Produto buscarProduto(int codigoProduto) {
        for (Produto produto : produtos) {
            if(produto.getCodigoProduto() == codigoProduto) {
                return produto;
            }
        }
        throw new IllegalArgumentException("Produto não encontrado: " + codigoProduto);
    }

    public int lancar(int codigoProduto, TipoMovimentacao tipo, String descricao, int quantidade) {
        Produto produto = buscarProduto(codigoProduto);

        Movimentacao movimentacao = new Movimentacao(proximoId, codigoProduto ,tipo, descricao, quantidade);

        if(tipo == TipoMovimentacao.ENTRADA) {
            produto.darEntrada(quantidade);
        }else{
            produto.darSaida(quantidade);
        }

        movimentacoes.add(movimentacao);
        proximoId++;

        return produto.getEstoque();

    }
}
