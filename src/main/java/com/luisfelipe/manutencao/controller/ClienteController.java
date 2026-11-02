package com.luisfelipe.manutencao.controller;

import com.luisfelipe.manutencao.model.Cliente;
import com.luisfelipe.manutencao.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    public Cliente cadastrar (@Valid @RequestBody Cliente cliente){
        return clienteService.cadastrar(cliente);
    }

    @GetMapping
    public List<Cliente> listarClientes(){
       return clienteService.listaClientes();
    }

    @GetMapping("/{id}")
    public Optional<Cliente> buscaPorId(@PathVariable Long id){
        return clienteService.buscaPorId(id);
    }

    @PutMapping("/{id}")
    public Cliente atualizaCliente(@PathVariable Long id, @Valid @RequestBody Cliente cliente){
        return clienteService.atualizar(id,cliente);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        clienteService.delete(id);
    }
}
