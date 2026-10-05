package br.com.lucas.estoque;

import java.util.List;

public record RegistroDeEstoque(List<Produto> estoque) {
    public RegistroDeEstoque {
        if (estoque == null) {
            throw new IllegalArgumentException("Lista de estoque não pode ser nula");
        }
    }
}
