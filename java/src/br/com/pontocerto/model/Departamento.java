package br.com.pontocerto.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Representa um Departamento na empresa (Agregação ◇ com Colaborador).
 * O departamento apenas agrupa colaboradores; estes continuam existindo de forma independente.
 */
public class Departamento {
    private String codigo;
    private String nome;
    private List<Colaborador> colaboradores;

    public Departamento(String codigo, String nome) {
        if (codigo == null || codigo.isBlank()) {
            throw new IllegalArgumentException("Código do departamento não pode ser vazio.");
        }
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome do departamento não pode ser vazio.");
        }
        this.codigo = codigo;
        this.nome = nome;
        this.colaboradores = new ArrayList<>();
    }

    /**
     * Relação de Agregação: Adiciona um colaborador ao departamento.
     */
    public void adicionarColaborador(Colaborador colaborador) {
        if (colaborador == null) {
            throw new IllegalArgumentException("Colaborador não pode ser nulo.");
        }
        if (!this.colaboradores.contains(colaborador)) {
            this.colaboradores.add(colaborador);
        }
    }

    /**
     * Relação de Agregação: Remove o colaborador do departamento.
     */
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
        return Collections.unmodifiableList(colaboradores);
    }

    @Override
    public String toString() {
        return "Departamento [" + codigo + " - " + nome + "] (" + colaboradores.size() + " colaboradores)";
    }
}
