package br.com.pontocerto.model;

public class ColaboradorEstagiario extends Colaborador {
    private String instituicaoEnsino;
    private String apoliceSeguro;

    public ColaboradorEstagiario(String matricula, String nome, double bolsaAuxilio, Cargo cargo, 
                                 String instituicaoEnsino, String apoliceSeguro) {
        super(matricula, nome, bolsaAuxilio, cargo);
        this.instituicaoEnsino = instituicaoEnsino;
        this.apoliceSeguro = apoliceSeguro;
    }

    @Override
    public double calcularAdicional(double horasExtras) {
        return 0.0;
    }

    @Override
    public int getCargaHorariaDiaria() {
        return 6;
    }

    public String getInstituicaoEnsino() {
        return instituicaoEnsino;
    }

    public String getApoliceSeguro() {
        return apoliceSeguro;
    }

    @Override
    public String toString() {
        return super.toString() + " [Estagiario - " + instituicaoEnsino + "]";
    }
}
