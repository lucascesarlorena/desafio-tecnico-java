package br.com.lucas.comissao;

import java.math.BigDecimal;

public record Venda(String vendedor, BigDecimal valor) {
    public Venda {
        if (vendedor == null || vendedor.isEmpty()) {
            throw new IllegalArgumentException("Vendedor não pode ser nulo ou vazio");
        }
        if (valor == null || valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Valor da venda deve ser positivo");
        }
    }
}
