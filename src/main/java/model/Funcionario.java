package model;

import utils.Formatador;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Funcionario extends Pessoa {
    private BigDecimal salario;
    private String funcao;

    public Funcionario(String nome, LocalDate dataNascimento, BigDecimal salario, String funcao) {
        super(nome, dataNascimento);
        this.salario = salario;
        this.funcao = funcao;
    }

    public String getNome() { return nome; }
    public LocalDate getDataNascimento() { return dataNascimento; }
    public BigDecimal getSalario() { return salario; }
    public String getFuncao() { return funcao; }

    public void aumentarSalario(BigDecimal percentual) {
        this.salario = this.salario.add(this.salario.multiply(percentual));
    }


    @Override
    public String toString() {
        return String.format(
                "Nome: %s, Data de Nascimento: %s, Salário: %s, Função: %s",
                nome,
                Formatador.formatarData(dataNascimento),
                Formatador.formatarMoeda(salario),
                funcao
        );
    }
}