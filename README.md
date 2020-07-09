# API REST VOTAÇÃO DE PAUTAS

 A aplicação é uma  API Rest de votação de pautas, o banco de dados utilizado é postgresql.

  Requisitos:
     Intellij,
     jdk 11,
     maven.
 
  Testar a aplicação:
      swagger
         
   Funcionamento
   Ao Iniciar o servidor
    1- Deve-se acessar o endereço https://apirest-votacao.herokuapp.com/swagger-ui.html;

   A aplicação é uma Api Rest desenvolvida com Spring boot.

   Cadastro de Pauta
   URL:POST/pauta/v1

  Código implementado:
  Divisão de responsabilidades das classes onde o controlador fica responsável por aceitar e devolver a requisição,
  camada service responsável pelas regras de negócio e o repository responsável pela conexão com banco de dados.
  DTOs  recebem e devolvem os dados necessários.
