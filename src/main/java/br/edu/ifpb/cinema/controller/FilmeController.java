package br.edu.ifpb.cinema.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifpb.cinema.dao.FilmeDAO;
import br.edu.ifpb.cinema.model.Filme;

@Controller 
@RequestMapping("/filmes")
public class FilmeController {

    private final FilmeDAO filmeDAO;

    private static final String MENSAGEM_SUCESSO = "Operação realizada com sucesso!";
    private static final String MENSAGEM_ERRO = "Houve um erro ao realizar a operação.";

    public FilmeController(FilmeDAO filmeDAO) {
        this.filmeDAO = filmeDAO;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("filmes", filmeDAO.listar());
        return "listFilmes";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("filme", new Filme());
        return "cadastrarFilme";
    }

    @PostMapping
    public String salvar(@ModelAttribute Filme filme, RedirectAttributes redirectAttributes) {
        try {
            if (filme.getIdFilme() == null) {
                filmeDAO.salvar(filme);
            } else {
                filmeDAO.atualizar(filme);
            }
            redirectAttributes.addFlashAttribute("mensagemSucesso", MENSAGEM_SUCESSO);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensagemErro", MENSAGEM_ERRO);
            e.printStackTrace();
        }
        return "redirect:/filmes";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable("id") Integer id, Model model) {
        Filme filme = filmeDAO.buscarPorId(id);
        model.addAttribute("filme", filme);
        return "cadastrarFilme";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        try {
            filmeDAO.deletar(id);
            redirectAttributes.addFlashAttribute("mensagemSucesso", "Filme excluído com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensagemErro", "Erro ao excluir o filme.");
            e.printStackTrace();
        }
        return "redirect:/filmes";
    }
}