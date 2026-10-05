package br.com.lucas.comissao;

import java.math.BigDecimal;

public class CalculadoraDeComissao {

    public static BigDecimal calcular(Venda venda) {
        BigDecimal valor = venda.valor();

        if(valor.compareTo((new BigDecimal(("100")))) < 0) {
            return BigDecimal.ZERO;

        }else if(valor.compareTo((new BigDecimal(("500")))) < 0) {
            return valor.multiply(new BigDecimal("0.01"));

        } else {
            return valor.multiply(new BigDecimal("0.05"));
        }
    }
}

