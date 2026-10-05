package br.com.lucas.juros;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CalculadoraDeJuros {


    public static BigDecimal calcularJuros(BigDecimal valorInicial, LocalDate dataDeVencimento, LocalDate dataDeHoje){
        long diasDeAtraso = java.time.temporal.ChronoUnit.DAYS.between(dataDeVencimento, dataDeHoje);



        if(diasDeAtraso <= 0){
            return BigDecimal.ZERO;
        } else {
            BigDecimal taxaDeJuros = new BigDecimal("0.025");
            BigDecimal juros = valorInicial.multiply(taxaDeJuros).multiply(new BigDecimal(diasDeAtraso));
            return juros;
        }
    }

}
