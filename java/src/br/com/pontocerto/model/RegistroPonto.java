package br.com.pontocerto.model;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Representa um Registro de Ponto diário (Composição ◆ com Colaborador).
 * Garante o encapsulamento e a proteção das regras invariantes de negócio.
 */
public class RegistroPonto {
    private LocalDate data;
    private LocalTime entrada;
    private LocalTime saida;
    private StatusPonto status;

    public RegistroPonto(LocalDate data, LocalTime entrada) {
        if (data == null) {
            throw new IllegalArgumentException("A data do ponto não pode ser nula.");
        }
        if (entrada == null) {
            throw new IllegalArgumentException("O horário de entrada não pode ser nulo.");
        }
        this.data = data;
        this.entrada = entrada;
        this.saida = null;
        this.status = StatusPonto.ABERTO;
    }

    /**
     * Registra a saída do colaborador validando a invariante:
     * A saída NÃO pode ocorrer antes da entrada no mesmo dia.
     */
    public void registrarSaida(LocalTime saida) {
        if (saida == null) {
            throw new IllegalArgumentException("Horário de saída não pode ser nulo.");
        }
        if (this.status == StatusPonto.FECHADO) {
            throw new IllegalStateException("O ponto deste dia já está fechado.");
        }
        if (saida.isBefore(this.entrada)) {
            throw new IllegalArgumentException("Regra violada: Horário de saída (" + saida 
                    + ") não pode ser anterior ao horário de entrada (" + this.entrada + ") no mesmo dia.");
        }

        this.saida = saida;
        this.status = StatusPonto.FECHADO;
    }

    /**
     * Calcula as horas trabalhadas em formato decimal.
     */
    public double calcularHorasTrabalhadas() {
        if (this.saida == null) {
            return 0.0;
        }
        Duration duracao = Duration.between(this.entrada, this.saida);
        return duracao.toMinutes() / 60.0;
    }

    public LocalDate getData() {
        return data;
    }

    public LocalTime getEntrada() {
        return entrada;
    }

    public LocalTime getSaida() {
        return saida;
    }

    public StatusPonto getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "RegistroPonto [Data: " + data + ", Entrada: " + entrada + ", Saída: " 
                + (saida != null ? saida : "Em aberto") + ", Status: " + status 
                + ", Horas: " + String.format("%.2fh", calcularHorasTrabalhadas()) + "]";
    }
}
