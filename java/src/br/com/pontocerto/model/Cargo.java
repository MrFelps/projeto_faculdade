package br.com.pontocerto.model;

public class Cargo {
    private String codigo;
    private String titulo;
    private String departamentoNome;

    public Cargo(String codigo, String titulo, String departamentoNome) {
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
