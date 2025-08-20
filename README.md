# Livraria — Spring MVC (Spring Boot + Thymeleaf + JPA)

Aplicação web simples de **cadastro e listagem de livros** construída com **Spring Boot 3**, **Spring MVC**, **Thymeleaf** e **Spring Data JPA**. 
O projeto demonstra:
- Padrão **MVC** (Controller → Service → Repository → Entity)
- **Validação server-side** com `@Valid` + `BindingResult` (Bean Validation)
- **Templates Thymeleaf** com `th:field`, `th:errors` e fragments (`header`/`footer`)
- **Bootstrap 5** (via **WebJars**) + CSS custom (`style.css`)
- Banco **H2 em memória** (ideal para desenvolvimento)

## 📁 Estrutura
```text
src/
├─ main/
│  ├─ java/br/com/fiap/spring_mvc/
│  │  ├─ SpringMvcApplication.java
│  │  ├─ controller/LivroController.java
│  │  ├─ entity/
│  │  │  ├─ Livro.java
│  │  │  └─ Categoria.java
│  │  ├─ repository/LivroRepository.java
│  │  └─ service/LivroService.java
│  └─ resources/
│     ├─ application.properties
│     ├─ static/css/style.css
│     └─ templates/
│        ├─ fragments/header.html
│        ├─ fragments/footer.html
│        ├─ livroCadastro.html
│        └─ livroLista.html
└─ test/
   └─ java/br/com/fiap/spring_mvc/SpringMvcApplicationTests.java
```

## ✨ Funcionalidades
- **Listar livros** — `/livros/lista`  
- **Formulário de cadastro** — `/livros/cadastro`
- **Salvar livro** — `POST /livros/cadastrar`
- Validações no cadastro:
  - Título / Autor / Editora: obrigatórios
  - Categoria: obrigatória (select)
  - Preço: obrigatório, mínimo `0.99`, até 2 casas decimais
  - ISBN: validação por regex (padrão iniciado por 970 — ajustável)
  - Data de publicação: obrigatória e **não futura**

> Após um cadastro válido o fluxo usa **PRG (Post‑Redirect‑Get)**, redirecionando para `/livros/lista` e evitando reenvio do formulário.

## 🧱 Tecnologias
- **Java 21**
- **Spring Boot 3.5.x**
  - Web (Spring MVC)
  - Thymeleaf
  - Spring Data JPA
  - Validation (Jakarta Bean Validation)
- **H2 Database** (runtime)
- **Bootstrap 5** via **WebJars**

## 🚀 Como executar

Pré‑requisitos: Java 21 e Gradle (ou o wrapper do projeto).

```bash
# Linux/Mac
./gradlew bootRun

# Windows
gradlew.bat bootRun
```

Acesse:
- `http://localhost:8080/livros/cadastro`
- `http://localhost:8080/livros/lista`

### Testes
```bash
./gradlew test
```

## 🖼️ Views (Thymeleaf)
- **Fragments** reutilizáveis: `fragments/header.html` e `fragments/footer.html`
- **Formulário** (`livroCadastro.html`)
  - Usa `th:object`/`th:field` e `th:errorclass="error"` para destacar campos inválidos
  - Mensagens de erro por campo com `th:errors`
- **Lista** (`livroLista.html`)
  - Tabela com classes do **Bootstrap** (`table table-striped table-hover`)

## 🧩 Camadas principais

### Controller
`LivroController` expõe endpoints para listar, exibir formulário e cadastrar.
Usa `@Valid` + `BindingResult` para tratar erros e `RedirectAttributes` no PRG.

### Service
`LivroService` isola a regra de negócio e orquestra o `LivroRepository` (JPA).

### Repository
`LivroRepository` estende `JpaRepository<Livro, Long>`.

### Entities
- `Livro` — campos com anotações de **validação** (Bean Validation).
- `Categoria` — enum usado no `<select>` do formulário.

## ⚙️ Configuração
Arquivo `src/main/resources/application.properties` (mínimo):
```properties
spring.application.name=spring-mvc
```
> Se desejar expor o console do H2 em dev, você pode complementar com:
> ```properties
> spring.h2.console.enabled=true
> spring.h2.console.path=/h2-console
> spring.datasource.url=jdbc:h2:mem:demo;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE
> spring.jpa.hibernate.ddl-auto=update
> ```

## 🛣️ Rotas
- `GET  /livros/lista` — lista de livros
- `GET  /livros/cadastro` — formulário de cadastro
- `POST /livros/cadastrar` — salva o livro (validação server‑side)

## 🔮 Próximos passos (sugestões)
- Implementar **detalhe/edição/remoção** de livro
- Paginação e ordenação na lista (`Pageable`)
- Mensagens i18n (`messages.properties`)
- Testes com **MockMvc** para validar cenários de erro/sucesso do formulário

---

Feito com ☕ e Spring. 
