package br.edu.ifpb.cinema.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifpb.cinema.dao.DiretorDAO;
import br.edu.ifpb.cinema.dao.NacionalidadeDAO;
import br.edu.ifpb.cinema.model.Diretor;

@Controller 
@RequestMapping("/diretores")
public class DiretorController {

    private final DiretorDAO diretorDAO;
    private final NacionalidadeDAO nacionalidadeDAO;

    private static final String MENSAGEM_SUCESSO = "Operação realizada com sucesso!";
    private static final String MENSAGEM_ERRO = "Houve um erro ao realizar a operação.";

    public DiretorController(DiretorDAO diretorDAO, NacionalidadeDAO nacionalidadeDAO) {
        this.diretorDAO = diretorDAO;
        this.nacionalidadeDAO = nacionalidadeDAO;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("diretores", diretorDAO.listarDiretoresComNacionalidade());
        return "listDiretores";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("diretor", new Diretor());
        try {
            model.addAttribute("nacionalidades", nacionalidadeDAO.listar());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "cadastrarDiretor";
    }

    @PostMapping
    public String salvar(@ModelAttribute Diretor diretor, RedirectAttributes redirectAttributes) {
        try {
            if (diretor.getIdDiretor() == null) {
                diretorDAO.salvar(diretor);
            } else {
                diretorDAO.atualizar(diretor);
            }
            redirectAttributes.addFlashAttribute("mensagemSucesso", MENSAGEM_SUCESSO);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensagemErro", MENSAGEM_ERRO);
            e.printStackTrace();
        }
        return "redirect:/diretores";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable("id") Integer id, Model model) {
        Diretor diretor = diretorDAO.buscarPorId(id);
        model.addAttribute("diretor", diretor);
        model.addAttribute("nacionalidades", nacionalidadeDAO.listar());
        return "cadastrarDiretor";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        try {
            diretorDAO.deletar(id);
            redirectAttributes.addFlashAttribute("mensagemSucesso", "Diretor excluído com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensagemErro", "Erro ao excluir o diretor.");
            e.printStackTrace();
        }
        return "redirect:/diretores";
    }
}