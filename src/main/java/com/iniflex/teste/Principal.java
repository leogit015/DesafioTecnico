package com.iniflex.teste;

import model.Funcionario;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import service.FuncionarioService;
import utils.Formatador;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

@SpringBootApplication
public class Principal {
    public static void main(String[] args) {
        List<Funcionario> funcionarios = new ArrayList<>(Arrays.asList(
                new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"),
                new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"),
                new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"),
                new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"),
                new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"),
                new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"),
                new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"),
                new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"),
                new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"),
                new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente")
        ));


        funcionarios.removeIf(f -> f.getNome().equals("João"));

        funcionarios.forEach(System.out::println);

        // Não consegui atualizar a lista com o salário dos funcionários aumentado em 10% conforme o requesito 3.4


        Map<String, List<Funcionario>> funcionariosPorFuncao = funcionarios.stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncao));
        funcionariosPorFuncao.forEach((funcao, lista) -> {
            System.out.println("\n" + funcao);
            lista.forEach(System.out::println);
        });


        System.out.println("\nAniversariantes de Outubro e Dezembro:");
        funcionarios.stream()
                .filter(f -> f.getDataNascimento().getMonth() == Month.OCTOBER || f.getDataNascimento().getMonth() == Month.DECEMBER)
                .forEach(System.out::println);


        Funcionario maisVelho = funcionarios.stream()
                .min(Comparator.comparing(Funcionario::getDataNascimento))
                .orElse(null);
        if (maisVelho != null) {
            System.out.printf("\nFuncionário mais velho: %s, Idade: %d anos\n",
                    maisVelho.getNome(),
                    LocalDate.now().getYear() - maisVelho.getDataNascimento().getYear()
            );
        }


        System.out.println("\nFuncionários em ordem alfabética:");
        funcionarios.stream()
                .sorted(Comparator.comparing(Funcionario::getNome))
                .forEach(System.out::println);


        BigDecimal totalSalarios = funcionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("\nTotal dos salários: " + Formatador.formatarMoeda(totalSalarios));


        BigDecimal salarioMinimo = new BigDecimal("1212.00");
        System.out.println("\nSalários mínimos por funcionário:");
        funcionarios.forEach(f -> {
            BigDecimal qtdSalarios = f.getSalario().divide(salarioMinimo, 2, BigDecimal.ROUND_HALF_UP);
            System.out.printf("%s ganha %.2f salários mínimos\n", f.getNome(), qtdSalarios);
        });
    }
}
