# API RESTful para Sistema de Usuários de Carros

Esta é uma aplicação que expõe uma API RESTful para gerenciamento de usuários e carros com funcionalidade de autenticação via login. A aplicação é desenvolvida utilizando o framework Spring e aceita e responde apenas em formato JSON. O projeto é construído seguindo padrões modernos de desenvolvimento e práticas de segurança.

## Estórias de Usuário

1. **Autenticação do Usuário:**
   - Como usuário, quero poder fazer login na aplicação para acessar minhas informações e gerenciar meus carros.

2. **Gerenciamento de Usuários:**
   - Como usuário, quero poder listar todos os usuários cadastrados.
   - Como usuário, quero poder cadastrar um novo usuário na aplicação.
   - Como usuário, quero poder buscar um usuário pelo seu identificador único (ID).
   - Como usuário, quero poder remover um usuário pelo seu identificador único (ID).
   - Como usuário, quero poder atualizar as informações de um usuário existente.

3. **Gerenciamento de Carros:**
   - Como usuário autenticado, quero poder listar todos os meus carros cadastrados.
   - Como usuário autenticado, quero poder cadastrar um novo carro na minha conta.
   - Como usuário autenticado, quero poder buscar um carro pelo seu identificador único (ID).
   - Como usuário autenticado, quero poder remover um carro pelo seu identificador único (ID).
   - Como usuário autenticado, quero poder atualizar as informações de um carro pertencente à minha conta.

## Solução

Este projeto consiste em uma aplicação Java Spring que oferece uma API RESTful para gerenciamento de usuários e carros. Abaixo estão os principais componentes e características da solução:

- **Tecnologias Utilizadas:** Java 17, Spring Framework, Spring Boot, Spring Security, JPA/Hibernate, H2 Database, JWT para autenticação, Maven para build.

- **Funcionalidades:** A aplicação permite a criação, listagem, busca, atualização e remoção de usuários e carros. Alguns endpoints são protegidos com autenticação JWT, garantindo a segurança das operações.
- Para mais informações, acesse o swagger em ${host_da_maquina}/open-api

- **Autenticação:** Os usuários podem fazer login na aplicação através do endpoint `/api/signin`, fornecendo seu login e senha. Um token de acesso JWT é retornado após autenticação bem-sucedida, permitindo acesso aos endpoints protegidos.

- **Validação de Dados:** A entrada de dados é validada para garantir a integridade e consistência dos dados. Mensagens de erro claras são retornadas em caso de campos inválidos ou ausentes.

- **Documentação:** A API é documentada utilizando o Swagger para facilitar o entendimento e uso por parte dos desenvolvedores.

- **Testes:** São incluídos testes de integração para garantir o correto funcionamento das funcionalidades implementadas.

- **A ferramenta ainda está em desenvolvimento.

## Executando o Projeto

1. **Clonar o Repositório:**

git clone https://github.com/ranyelmoraes/secure-car-park.git

2. **Build do Projeto:**

mvn clean install

3. **Execução da Aplicação:**

java -jar target/secure-car-park-{versão-pom}.jar

4. **Acesso à API:**
Acesse a API em `http://localhost:8080`.

5. **Documentação da API:**
Acesse a documentação da API em `http://localhost:8080/open-api.html`.

## Contribuição

Contribuições são bem-vindas! Sinta-se à vontade para abrir uma *issue* ou enviar um *pull request* com melhorias, correções ou novas funcionalidades.



