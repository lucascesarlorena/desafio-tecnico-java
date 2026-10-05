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

        Produto caneta = registro.estoque().get(0);
        System.out.println("Descrição: " + caneta.getDescricaoProduto() + ", Estoque: " + caneta.getEstoque());

        caneta.darEntrada(20);
        System.out.println("Depois da entrada de 20: " + caneta.getEstoque());

        caneta.darEntrada(50);
        System.out.println("Depois da entrada de 50: " + caneta.getEstoque());

        caneta.darSaida(1000);
        System.out.println("Depois da saída de 1000: " + caneta.getEstoque());
    }
}
