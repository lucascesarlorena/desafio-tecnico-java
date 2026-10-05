package br.com.lucas.juros;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class JurosApp {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Calculadora de Juros");

        System.out.println("Valor Inicial: ");
        BigDecimal valorInicial = new BigDecimal(scanner.nextLine().replace(",", "."));

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println("Data de Vencimento (dd/MM/yyyy): ");
        LocalDate dataVencimento = LocalDate.parse(scanner.nextLine(), formato);

        System.out.println("Data lida: " + dataVencimento);


    }
}
