package br.com.lucas.comissao;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;

public class ComissaoApp {
    public static void main(String[] args) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();

        InputStream arquivoVendas = ComissaoApp.class.getResourceAsStream("/vendas.json");

        RegistroDeVendas registro = objectMapper.readValue(arquivoVendas, RegistroDeVendas.class);

        System.out.println(registro.vendas().size());

        System.out.println(CalculadoraDeComissao.calcular(new Venda("Lucas", new BigDecimal(("80.00")))));
        System.out.println(CalculadoraDeComissao.calcular(new Venda("LucasTest1", new BigDecimal(("300.00")))));
        System.out.println(CalculadoraDeComissao.calcular(new Venda("LucasTest2", new BigDecimal(("1200.00")))));

        System.out.println(CalculadoraDeComissao.calcular(new Venda("LucasTest4", new BigDecimal(("100.00")))));
        System.out.println(CalculadoraDeComissao.calcular(new Venda("LucasTest5", new BigDecimal(("500.00")))));

    }
}
