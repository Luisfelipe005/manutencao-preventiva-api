# Sistema de Manutenção Preventiva

Esse projeto está sendo desenvolvido para o meu TCC do curso de Análise e Desenvolvimento de Sistemas.

A ideia do sistema é ajudar técnicos de climatização a organizar as manutenções preventivas de equipamentos de ar-condicionado.

Além de cadastrar clientes, equipamentos e manutenções, o sistema utiliza o histórico de cada equipamento para calcular uma criticidade e classificar ele como Baixa, Média ou Alta.

## Tecnologias utilizadas

- Java
- Spring Boot
- Spring Data JPA
- Hibernate
- Maven
- H2 Database
- API REST

## Como funciona

O sistema possui três entidades principais:

- Cliente
- Equipamento
- Manutenção

Um cliente pode possuir vários equipamentos e cada equipamento pode ter um histórico de manutenções.

Através dessas informações, o sistema analisa alguns dados do equipamento para calcular sua criticidade.

São considerados:

- tempo de uso;
- quantidade de falhas;
- quantidade de manutenções corretivas;
- situação da manutenção preventiva.

Depois do cálculo, o equipamento recebe uma classificação:

- BAIXA: até 2 pontos
- MÉDIA: de 3 a 5 pontos
- ALTA: acima de 5 pontos

A ideia dessa classificação é ajudar o técnico a identificar quais equipamentos precisam de mais atenção.

## Funcionalidades

Até o momento o projeto possui:

- CRUD de clientes;
- CRUD de equipamentos;
- CRUD de manutenções;
- relacionamento entre as entidades;
- histórico de manutenções;
- cálculo de criticidade;
- tratamento de exceções;
- API REST.

## Estrutura do projeto

Organizei o backend em camadas:

- Controller
- Service
- Repository
- Model
- DTO
- Exception

## Status do projeto

O backend e a lógica de criticidade já estão funcionando.

No momento estou desenvolvendo o frontend para criar a interface do sistema.

## Próximos passos

- Desenvolver o frontend;
- Integrar a interface com o backend;
- Continuar realizando testes;
- Finalizar o projeto para apresentação do TCC.

## Autor

Luis Felipe Soares Santos da Conceição

GitHub: [Luisfelipe005](https://github.com/Luisfelipe005)