package br.com.lucas.comissao;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;

import java.math.RoundingMode;
import java.util.LinkedHashMap;
import java.util.Map;

public class ComissaoApp {
    public static void main(String[] args) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();

        InputStream arquivoVendas = ComissaoApp.class.getResourceAsStream("/vendas.json");

        RegistroDeVendas registro = objectMapper.readValue(arquivoVendas, RegistroDeVendas.class);

        System.out.println(registro.vendas().size());

        Map<String, BigDecimal> comissoesPorVendedor = new LinkedHashMap<>();

        for(Venda venda : registro.vendas()) {

            BigDecimal comissao = CalculadoraDeComissao.calcular(venda);

            BigDecimal totalAtual = comissoesPorVendedor.getOrDefault(venda.vendedor(), BigDecimal.ZERO);

            BigDecimal novoTotal = totalAtual.add(comissao);

            comissoesPorVendedor.put(venda.vendedor(), novoTotal);

        }
        for (String vendedor : comissoesPorVendedor.keySet()){
            BigDecimal total = comissoesPorVendedor.get(vendedor).setScale(2, RoundingMode.HALF_UP);
            System.out.println("Vendedor: " + vendedor + ", Comissão total: " + total);
        }

    }
}
