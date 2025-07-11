# certdigital

Sistema legado para administração de certificados digitais.

---

## Sumário

1. [Visão Geral do Sistema](#visão-geral-do-sistema)
2. [Arquitetura e Tecnologias](#arquitetura-e-tecnologias)
3. [Estrutura de Diretórios](#estrutura-de-diretórios)
4. [Fluxo de Funcionamento](#fluxo-de-funcionamento)
5. [Principais Componentes](#principais-componentes)
6. [Banco de Dados](#banco-de-dados)
7. [Configurações e Deploy](#configurações-e-deploy)
8. [Customização e Extensibilidade](#customização-e-extensibilidade)
9. [Boas Práticas e Observações](#boas-práticas-e-observações)
10. [Referências e Contatos](#referências-e-contatos)

---

## Visão Geral do Sistema

O **certdigital** é um sistema legado para administração de certificados digitais, desenvolvido em Java 8, utilizando o framework Struts, rodando em um servidor Tomcat e integrado a um banco de dados PostgreSQL. Seu objetivo é gerenciar entidades, operadores, empresas e certificados digitais, fornecendo funcionalidades de cadastro, consulta, manutenção e geração de relatórios.

---

## Arquitetura e Tecnologias

- **Linguagem:** Java 8
- **Framework Web:** Struts 1.x
- **Servidor de Aplicação:** Tomcat
- **Banco de Dados:** PostgreSQL
- **Build:** Ant
- **Front-end:** JSP, HTML, CSS, JavaScript
- **Padrão de Projeto:** MVC (Model-View-Controller)
- **Outras Bibliotecas:** Commons (BeanUtils, Collections, DBCP, Logging, etc.), Log4j, Struts, JDBC drivers

---

## Estrutura de Diretórios

```
certdigital/
│
├── documentos/                # Documentação e scripts de banco
│   └── estrutura.sql
│
├── fontes/
│   ├── ant/                   # Scripts de build (Ant)
│   ├── classes/               # Classes compiladas (.class)
│   ├── configuracoes/         # Configurações de banco e drivers
│   ├── lib/                   # Bibliotecas Java (.jar)
│   └── src/                   # Código-fonte Java (.java)
│
├── web/                       # Aplicação web (JSP, recursos estáticos)
│   ├── _css/                  # Arquivos de estilo
│   ├── _img/                  # Imagens
│   ├── _js/                   # Scripts JavaScript
│   ├── _menu/                 # Menus e navegação
│   ├── APP_TEMPLATES/         # Templates JSP reutilizáveis
│   ├── SCRIPT/                # Scripts auxiliares
│   ├── WEB-INF/               # Configurações do webapp (Struts, web.xml, libs)
│   └── *.jsp, *.htm           # Páginas JSP e HTML
```

---

## Fluxo de Funcionamento

### Camadas

- **View:** JSPs e HTML em `/web/`
- **Controller:** Actions do Struts em `br.certdigital.web.*`
- **Model:** Facades, DAOs e VOs em `br.certdigital.facade`, `br.certdigital.dao`, `br.certdigital.vo`

### Fluxo Típico

1. Usuário acessa uma página JSP (ex: `listarCertificados.jsp`)
2. Requisição é roteada pelo Struts (configurado em `struts-config.xml`)
3. Action correspondente é executada (ex: `CertificadoAction`)
4. Action utiliza Facade/DAO para acessar dados
5. Dados são retornados como VO (Value Object)
6. JSP exibe os dados ao usuário

---

## Principais Componentes

### Pacotes Java

- `br.certdigital.dao`  
  Acesso a dados (CRUD) para entidades do sistema.
- `br.certdigital.facade`  
  Fachada de serviços, centraliza regras de negócio.
- `br.certdigital.vo`  
  Objetos de valor (Value Objects) para transferência de dados.
- `br.certdigital.web`  
  Actions do Struts, controladores das requisições web.
- `br.certdigital.shared`  
  Utilitários, exceções, segurança, formatação, etc.
- `br.certdigital.tools`  
  Utilitários de baixo nível, integração com banco, logging, etc.
- `br.certdigital.view`  
  Helpers para lógica de apresentação.

### Configurações

- **`struts-config.xml`**  
  Mapeamento de Actions, Forms e Forwards do Struts.
- **`web.xml`**  
  Configuração do webapp, servlets, filtros, listeners.
- **`log4j.properties`**  
  Configuração de logging.
- **`db2400-ds.xml` / `config_db2_jboss.txt`**  
  Configuração de datasources para banco de dados.

---

## Banco de Dados

- **Script de estrutura:**  
  `documentos/estrutura.sql`
- **Principais entidades:**  
  Empresas, Operadores, Certificados, Produtos, Preços, Entidades, Regras de Acesso.
- **Conexão:**  
  Configurada via arquivos em `fontes/configuracoes/` e drivers em `fontes/configuracoes/drivers/lib/`.

---

## Configurações e Deploy

### Build

- Utiliza **Ant** (`fontes/ant/build.xml`) para compilar e empacotar o projeto.

### Deploy

- O artefato gerado (provavelmente um `.war`) deve ser colocado no diretório `webapps` do Tomcat.
- Certifique-se de que as bibliotecas necessárias estejam em `WEB-INF/lib`.
- Configure o datasource do PostgreSQL conforme os arquivos de configuração.

### Dependências

- Bibliotecas Java em `fontes/lib/` e `web/WEB-INF/lib/`.
- Drivers JDBC para PostgreSQL e outros bancos suportados.

---

## Customização e Extensibilidade

- **Novas funcionalidades:**  
  Siga o padrão MVC, criando novos VOs, DAOs, Facades, Actions e JSPs conforme necessário.
- **Internacionalização:**  
  Mensagens e labels em `ApplicationResources.properties`.
- **Templates JSP:**  
  Utilize arquivos em `APP_TEMPLATES/` para padronizar mensagens e componentes.

---

## Boas Práticas e Observações

- **Legado:**  
  O sistema utiliza Struts 1.x, que está obsoleto. Considere migração futura para frameworks modernos.
- **Java 8:**  
  Não utilize recursos de versões superiores.
- **Segurança:**  
  Atenção a SQL Injection e XSS, pois o sistema é antigo.
- **Logging:**  
  Utilize o Log4j para rastreabilidade.
- **Backup:**  
  Sempre faça backup do banco antes de alterações.

---

# CertDigital - Deploy em Containers

Este projeto pode ser executado facilmente em containers Docker, incluindo a aplicação Java (Tomcat) e o banco de dados PostgreSQL.

## Pré-requisitos
- [Docker](https://docs.docker.com/get-docker/)
- [Docker Compose](https://docs.docker.com/compose/install/)

## Passos para Deploy

1. **Clone o repositório:**
   ```bash
   git clone <repo-url>
   cd certdigital
   ```

2. **Build e deploy dos containers:**
   ```bash
   docker-compose up --build
   ```
   Isso irá:
   - Subir o banco PostgreSQL com a estrutura definida em `documentos/estrutura.sql`.
   - Buildar a aplicação Java com Ant e rodar no Tomcat.

3. **Acessar a aplicação:**
   - Acesse: [http://localhost:8080/certdigital](http://localhost:8080/certdigital)

4. **Banco de Dados:**
   - Host: `localhost`
   - Porta: `5432`
   - Banco: `certdigital`
   - Usuário: `certdig`
   - Senha: `certdig!1234`

## Observações
- O build do projeto é feito automaticamente no container.
- O script SQL é executado apenas no primeiro start do banco.
- Se alterar o SQL, remova o volume do banco para reexecutar a estrutura:
  ```bash
  docker-compose down -v
  docker-compose up --build
  ```

---
## Referências e Contatos

- **Documentação Struts:**  
  [Struts 1.x Documentation](https://struts.apache.org/1.x/)
- **Documentação Tomcat:**  
  [Tomcat Documentation](https://tomcat.apache.org/tomcat-8.5-doc/)
- **Documentação PostgreSQL:**  
  [PostgreSQL Documentation](https://www.postgresql.org/docs/)

---

  **Responsável Técnico:**  
  Paulo César Pires de Aragão  
  paragao01@gmail.com  
  (21) 98272-9030

---

> Esta documentação foi gerada automaticamente a partir da análise da estrutura do projeto. Para detalhes específicos de regras de negócio, consulte os arquivos de código-fonte em `fontes/src/br/certdigital/`. 
<<<<<<< HEAD
---

Dúvidas? Abra uma issue! 
=======
>>>>>>> 11b9932e8485cd8f79cf47d3d9c7708e360bde6e
