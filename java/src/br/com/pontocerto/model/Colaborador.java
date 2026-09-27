package br.com.pontocerto.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public abstract class Colaborador {
    private String matricula;
    private String nome;
    private double salarioBase;
    private Cargo cargo;
    private List<RegistroPonto> pontos;

    public Colaborador(String matricula, String nome, double salarioBase, Cargo cargo) {
        this.matricula = matricula;
        this.nome = nome;
        this.salarioBase = salarioBase;
        this.cargo = cargo;
        this.pontos = new ArrayList<>();
    }

    public RegistroPonto registrarPonto(LocalDate data, LocalTime entrada) {
        RegistroPonto ponto = new RegistroPonto(data, entrada);
        this.pontos.add(ponto);
        return ponto;
    }

    public void fecharPonto(LocalDate data, LocalTime saida) {
        for (RegistroPonto ponto : pontos) {
            if (ponto.getData().equals(data) && ponto.getStatus() == StatusPonto.ABERTO) {
                ponto.registrarSaida(saida);
                return;
            }
        }
    }

    public abstract double calcularAdicional(double horasExtras);

    public abstract int getCargaHorariaDiaria();

    public double getValorHora() {
        int horasMensais = getCargaHorariaDiaria() * 22;
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
        return pontos;
    }

    @Override
    public String toString() {
        return matricula + " - " + nome + " (" + (cargo != null ? cargo.getTitulo() : "Sem cargo") + ")";
    }
}
