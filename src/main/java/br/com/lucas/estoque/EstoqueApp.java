package br.com.lucas.estoque;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;

public class EstoqueApp {

    public static void main(String[] args) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();

        InputStream arquivoEstoque = EstoqueApp.class.getResourceAsStream("/estoque.json");
        RegistroDeEstoque registro = objectMapper.readValue(arquivoEstoque, RegistroDeEstoque.class);

        System.out.println(registro.estoque().size() + " produtos no estoque");

        ControleDeEstoque controle = new ControleDeEstoque(registro.estoque());

        int quantidadeFinal = controle.lancar(101, TipoMovimentacao.ENTRADA, "Compra de 20 unidades", 20);
        System.out.println("Quantidade final do produto 101: " + quantidadeFinal);

        int quantidadeFinal2 = controle.lancar(102, TipoMovimentacao.SAIDA, "Venda de 5 unidades", 10);
        System.out.println("Quantidade final do produto 102: " + quantidadeFinal2);

        int quantidadeFinal3 = controle.lancar(102, TipoMovimentacao.SAIDA, "Venda de 1000 unidades", 1000);
        System.out.println("Quantidade final do produto 102: " + quantidadeFinal3);
    }
}
