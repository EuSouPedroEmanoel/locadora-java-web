# Nome do Projeto

> ⚠️ **Nota de Contexto e Maturidade Técnica**
> Este projeto foi desenvolvido em **pouco tempo** como parte de uma disciplina acadêmica. A proposta central da matéria era aprender os fundamentos da arquitetura para a web utilizando **Java Web puro (Servlets e JSPs), sem o uso de frameworks modernos** (como Spring, Hibernate, etc.). O objetivo era entender "o que acontece por baixo dos panos" antes de adotar ferramentas de abstração.
>
> Como eu estava no início do meu processo de aprendizado com essas tecnologias, o código reflete estritamente o meu nível de conhecimento na época.

## 🏗️ Arquitetura do Projeto
Apesar de ser um projeto de estudante feito sob restrição de tempo, a estrutura foi desenhada seguindo o padrão **MVC (Model-View-Controller)** para garantir a separação de responsabilidades, organizando-se em:
* **View:** Páginas `JSP` responsáveis pela camada de apresentação.
* **Controller:** `Servlets` Java encarregados de receber as requisições HTTP, processar as ações e gerenciar o fluxo.
* **Model & Persistência:**
  * **VO (Value Object):** Classes de modelagem e transferência de dados.
  * **DAO (Data Access Object):** Camada responsável por isolar toda a lógica de persistência e consultas SQL.
  * **Módulo de Conexão:** Componente dedicado estritamente a gerenciar a comunicação com o banco de dados.

## 🎨 Sobre o uso de CSS Inline
Se você notar o uso de **CSS inline** em alguns pontos, isso foi puramente um experimento de momento. Eu nunca tinha testado essa abordagem antes e queria ver, na prática, como ela se comportava diretamente nas tags do HTML/JSP. Hoje compreendo perfeitamente que essa não é uma boa prática de estilização e manutenção.

## 🛠️ Tecnologias Utilizadas (Escopo Acadêmico)
* **Java (Jakarta EE / Java EE)**
* **Java Servlet API**
* **JSP (JavaServer Pages)**
* **Servidor de Aplicação:** (Ex: Apache Tomcat / GlassFish)

## 🧠 O que eu faria diferente hoje em dia?
Olhando para trás, com a experiência e maturidade que tenho hoje, o desenvolvimento deste projeto seria abordado de forma diferente:
1. **Adoção de Frameworks Modernos:** Utilizaria o ecossistema **Spring Boot** (Spring MVC, Spring Data JPA) para gerenciar o ciclo de vida da aplicação, injeção de dependências e persistência de dados de forma nativa e segura.
2. **Abstração da Camada de Dados:** Substituiria o JDBC puro e os DAOs manuais por um ORM (como o Hibernate), eliminando a necessidade de escrever queries SQL manualmente no código e facilitando a manutenção.
3. **Estilização Profissional:** Separaria totalmente o CSS da estrutura HTML/JSP, utilizando arquivos `.css` externos ou integrando uma ferramenta de mercado (como Tailwind CSS ou Bootstrap).
4. **Segurança e Validações:** Implementaria filtros de autenticação robustos (como Spring Security), tratamento global de exceções e validações rigorosas na entrada de dados.

---

*Este repositório permanece público estritamente como um registro histórico do meu progresso e evolução como desenvolvedor.*
