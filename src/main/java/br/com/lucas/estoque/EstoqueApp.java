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

        Scanner scanner = new Scanner(System.in);
        String continuar;

        do {
            try {
                for (Produto produto : registro.estoque()) {
                    System.out.println("Produto: " + produto.getDescricaoProduto() + " - Código: " + produto.getCodigoProduto() + " - Estoque: " + produto.getEstoque());
                }
                System.out.println("Digite o código do produto:");
                int codigoProduto = Integer.parseInt(scanner.nextLine());

                System.out.println("Digite o tipo de movimentação (ENTRADA ou SAIDA):");
                String tipoMovimentacaoStr = scanner.nextLine().toUpperCase();

                System.out.println("Digite a descrição da movimentação:");
                String descricao = scanner.nextLine();

                System.out.println("Digite a quantidade:");
                int quantidade = Integer.parseInt(scanner.nextLine());

                if(!tipoMovimentacaoStr.equals("ENTRADA") && !tipoMovimentacaoStr.equals("SAIDA")) {
                    throw new IllegalArgumentException("Tipo de movimentação inválido. Use 'ENTRADA' ou 'SAIDA'.");
                }

                TipoMovimentacao tipo = TipoMovimentacao.valueOf(tipoMovimentacaoStr);
                int quantidadeFinal = controle.lancar(codigoProduto, tipo, descricao, quantidade);
                System.out.println("Quantidade final do produto " + codigoProduto + ": " + quantidadeFinal);

            } catch (NumberFormatException e) {
                System.err.println("Entrada inválida: o código e a quantidade devem ser números inteiros.");

            } catch (IllegalArgumentException e) {
                System.err.println("Não foi possível lançar a movimentação: " + e.getMessage());
            }

            System.out.println("Deseja lançar outra movimentação? (S/N)");
            continuar = scanner.nextLine().trim().toUpperCase();

        }while (continuar.equals("S"));
    }
}
