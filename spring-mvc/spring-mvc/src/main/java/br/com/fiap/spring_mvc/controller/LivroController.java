package br.com.fiap.spring_mvc.controller;

import br.com.fiap.spring_mvc.entity.Categoria;
import br.com.fiap.spring_mvc.entity.Livro;
import br.com.fiap.spring_mvc.service.LivroService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/livros")
public class LivroController {

    private final LivroService livroService;

    // Prefira injeção por construtor
    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }

    // Disponibiliza a lista de categorias para QUALQUER view deste controller
    @ModelAttribute("categoriaLista")
    public List<Categoria> populaCategorias() {
        return Arrays.asList(Categoria.values());
    }

    // Redireciona /livros e /livros/ para a lista
    @GetMapping({"", "/"})
    public String rootRedirect() {
        return "redirect:/livros/lista";
    }

    @GetMapping("/lista")
    public String listarLivros(Model model) {
        model.addAttribute("listaLivros", livroService.readLivros());
        return "livroLista";
    }

    @GetMapping("/cadastro")
    public String cadastroLivro(Model model) {
        model.addAttribute("livro", new Livro());
        return "livroCadastro";
    }

    @PostMapping("/cadastrar")
    public String cadastrarLivro(@Valid @ModelAttribute("livro") Livro livro,
                                 BindingResult result,
                                 RedirectAttributes ra) {

        // Se houver erro de validação, volta para o formulário
        if (result.hasErrors()) {
            return "livroCadastro";
        }

        livroService.createLivro(livro);
        ra.addFlashAttribute("msg", "Livro cadastrado com sucesso!");
        return "redirect:/livros/lista"; // PRG
    }
}