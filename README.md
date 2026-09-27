# Sistema de RH - Controle de Ponto e Colaboradores

## Integrantes
- Felipe Gabriel Boccardo de Brito de Jesus - RA: 3024103228

## Sobre o Projeto
Sistema para gerenciamento de colaboradores, departamentos e controle de ponto da empresa. O sistema permite o cadastro de colaboradores sob regimes CLT e Estágio, alocação em departamentos e cargos, além de registrar as marcações de entrada e saída diárias com validação de horários e cálculo de adicionais de horas trabalhadas.

## Diagrama de Classes

```mermaid
classDiagram
    direction LR

    class Colaborador {
        <<abstract>>
        -matricula : String
        -nome : String
        -salarioBase : double
        -cargo : Cargo
        -pontos : List~RegistroPonto~
        +getMatricula() String
        +getNome() String
        +getSalarioBase() double
        +getCargo() Cargo
        +setCargo(cargo: Cargo) void
        +registrarPonto(data: LocalDate, entrada: LocalTime) RegistroPonto
        +fecharPonto(data: LocalDate, saida: LocalTime) void
        +getPontos() List~RegistroPonto~
        +calcularAdicional(horasExtras: double)* double
        +getCargaHorariaDiaria()* int
    }

    class ColaboradorCLT {
        -pis : String
        +getPis() String
        +calcularAdicional(horasExtras: double) double
        +getCargaHorariaDiaria() int
    }

    class ColaboradorEstagiario {
        -instituicaoEnsino : String
        -apoliceSeguro : String
        +getInstituicaoEnsino() String
        +getApoliceSeguro() String
        +calcularAdicional(horasExtras: double) double
        +getCargaHorariaDiaria() int
    }

    class Cargo {
        -codigo : String
        -titulo : String
        -departamentoNome : String
        +getCodigo() String
        +getTitulo() String
        +getDepartamentoNome() String
    }

    class Departamento {
        -codigo : String
        -nome : String
        -colaboradores : List~Colaborador~
        +adicionarColaborador(colaborador: Colaborador) void
        +removerColaborador(colaborador: Colaborador) void
        +getColaboradores() List~Colaborador~
        +getCodigo() String
        +getNome() String
    }

    class RegistroPonto {
        -data : LocalDate
        -entrada : LocalTime
        -saida : LocalTime
        -status : StatusPonto
        +registrarSaida(saida: LocalTime) void
        +calcularHorasTrabalhadas() double
        +getData() LocalDate
        +getEntrada() LocalTime
        +getSaida() LocalTime
        +getStatus() StatusPonto
    }

    class StatusPonto {
        <<enumeration>>
        ABERTO
        FECHADO
        AJUSTADO
    }

    Colaborador <|-- ColaboradorCLT
    Colaborador <|-- ColaboradorEstagiario
    Colaborador "1" *-- "*" RegistroPonto
    Departamento "1" o-- "*" Colaborador
    Colaborador "*" --> "1" Cargo
    RegistroPonto --> StatusPonto
```
