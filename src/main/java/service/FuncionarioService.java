package service;

import model.Funcionario;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FuncionarioService {
    public static void aumentarSalarios(List<Funcionario> funcionarios, BigDecimal percentual) {
        funcionarios.forEach(f -> f.aumentarSalario(percentual));
    }

    public static Map<String, List<Funcionario>> agruparPorFuncao(List<Funcionario> funcionarios) {
        return funcionarios.stream().collect(Collectors.groupingBy(Funcionario::getFuncao));
    }

    public static Funcionario getMaisVelho(List<Funcionario> funcionarios) {
        return Collections.min(funcionarios, Comparator.comparing(Funcionario::getDataNascimento));
    }
}