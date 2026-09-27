package br.com.pontocerto.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Classe Abstrata base para todos os colaboradores da empresa (Pilar: Abstração).
 * Possui Composição com RegistroPonto (as marcações nascem e morrem com o Colaborador).
 */
public abstract class Colaborador {
    private String matricula;
    private String nome;
    private double salarioBase;
    private Cargo cargo;
    private List<RegistroPonto> pontos;

    public Colaborador(String matricula, String nome, double salarioBase, Cargo cargo) {
        if (matricula == null || matricula.isBlank()) {
            throw new IllegalArgumentException("Matrícula não pode ser vazia.");
        }
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome não pode ser vazio.");
        }
        if (salarioBase < 0) {
            throw new IllegalArgumentException("Salário base não pode ser negativo.");
        }
        this.matricula = matricula;
        this.nome = nome;
        this.salarioBase = salarioBase;
        this.cargo = cargo;
        this.pontos = new ArrayList<>();
    }

    /**
     * Operação de Composição: Registra a entrada de um novo ponto.
     */
    public RegistroPonto registrarPonto(LocalDate data, LocalTime entrada) {
        RegistroPonto novoPonto = new RegistroPonto(data, entrada);
        this.pontos.add(novoPonto);
        return novoPonto;
    }

    /**
     * Localiza o ponto da data e registra a saída correspondente.
     */
    public void fecharPonto(LocalDate data, LocalTime saida) {
        for (RegistroPonto ponto : pontos) {
            if (ponto.getData().equals(data) && ponto.getStatus() == StatusPonto.ABERTO) {
                ponto.registrarSaida(saida);
                return;
            }
        }
        throw new IllegalStateException("Nenhum ponto em aberto encontrado para a data: " + data);
    }

    /**
     * Método Abstrato Polimórfico: Cada tipo de colaborador calcula o adicional de acordo com sua regra.
     * (Pilar: Polimorfismo)
     */
    public abstract double calcularAdicional(double horasExtras);

    /**
     * Método Abstrato: Retorna a jornada diária padrão (horas) do colaborador.
     */
    public abstract int getCargaHorariaDiaria();

    /**
     * Calcula o valor estimado da hora de trabalho baseado em 220h/mês (ou 132h para estágio).
     */
    public double getValorHora() {
        int horasMensais = getCargaHorariaDiaria() * 22; // média de 22 dias úteis
        return horasMensais > 0 ? (salarioBase / horasMensais) : 0.0;
    }

    public String getMatricula() {
        return matricula;
    }

    public String getNome() {
        return nome;
    }

    public double getSalarioBase() {
        return salarioBase;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public List<RegistroPonto> getPontos() {
        return Collections.unmodifiableList(pontos);
    }

    @Override
    public String toString() {
        return "[" + matricula + "] " + nome + " | Cargo: " + (cargo != null ? cargo.getTitulo() : "Sem cargo") 
                + " | Salário Base: R$ " + String.format("%.2f", salarioBase);
    }
}
