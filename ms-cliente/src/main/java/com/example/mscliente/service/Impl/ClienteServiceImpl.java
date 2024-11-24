package com.example.mscliente.service.Impl;

import com.example.mscliente.entity.Cliente; // Importa la entidad Cliente
import com.example.mscliente.repository.ClienteRepository; // Importa el repositorio ClienteRepository
import com.example.mscliente.service.ClienteService; // Importa la interfaz ClienteService
import org.springframework.beans.factory.annotation.Autowired; // Importa la anotación para la inyección de dependencias
import org.springframework.stereotype.Service; // Importa la anotación para definir un servicio

import java.util.List; // Importa la clase List
import java.util.Optional; // Importa la clase Optional

@Service // Indica que esta clase es un servicio de Spring
public class ClienteServiceImpl implements ClienteService {

    @Autowired // Inyección automática del repositorio ClienteRepository
    private ClienteRepository clienteRepository;

    @Override
    public List<Cliente> list() {
        // Devuelve una lista de todos los clientes
        return clienteRepository.findAll();
    }

    @Override
    public Cliente save(Cliente cliente) {
        // Guarda un nuevo cliente en la base de datos
        return clienteRepository.save(cliente);
    }

    @Override
    public Cliente update(Cliente cliente) {
        // Actualiza un cliente existente en la base de datos
        return clienteRepository.save(cliente);
    }

    @Override
    public Optional<Cliente> findById(Integer id) {
        // Busca un cliente por su ID y devuelve un Optional
        return clienteRepository.findById(id);
    }

    @Override
    public void delete(Integer id) {
        // Elimina un cliente por su ID
        clienteRepository.deleteById(id);
    }
}
