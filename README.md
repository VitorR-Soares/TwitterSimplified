<p align="center" width="100%">
    <img width="50%" src="https://turbologo.com/articles/wp-content/uploads/2019/07/twitter-text-logo-and-bird.png"> 
</p>


<h3 align="center">
  Twitter Simplificado
</h3>

<p align="center">

  <img alt="License: MIT" src="https://img.shields.io/badge/license-MIT-%2304D361">
  <img alt="Language: Java" src="https://img.shields.io/badge/language-java-green">
  <img alt="Version: 1.0" src="https://img.shields.io/badge/version-1.0-yellowgreen">
  <img alt="Concluído" src="https://img.shields.io/badge/concluído-OK-green">

</p>

# Descrição
Esse é um projeto que busca simular as operações básicas em um Twitter Simplificado. O objetivo principal foi treinar boas práticas de segurança, como Autorização e Autenticação via Token JWT e criptografia de senhas com BCrypt, controles de permissão com roles e operações CRUD.

## Índice

- [Funcionalidades](#funcionalidades)
- [Tecnologias Utilizadas](#tecnologias-utilizadas)
- [Instalação](#instalação)
- [Utilização](#utilização)
- [API Endpoints](#api-endpoints)

## Funcionalidades

- Criar Conta
- Feed Público
- Login
- Criação de Tweet
- Deleção de Tweet

## Tecnologias Utilizadas

- Java
- Spring Boot
- MySQL como banco de dados
- Docker para conteinerização
- Para analisar o banco de dados, utilizei Beekeper
- Para consumir os endpoints da aplicação, utilizei Bruno

## Instalação

1. Clone o Repositório

```bash
git clone https://github.com/VitorR-Soares/TwitterSimplified.git
```

2. Instale as dependências com Maven
3. Garanta que possua o Docker Instalado

## Utilização

1. Inicie o contâiner docker no console, dentro da pasta 'docker
```markdown
  docker compose up
```
2. Inicie a aplicação com Maven
3. A API estará acessível no http://localhost:8080


## API Endpoints
A API disponibiliza os seguintes endpoints:

```markdown
ACESSO LIVRE / POST / login  - Realiza login na aplicação, com username e password, e retorna um accessToken e seu tempo de expiração

ACESSO LIVRE / POST / createUser - Registra um novo usuário no banco de dados, com username e password

ADMIN / GET / listUsers - Lista usuários, retornando a lista de todas as informações dos usuários [Demana autorização de 'admin']

BASIC / POST / createTweet - Cria um tweet, com o conteúdo digitado

BASCI AUTOR ou ADMIN / DELETE / deleteTweet / {id_tweet} - Deleta tweet especificado no caminho da URL

BASIC / GET / feed - Retorna todos os tweets, na disposição definida pelos QueryParams de 'page' e 'pageSize'
```


:mag: Baixe o projeto e teste você mesmo na prática.

### Entre em contato! Estou à disposição

Acesse meu [Linkedin](https://www.linkedin.com/in/vitorr-soares/) 

Para mais projetos, meu [GitHub](https://github.com/VitorR-Soares/)

