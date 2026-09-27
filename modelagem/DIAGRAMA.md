# 📐 Modelagem do Sistema RH PontoCerto

Este documento contém o Diagrama de Classes UML do sistema **RH PontoCerto**, especificando todos os atributos, métodos, visibilidades, multiplicidades e tipos de relacionamentos.

---

## 📊 Diagrama de Classes UML (Mermaid)

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

    Colaborador <|-- ColaboradorCLT : Herança (é um)
    Colaborador <|-- ColaboradorEstagiario : Herança (é um)
    Colaborador "1" *-- "*" RegistroPonto : Composição ◆
    Departamento "1" o-- "*" Colaborador : Agregação ◇
    Colaborador "*" --> "1" Cargo : Associação
    RegistroPonto --> StatusPonto : Associação
```

---

## 📌 Guia de Leitura dos Relacionamentos

- **Herança (`──▷` / `<|--`)**: `ColaboradorCLT` e `ColaboradorEstagiario` herdam de `Colaborador`.
- **Composição (`◆` / `*--`)**: `Colaborador` compõe `RegistroPonto`. O registro de ponto não existe sem o colaborador proprietário.
- **Agregação (`◇` / `o--`)**: `Departamento` agrega `Colaborador`. Se o departamento for extinto, os colaboradores continuam existindo.
- **Associação (`-->`)**: `Colaborador` referencia seu `Cargo` e `RegistroPonto` referencia `StatusPonto`.
- **Visibilidade**:
  - `-` = Privado (`private`)
  - `+` = Público (`public`)
  - `*` = Método Abstrato (`abstract`)
