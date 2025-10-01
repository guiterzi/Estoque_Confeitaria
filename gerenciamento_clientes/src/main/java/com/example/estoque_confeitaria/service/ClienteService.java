package com.example.estoque_confeitaria.service;

import com.example.estoque_confeitaria.model.Cliente;
import com.example.estoque_confeitaria.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    // Listar todos os clientes
    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }
    //LIstar por id
    public Cliente buscarCliente(Long id){
        return clienteRepository.findById(id).orElseThrow(()-> new RuntimeException("Cliente não encontrado com id: " + id));

    }

    // Cadastrar um cliente
    public Cliente cadastrarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    // Atualizar um cliente
    public Cliente atualizarCliente(Long id, Cliente clienteAtualizado) {
        return clienteRepository.findById(id)
                .map(c -> {
                    c.setNome(clienteAtualizado.getNome());
                    c.setEmail(clienteAtualizado.getEmail());
                    c.setIdade(clienteAtualizado.getIdade());
                    return clienteRepository.save(c);
                })
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado com id: " + id));
    }

    // Excluir um cliente
    public void excluirCliente(Long id) {
        if (!clienteRepository.existsById(id)) {
            throw new RuntimeException("Cliente não encontrado com id: " + id);
        }
        clienteRepository.deleteById(id);
    }
}
