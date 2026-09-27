package br.com.pontocerto.app;

import br.com.pontocerto.model.Cargo;
import br.com.pontocerto.model.Colaborador;
import br.com.pontocerto.model.ColaboradorCLT;
import br.com.pontocerto.model.ColaboradorEstagiario;
import br.com.pontocerto.model.Departamento;
import br.com.pontocerto.model.RegistroPonto;

import java.time.LocalDate;
import java.time.LocalTime;

public class Main {
    public static void main(String[] args) {
        Cargo dev = new Cargo("C01", "Desenvolvedor Junior", "TI");
        Cargo estagio = new Cargo("C02", "Estagiario TI", "TI");

        Departamento ti = new Departamento("D01", "Tecnologia da Informacao");

        Colaborador func1 = new ColaboradorCLT("101", "Carlos Silva", 3500.0, dev, "12345678900");
        Colaborador func2 = new ColaboradorEstagiario("201", "Mariana Souza", 1400.0, estagio, "UNIP", "SEG-9988");

        ti.adicionarColaborador(func1);
        ti.adicionarColaborador(func2);

        System.out.println("Departamento: " + ti.getNome());
        for (Colaborador c : ti.getColaboradores()) {
            System.out.println(" - " + c);
        }

        LocalDate hoje = LocalDate.now();

        func1.registrarPonto(hoje, LocalTime.of(8, 0));
        func1.fecharPonto(hoje, LocalTime.of(17, 30));

        func2.registrarPonto(hoje, LocalTime.of(9, 0));
        func2.fecharPonto(hoje, LocalTime.of(15, 0));

        System.out.println("\nRegistros de ponto:");
        for (RegistroPonto p : func1.getPontos()) {
            System.out.println(func1.getNome() + " -> " + p);
        }
        for (RegistroPonto p : func2.getPontos()) {
            System.out.println(func2.getNome() + " -> " + p);
        }

        System.out.println("\nCalculo de adicionais (10h extras simuladas):");
        System.out.println(func1.getNome() + " (CLT): R$ " + func1.calcularAdicional(10));
        System.out.println(func2.getNome() + " (Estagiario): R$ " + func2.calcularAdicional(10));
    }
}
