package com.luisfelipe.manutencao.service;

import com.luisfelipe.manutencao.model.Cliente;
import com.luisfelipe.manutencao.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente cadastrar(Cliente cliente){
        return clienteRepository.save(cliente);
    }

    public List<Cliente> listaClientes (){
        return clienteRepository.findAll();
    }

    public Optional<Cliente> buscaPorId(Long id){
        return clienteRepository.findById(id);
    }

    public void delete(Long id){
        clienteRepository.deleteById(id);
    }

    public Cliente atualizar (Long id, Cliente cliente){
        Cliente clienteAux = clienteRepository.findById(id).orElseThrow();
        clienteAux.setNome(cliente.getNome());
        clienteAux.setEmail(cliente.getEmail());
        clienteAux.setTelefone(cliente.getTelefone());
        return clienteRepository.save(clienteAux);
    }
}
