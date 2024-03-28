# Projeto Agenda em Java

Este é um projeto desenvolvido no Senai como parte do curso de Analise de Sistemas na Materia de Backend em Java. Trata-se de uma aplicação de agenda com banco de dados, utilizando o XAMPP como servidor SQL localmente. A IDE NetBeans foi utilizada para desenvolver tanto o backend quanto o frontend.

## Funcionalidades

- **Login:** Os usuários podem fazer login na aplicação utilizando um nome de usuário e senha.
- **Cadastro de Usuários:** Novos usuários podem se cadastrar na aplicação.
- **Edição de Cadastros:** Usuários podem editar as informações de contato existentes na agenda.
- **Exclusão de Cadastros:** Cadastros existentes podem ser excluídos da agenda.
- **Conexão com Banco de Dados:** A aplicação está conectada a um banco de dados MySQL usando o XAMPP.

## Requisitos

- Java Development Kit (JDK)
- XAMPP (ou outro servidor MySQL)
- IDE NetBeans (ou outra IDE Java compatível)

## Configuração

1. Instale o XAMPP e inicie os serviços Apache e MySQL.
2. Importe o projeto para a IDE NetBeans.
3. Configure as informações de conexão com o banco de dados no arquivo de configuração (geralmente `config.properties`).
4. Execute o projeto.

## Estrutura do Projeto

- **src/main/java:** Contém o código-fonte Java da aplicação.
- **src/main/resources:** Contém os recursos estáticos da aplicação, como arquivos de configuração.
- **web:** Contém os arquivos relacionados ao frontend da aplicação, se houver.

## Dependências

- JDBC (para conexão com o banco de dados)
- Swing (para construção da interface gráfica, se utilizada)

## Contribuição

Contribuições são bem-vindas! Se você encontrar problemas ou tiver sugestões para melhorias, sinta-se à vontade para abrir uma issue ou enviar um pull request.
