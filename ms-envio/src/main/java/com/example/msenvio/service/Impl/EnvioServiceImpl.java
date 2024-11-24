package com.example.msenvio.service.Impl;

import com.example.msenvio.dto.ClienteDto; // Importa el DTO Cliente
import com.example.msenvio.dto.ProductoDto; // Importa el DTO Producto
import com.example.msenvio.entity.Envio; // Importa la entidad Envio
import com.example.msenvio.entity.EnvioDetalle; // Importa la entidad EnvioDetalle
import com.example.msenvio.feign.ClienteFeign; // Importa el cliente Feign para Cliente
import com.example.msenvio.feign.ProductoFeign; // Importa el cliente Feign para Producto
import com.example.msenvio.repository.EnvioRepository; // Importa el repositorio EnvioRepository
import com.example.msenvio.service.EnvioService; // Importa la interfaz de servicio EnvioService
import org.springframework.beans.factory.annotation.Autowired; // Importa la anotación para inyección de dependencias
import org.springframework.stereotype.Service; // Importa la anotación para definir un servicio

import java.util.List; // Importa la clase List
import java.util.Optional; // Importa la clase Optional

@Service
public class EnvioServiceImpl implements EnvioService {

    @Autowired
    private EnvioRepository envioRepository; // Inyección del repositorio de envíos

    @Autowired
    private ProductoFeign productoFeign; // Inyección del cliente Feign para productos

    @Autowired
    private ClienteFeign clienteFeign; // Inyección del cliente Feign para clientes

    @Override
    public List<Envio> list() {
        return envioRepository.findAll(); // Retorna todos los envíos
    }

    @Override
    public Envio save(Envio envio) {
        return envioRepository.save(envio); // Guarda un nuevo envío
    }

    @Override
    public Optional<Envio> findById(Integer id) {
        Optional<Envio> envioOptional = envioRepository.findById(id); // Busca el envío

        // Verifica si el Optional contiene un valor
        if (envioOptional.isPresent()) {
            Envio envio = envioOptional.get(); // Obtiene el envío

            // Obtiene el cliente asociado al envío usando el cliente Feign
            ClienteDto clienteDto = clienteFeign.getById(envio.getClienteId()).getBody();
            envio.setClienteDto(clienteDto); // Establece el cliente en el envío

            // Recorre los detalles del envío para obtener los productos
            for (EnvioDetalle detalleEnvio : envio.getEnvioDetalle()) {
                ProductoDto productoDto = productoFeign.getById(detalleEnvio.getProductoId()).getBody();
                detalleEnvio.setProductoDto(productoDto); // Establece el producto en el detalle de envío
            }

            // Retorna el Optional con el envío modificado
            return Optional.of(envio);
        } else {
            // Retorna vacío si no se encuentra el envío
            return Optional.empty();
        }
    }

    @Override
    public void delete(Integer id) {
        envioRepository.deleteById(id); // Elimina un envío por ID
    }

    @Override
    public Envio update(Envio envio) {
        return envioRepository.save(envio); // Actualiza un envío existente
    }
}
