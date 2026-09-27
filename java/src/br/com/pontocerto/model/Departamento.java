package br.com.pontocerto.model;

import java.util.ArrayList;
import java.util.List;

public class Departamento {
    private String codigo;
    private String nome;
    private List<Colaborador> colaboradores;

    public Departamento(String codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
        this.colaboradores = new ArrayList<>();
    }

    public void adicionarColaborador(Colaborador colaborador) {
        if (colaborador != null && !this.colaboradores.contains(colaborador)) {
            this.colaboradores.add(colaborador);
        }
    }

    public void removerColaborador(Colaborador colaborador) {
        this.colaboradores.remove(colaborador);
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public List<Colaborador> getColaboradores() {
        return colaboradores;
    }

    @Override
    public String toString() {
        return codigo + " - " + nome + " (" + colaboradores.size() + " colaboradores)";
    }
}
