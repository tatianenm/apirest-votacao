# API REST VOTAÇÃO DE PAUTAS

 A aplicação é uma  API Rest de votação de pautas, o banco de dados utilizado é postgresql, desenvolvida com Spring boot.

  Requisitos:
     Intellij,
     jdk 11,
     maven.
 
  Testar a aplicação: swagger
   
  Heroku: https://apirest-votacao.herokuapp.com/swagger-ui.html;
 
   Cadastro de Associado
   URL:GET/api-rest/v1/associados
   URL:POST/api-rest/v1/associados
   
   
   Cadastro de Pauta
   URL:GET/api-rest/v1/pautas
   URL:POST/api-rest/v1/pautas
   
   Cadastro de Sessão
   URL:POST/api-rest/v1/sessao
   
   Cadastro de Votação
   URL:GET/api-rest/v1/votacao
   URL:POST/api-rest/v1/votacao

  Código implementado:
  Divisão de responsabilidades das classes onde o controlador fica responsável por aceitar e devolver a requisição,
  camada service responsável pelas regras de negócio e o repository responsável pela conexão com banco de dados.
  DTOs  recebem e devolvem os dados necessários.
