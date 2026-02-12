# Sistema de Gerenciamento de Campeonatos e Times

Este projeto implementa um sistema de gerenciamento de campeonatos e times, conforme os requisitos de legibilidade, reusabilidade, adequação a padrões, confiabilidade e corretude. O sistema foi desenvolvido em **Java** com **Spring Boot** e utiliza o banco de dados em memória **H2** para persistência.

## Princípios de Qualidade Aplicados

| Princípio | Aplicação no Projeto |
| :--- | :--- |
| **Legibilidade** | Uso de nomes de classes, métodos e variáveis claros e em português (ex: `Time`, `Campeonato`, `ParticipacaoService`). |
| **Reusabilidade** | As entidades `Time` e `Campeonato` são reutilizáveis. A lógica de negócio é isolada na camada de `Service`, seguindo o Princípio da Responsabilidade Única. |
| **Adequação a Padrões** | Utilização da arquitetura em camadas (Controller, Service, Repository) e endpoints **RESTful** (`/api/times`, `/api/campeonatos`). |
| **Confiabilidade** | Implementação de validações de dados (ex: `dataFinal` > `dataInicial`, pontuação não negativa) e tratamento de exceções na camada de `Service`. |
| **Corretude** | O modelo de dados (`Time`, `Campeonato`, `Participacao`) atende diretamente ao requisito de registrar times, campeonatos e suas colocações por pontos. |

## Requisitos

*   Java Development Kit (JDK) 21 ou superior
*   Apache Maven

## Como Executar o Projeto

1.  Navegue até o diretório raiz do projeto (`campeonatos-times`).
2.  Execute o comando Maven para compilar e rodar a aplicação:

    ```bash
    ./mvnw spring-boot:run
    ```

3.  A aplicação estará acessível em `http://localhost:8080`.

## Acesso ao Console H2

O banco de dados H2 está configurado para ser acessível via console web, útil para inspecionar os dados:

*   **URL:** `http://localhost:8080/h2-console`
*   **JDBC URL:** `jdbc:h2:mem:campeonatosdb`
*   **Usuário:** `sa`
*   **Senha:** `password`

## Documentação da API (Endpoints RESTful)

A API é dividida em três recursos principais: Times, Campeonatos e Participações.

### 1. Times (`/api/times`)

| Método | Rota | Descrição | Corpo da Requisição (Exemplo) |
| :--- | :--- | :--- | :--- |
| `POST` | `/api/times` | Cria um novo Time. | `{"nome": "Flamengo", "cidade": "Rio de Janeiro"}` |
| `GET` | `/api/times` | Lista todos os Times. | - |
| `GET` | `/api/times/{id}` | Busca um Time pelo ID. | - |
| `PUT` | `/api/times/{id}` | Atualiza um Time existente. | `{"nome": "Flamengo", "cidade": "RJ"}` |
| `DELETE` | `/api/times/{id}` | Deleta um Time. | - |

### 2. Campeonatos (`/api/campeonatos`)

| Método | Rota | Descrição | Corpo da Requisição (Exemplo) |
| :--- | :--- | :--- | :--- |
| `POST` | `/api/campeonatos` | Cria um novo Campeonato. | `{"nome": "Brasileirão 2025", "dataInicial": "2025-04-15", "dataFinal": "2025-12-08"}` |
| `GET` | `/api/campeonatos` | Lista todos os Campeonatos. | - |
| `GET` | `/api/campeonatos/{id}` | Busca um Campeonato pelo ID. | - |
| `PUT` | `/api/campeonatos/{id}` | Atualiza um Campeonato. | - |
| `DELETE` | `/api/campeonatos/{id}` | Deleta um Campeonato. | - |

### 3. Participações (`/api/participacoes`)

| Método | Rota | Descrição | Corpo da Requisição (Exemplo) |
| :--- | :--- | :--- | :--- |
| `POST` | `/api/participacoes` | Registra a participação de um Time em um Campeonato. | `{"time": {"id": 1}, "campeonato": {"id": 1}, "pontuacao": 75, "colocacao": 1}` |
| `GET` | `/api/participacoes/campeonato/{campeonatoId}` | Lista as participações de um Campeonato, ordenadas pela colocação. | - |
| `GET` | `/api/participacoes/{id}` | Busca uma Participação pelo ID. | - |
| `PUT` | `/api/participacoes/{id}` | Atualiza uma Participação (ex: mudar a colocação). | - |
| `DELETE` | `/api/participacoes/{id}` | Deleta uma Participação. | - |
