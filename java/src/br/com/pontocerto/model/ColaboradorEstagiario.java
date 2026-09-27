package br.com.pontocerto.model;

/**
 * Representa um Estagiário na organização (Pilares: Herança e Polimorfismo).
 */
public class ColaboradorEstagiario extends Colaborador {
    private String instituicaoEnsino;
    private String apoliceSeguro;

    public ColaboradorEstagiario(String matricula, String nome, double bolsaAuxilio, Cargo cargo, 
                                 String instituicaoEnsino, String apoliceSeguro) {
        super(matricula, nome, bolsaAuxilio, cargo);
        if (instituicaoEnsino == null || instituicaoEnsino.isBlank()) {
            throw new IllegalArgumentException("Instituição de ensino não pode ser vazia.");
        }
        if (apoliceSeguro == null || apoliceSeguro.isBlank()) {
            throw new IllegalArgumentException("Apólice de seguro é obrigatória para estágio.");
        }
        this.instituicaoEnsino = instituicaoEnsino;
        this.apoliceSeguro = apoliceSeguro;
    }

    /**
     * Polimorfismo: Pela legislação de estágio, estagiários têm carga horária limitada
     * e não recebem adicional de horas extras.
     */
    @Override
    public double calcularAdicional(double horasExtras) {
        // Estágio possui regras restritas e não gera adicional de horas extras
        return 0.0;
    }

    /**
     * Retorna a jornada padrão de estágio de 6 horas diárias.
     */
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
        return super.toString() + " | Tipo: Estagiário (Faculdade: " + instituicaoEnsino 
                + ", Seguro: " + apoliceSeguro + ", Jornada: " + getCargaHorariaDiaria() + "h)";
    }
}
