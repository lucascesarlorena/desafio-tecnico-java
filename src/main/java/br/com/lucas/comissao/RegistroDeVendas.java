package br.com.lucas.comissao;

import java.util.List;

public record RegistroDeVendas(List<Venda> vendas) {
    public RegistroDeVendas {
        if (vendas == null || vendas.isEmpty()) {
            throw new IllegalArgumentException("Lista de vendas não pode ser nula ou vazia");
        }
    }
}
