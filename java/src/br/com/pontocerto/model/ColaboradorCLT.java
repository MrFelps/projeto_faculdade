package br.com.pontocerto.model;

/**
 * Representa um colaborador contratado sob o regime CLT (Pilares: Herança e Polimorfismo).
 */
public class ColaboradorCLT extends Colaborador {
    private String pis;

    public ColaboradorCLT(String matricula, String nome, double salarioBase, Cargo cargo, String pis) {
        super(matricula, nome, salarioBase, cargo);
        if (pis == null || pis.isBlank()) {
            throw new IllegalArgumentException("Número do PIS não pode ser vazio.");
        }
        this.pis = pis;
    }

    /**
     * Polimorfismo: Calcula hora extra com adicional legal de 50% (CLT).
     */
    @Override
    public double calcularAdicional(double horasExtras) {
        if (horasExtras <= 0) {
            return 0.0;
        }
        double valorHora = getValorHora();
        double valorHoraExtra = valorHora * 1.50; // Adicional de 50%
        return horasExtras * valorHoraExtra;
    }

    /**
     * Retorna a jornada padrão CLT de 8 horas diárias.
     */
    @Override
    public int getCargaHorariaDiaria() {
        return 8;
    }

    public String getPis() {
        return pis;
    }

    @Override
    public String toString() {
        return super.toString() + " | Tipo: CLT (PIS: " + pis + ", Jornada: " + getCargaHorariaDiaria() + "h)";
    }
}
