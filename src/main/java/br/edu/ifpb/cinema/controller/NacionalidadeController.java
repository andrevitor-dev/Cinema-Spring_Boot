package br.edu.ifpb.cinema.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifpb.cinema.dao.NacionalidadeDAO;
import br.edu.ifpb.cinema.model.Nacionalidade;

@Controller 
@RequestMapping("/nacionalidades")
public class NacionalidadeController {

    private final NacionalidadeDAO nacionalidadeDAO;

    private static final String MENSAGEM_SUCESSO = "Operação realizada com sucesso!";
    private static final String MENSAGEM_ERRO = "Houve um erro ao realizar a operação.";

    public NacionalidadeController(NacionalidadeDAO nacionalidadeDAO) {
        this.nacionalidadeDAO = nacionalidadeDAO;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("nacionalidades", nacionalidadeDAO.listar());
        return "listNacionalidades";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("nacionalidade", new Nacionalidade());
        return "cadastrarNacionalidade";
    }

    @PostMapping
    public String salvar(@ModelAttribute Nacionalidade nacionalidade, RedirectAttributes redirectAttributes) {
        try {
            if (nacionalidade.getIdNacionalidade() == null) {
                nacionalidadeDAO.salvar(nacionalidade);
            } else {
                nacionalidadeDAO.atualizar(nacionalidade);
            }
            redirectAttributes.addFlashAttribute("mensagemSucesso", MENSAGEM_SUCESSO);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensagemErro", MENSAGEM_ERRO);
            e.printStackTrace();
        }
        return "redirect:/nacionalidades";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable("id") Integer id, Model model) {
        Nacionalidade nacionalidade = nacionalidadeDAO.buscar(id);
        model.addAttribute("nacionalidade", nacionalidade);
        return "cadastrarNacionalidade";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        try {
            nacionalidadeDAO.deletar(id);
            redirectAttributes.addFlashAttribute("mensagemSucesso", "Nacionalidade excluída com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensagemErro", "Não é possível excluir esta nacionalidade pois ela está vinculada a um diretor.");
            e.printStackTrace();
        }
        return "redirect:/nacionalidades";
    }
}