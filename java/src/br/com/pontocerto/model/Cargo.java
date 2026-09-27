package br.com.pontocerto.model;

/**
 * Representa um Cargo na organização (Associação com Colaborador).
 */
public class Cargo {
    private String codigo;
    private String titulo;
    private String departamentoNome;

    public Cargo(String codigo, String titulo, String departamentoNome) {
        if (codigo == null || codigo.isBlank()) {
            throw new IllegalArgumentException("Código do cargo não pode ser vazio.");
        }
        if (titulo == null || titulo.isBlank()) {
            throw new IllegalArgumentException("Título do cargo não pode ser vazio.");
        }
        this.codigo = codigo;
        this.titulo = titulo;
        this.departamentoNome = departamentoNome;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDepartamentoNome() {
        return departamentoNome;
    }

    @Override
    public String toString() {
        return titulo + " (" + codigo + ")";
    }
}
