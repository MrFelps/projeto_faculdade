package br.com.pontocerto.model;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

public class RegistroPonto {
    private LocalDate data;
    private LocalTime entrada;
    private LocalTime saida;
    private StatusPonto status;

    public RegistroPonto(LocalDate data, LocalTime entrada) {
        this.data = data;
        this.entrada = entrada;
        this.saida = null;
        this.status = StatusPonto.ABERTO;
    }

    public void registrarSaida(LocalTime saida) {
        if (saida.isBefore(this.entrada)) {
            throw new IllegalArgumentException("Horario de saida nao pode ser anterior a entrada.");
        }
        this.saida = saida;
        this.status = StatusPonto.FECHADO;
    }

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
        return "Data: " + data + " | Entrada: " + entrada + " | Saida: " 
                + (saida != null ? saida : "Aberto") + " | Status: " + status;
    }
}
