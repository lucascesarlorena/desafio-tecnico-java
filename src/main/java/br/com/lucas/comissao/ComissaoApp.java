package br.com.lucas.comissao;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;

public class ComissaoApp {
    public static void main(String[] args) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();

        InputStream arquivoVendas = ComissaoApp.class.getResourceAsStream("/vendas.json");

        RegistroDeVendas registro = objectMapper.readValue(arquivoVendas, RegistroDeVendas.class);

        System.out.println(registro.vendas().size());
    }
}
