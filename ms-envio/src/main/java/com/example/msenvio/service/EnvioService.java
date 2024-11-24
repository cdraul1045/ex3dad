package com.example.msenvio.service;

import com.example.msenvio.entity.Envio;

import java.util.List;
import java.util.Optional;

public interface EnvioService {
    List<Envio> list(); // Listar todos los envíos
    Envio save(Envio envio); // Guardar un nuevo envío
    Envio update(Envio envio); // Actualizar un envío existente
    Optional<Envio> findById(Integer id); // Buscar un envío por ID
    void delete(Integer id); // Eliminar un envío por ID
}
