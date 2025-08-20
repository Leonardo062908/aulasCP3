package br.com.fiap.spring_mvc.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O título é obrigatório")
    private String titulo;

    @NotBlank(message = "O autor é obrigatório")
    private String autor;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "A categoria é obrigatória")
    private Categoria categoria;

    @NotBlank(message = "A editora é obrigatória")
    private String editora;

    @NotNull(message = "O preço é obrigatório")
    @DecimalMin(value = "0.99", message = "O preço deve ser no mínimo 0.99")
    @Digits(integer = 9, fraction = 2, message = "Preço com no máximo 2 casas decimais")
    private BigDecimal preco;

    @Pattern(
            regexp = "^970\\d{7}$|^970\\d{10}$", // mantenho sua regra atual
            message = "ISBN fora do padrão"
    )
    private String isbn;

    @NotNull(message = "A data é obrigatória")
    @PastOrPresent(message = "A data não pode ser futura")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) // ajuda no binding yyyy-MM-dd
    private LocalDate dataPublicacao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public LocalDate getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(LocalDate dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }
}