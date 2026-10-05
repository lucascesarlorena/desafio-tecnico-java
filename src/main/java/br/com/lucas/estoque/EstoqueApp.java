package br.com.lucas.estoque;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class EstoqueApp {

    public static void main(String[] args) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();

        InputStream arquivoEstoque = EstoqueApp.class.getResourceAsStream("/estoque.json");
        RegistroDeEstoque registro = objectMapper.readValue(arquivoEstoque, RegistroDeEstoque.class);

        System.out.println(registro.estoque().size() + " produtos no estoque");

        ControleDeEstoque controle = new ControleDeEstoque(registro.estoque());

        for (Produto produto : registro.estoque()) {
            System.out.println("Produto: " + produto.getDescricaoProduto() + " - Código: " + produto.getCodigoProduto() + " - Estoque: " + produto.getEstoque());
        }
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o código do produto:");
        int codigoProduto = Integer.parseInt(scanner.nextLine());

        System.out.println("Digite o tipo de movimentação (ENTRADA ou SAIDA):");
        String tipoMovimentacaoStr = scanner.nextLine().toUpperCase();

        System.out.println("Digite a descrição da movimentação:");
        String descricao = scanner.nextLine();

        System.out.println("Digite a quantidade:");
        int quantidade = Integer.parseInt(scanner.nextLine());

        System.out.println("Processando movimentação...");
        System.out.println("Código: " + codigoProduto);
        System.out.println("Tipo: " + tipoMovimentacaoStr);
        System.out.println("Descrição: " + descricao);
        System.out.println("Quantidade: " + quantidade);

        TipoMovimentacao tipo = TipoMovimentacao.valueOf(tipoMovimentacaoStr);
        int quantidadeFinal = controle.lancar(codigoProduto, tipo, descricao, quantidade);
        System.out.println("Quantidade final do produto " + codigoProduto + ": " + quantidadeFinal);


    }
}
