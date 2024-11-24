package com.example.mscliente.service;

import com.example.mscliente.entity.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteService {
    public List<Cliente> list();  // Listar todos los clientes
    public Cliente save(Cliente cliente);  // Guardar un nuevo cliente
    public Cliente update(Cliente cliente);  // Actualizar un cliente existente
    public Optional<Cliente> findById(Integer id);  // Buscar un cliente por su ID
    public void delete(Integer id);  // Eliminar un cliente por su ID
}
