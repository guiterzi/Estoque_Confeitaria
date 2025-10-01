package com.example.estoque_confeitaria.controller;

import com.example.estoque_confeitaria.model.Cliente;
import com.example.estoque_confeitaria.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("clientes", clienteService.listarClientes());
        return "clientes/lista";
    }

    @GetMapping("/novo")
    public String abrirCadastro(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "clientes/form";
    }

    @PostMapping
    public String cadastrar(@Valid @ModelAttribute Cliente cliente,
                            BindingResult erros,
                            RedirectAttributes ra) {

        if (erros.hasErrors()) {
            return "clientes/form";
        }
        clienteService.cadastrarCliente(cliente);
        ra.addFlashAttribute("msg", "Cliente cadastrado!");
        return "redirect:/clientes";
    }

    @GetMapping("/editar/{id}")
    public String abrirEdicao(@PathVariable Long id, Model model) {
        model.addAttribute("cliente", clienteService.buscarCliente(id));
        return "clientes/form";
    }

    @PostMapping("/{id}")
    public String atualizar(@PathVariable Long id,
                            @Valid @ModelAttribute Cliente cliente,
                            BindingResult erros,
                            RedirectAttributes ra) {

        if (erros.hasErrors()) {
            return "clientes/form";
        }
        clienteService.atualizarCliente(id, cliente);
        ra.addFlashAttribute("msg", "Cliente atualizado!");
        return "redirect:/clientes";
    }
    
}
