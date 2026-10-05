package br.com.lucas.juros;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class JurosApp {

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Calculadora de Juros");
            System.out.println("Valor Inicial: ");
            BigDecimal valorInicial = new BigDecimal(scanner.nextLine().replace(",", "."));

            if(valorInicial.compareTo(BigDecimal.ZERO) < 0){
                throw new IllegalArgumentException("O valor inicial não pode ser negativo.");
            }

            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            System.out.println("Data de Vencimento (dd/MM/yyyy): ");
            LocalDate dataVencimento = LocalDate.parse(scanner.nextLine(), formato);

            LocalDate dataDeHoje = LocalDate.now();

            BigDecimal juros = CalculadoraDeJuros.calcularJuros(valorInicial, dataVencimento, dataDeHoje);
            System.out.println("Juros R$: " + juros.setScale(2, RoundingMode.HALF_UP));

        }catch (NumberFormatException e) {
            System.err.println("Erro valor inválido. Por favor, insira um número válido.");
        }catch (DateTimeParseException e) {
            System.err.println("Erro na data. Por favor, insira uma data válida no formato dd/MM/yyyy.");
        }catch (IllegalArgumentException e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }
}
