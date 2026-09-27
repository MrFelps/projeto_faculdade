package br.com.pontocerto.model;

public class ColaboradorCLT extends Colaborador {
    private String pis;

    public ColaboradorCLT(String matricula, String nome, double salarioBase, Cargo cargo, String pis) {
        super(matricula, nome, salarioBase, cargo);
        this.pis = pis;
    }

    @Override
    public double calcularAdicional(double horasExtras) {
        if (horasExtras <= 0) {
            return 0.0;
        }
        double valorHora = getValorHora();
        return horasExtras * (valorHora * 1.5);
    }

    @Override
    public int getCargaHorariaDiaria() {
        return 8;
    }

    public String getPis() {
        return pis;
    }

    @Override
    public String toString() {
        return super.toString() + " [CLT - PIS: " + pis + "]";
    }
}
