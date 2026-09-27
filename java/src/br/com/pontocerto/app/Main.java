package br.com.pontocerto.app;

import br.com.pontocerto.model.Cargo;
import br.com.pontocerto.model.Colaborador;
import br.com.pontocerto.model.ColaboradorCLT;
import br.com.pontocerto.model.ColaboradorEstagiario;
import br.com.pontocerto.model.Departamento;
import br.com.pontocerto.model.RegistroPonto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

/**
 * Classe de Execução / Demonstração do Sistema RH PontoCerto.
 * Demonstra na prática os 4 pilares de POO e os relacionamentos de modelagem.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("==========================================================");
        System.out.println("     🏢 RH PONTOCERTO — SISTEMA DE GESTÃO DE PONTO       ");
        System.out.println("==========================================================\n");

        // 1. Criação de Cargos e Departamento (Agregação e Associação)
        Cargo cargoDev = new Cargo("DEV-01", "Desenvolvedor Java Pleno", "Tecnologia");
        Cargo cargoEstagio = new Cargo("EST-01", "Estagiário de Desenvolvimento", "Tecnologia");

        Departamento depTI = new Departamento("DEP-TI", "Tecnologia da Informação");

        // 2. Instanciação com Polimorfismo e Herança
        Colaborador clt = new ColaboradorCLT(
                "CLT-1001",
                "Carlos Silva",
                4400.00,
                cargoDev,
                "123.45678.90-1"
        );

        Colaborador estagiario = new ColaboradorEstagiario(
                "EST-2001",
                "Mariana Costa",
                1500.00,
                cargoEstagio,
                "Universidade Federal",
                "APOL-998877"
        );

        // Agregação: Adicionando ao departamento
        depTI.adicionarColaborador(clt);
        depTI.adicionarColaborador(estagiario);

        System.out.println("--- 📌 DEPARTAMENTO E COLABORADORES ---");
        System.out.println(depTI);
        for (Colaborador c : depTI.getColaboradores()) {
            System.out.println("  • " + c);
        }
        System.out.println();

        // 3. Registro de Ponto (Composição: Ponto pertence estritamente ao Colaborador)
        System.out.println("--- ⏱️ REGISTRO DE PONTO DIÁRIO ---");
        LocalDate hoje = LocalDate.now();

        // Entrada e Saída normais para Carlos (CLT)
        clt.registrarPonto(hoje, LocalTime.of(8, 0));
        clt.fecharPonto(hoje, LocalTime.of(17, 30)); // 9.5 horas trabalhadas (1.5h extra)

        // Entrada e Saída normais para Mariana (Estágio)
        estagiario.registrarPonto(hoje, LocalTime.of(9, 0));
        estagiario.fecharPonto(hoje, LocalTime.of(15, 0)); // 6 horas trabalhadas

        System.out.println("Pontos de " + clt.getNome() + ":");
        for (RegistroPonto p : clt.getPontos()) {
            System.out.println("  " + p);
        }

        System.out.println("\nPontos de " + estagiario.getNome() + ":");
        for (RegistroPonto p : estagiario.getPontos()) {
            System.out.println("  " + p);
        }
        System.out.println();

        // 4. Demonstração de Polimorfismo no Cálculo de Adicional
        System.out.println("--- 🧠 DEMONSTRAÇÃO DE POLIMORFISMO (Cálculo de Adicional / Horas Extras) ---");
        List<Colaborador> equipe = Arrays.asList(clt, estagiario);
        double horasExtrasTrabalhadas = 10.0; // Simulando 10h extras no mês

        for (Colaborador colab : equipe) {
            double adicional = colab.calcularAdicional(horasExtrasTrabalhadas);
            System.out.println("Colaborador: " + colab.getNome() + " (" + colab.getClass().getSimpleName() + ")");
            System.out.println("  • Carga horária padrão: " + colab.getCargaHorariaDiaria() + "h/dia");
            System.out.println("  • Valor hora estimado: R$ " + String.format("%.2f", colab.getValorHora()));
            System.out.println("  • Adicional para " + horasExtrasTrabalhadas + "h extras: R$ " + String.format("%.2f", adicional));
            System.out.println("  • Total com adicional: R$ " + String.format("%.2f", (colab.getSalarioBase() + adicional)));
            System.out.println();
        }

        // 5. Teste de Proteção de Invariante de Negócio (Encapsulamento)
        System.out.println("--- 🛡️ TESTE DE PROTEÇÃO DE INVARIANTE (Não permitir saída antes da entrada) ---");
        try {
            LocalDate amanha = hoje.plusDays(1);
            clt.registrarPonto(amanha, LocalTime.of(9, 0));
            System.out.println("Tentando registrar saída às 08:00 com entrada às 09:00...");
            clt.fecharPonto(amanha, LocalTime.of(8, 0)); // Deve lançar exceção!
        } catch (IllegalArgumentException e) {
            System.out.println("✅ Invariante funcionando com sucesso! Erro capturado:");
            System.out.println("   " + e.getMessage());
        }

        System.out.println("\n==========================================================");
        System.out.println("           SISTEMA EXECUTADO COM SUCESSO!                 ");
        System.out.println("==========================================================");
    }
}
