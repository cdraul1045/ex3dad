package com.example.mspago.Service.Impl;

import com.example.mspago.dto.ProductoDto; // Importa el DTO de Producto
import com.example.mspago.dto.ClienteDto; // Importa el DTO de Cliente
import com.example.mspago.Entity.Pago; // Importa la entidad Pago
import com.example.mspago.Entity.PagoDetalle; // Importa la entidad PagoDetalle
import com.example.mspago.feign.ProductoFeign; // Importa el Feign para productos
import com.example.mspago.feign.ClienteFeign; // Importa el Feign para clientes
import com.example.mspago.Repository.PagoRepository; // Importa el repositorio de Pago
import com.example.mspago.Service.PagoService; // Importa el servicio de Pago
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PagoServiceImpl implements PagoService {

    @Autowired
    private PagoRepository pagoRepository;

    @Autowired
    private ProductoFeign productoFeign;

    @Autowired
    private ClienteFeign clienteFeign;

    @Override
    public List<Pago> list() {
        return pagoRepository.findAll();
    }

    @Override
    public Pago save(Pago pago) {
        return pagoRepository.save(pago);
    }

    @Override
    public Optional<Pago> findById(Integer id) {
        Optional<Pago> pagoOptional = pagoRepository.findById(id);

        // Verifica si el Optional contiene un valor
        if (pagoOptional.isPresent()) {
            Pago pago = pagoOptional.get();

            // Obtener cliente mediante el cliente Feign
            ClienteDto clienteDto = clienteFeign.getById(pago.getClienteId()).getBody();
            pago.setClienteDto(clienteDto);

            // Recorre los detalles del pago solo si el pago existe
            for (PagoDetalle detallePago : pago.getPagoDetalle()) {
                // Obtiene el producto asociado al detalle del pago y lo establece
                ProductoDto productoDto = productoFeign.getById(detallePago.getProductoId()).getBody();
                detallePago.setProductoDto(productoDto);
            }

            // Retorna el Optional con el pago modificado
            return Optional.of(pago);
        } else {
            // Maneja el caso en que el pago no se encuentra, por ejemplo:
            // Lanzar una excepción personalizada o retornar un Optional vacío
            return Optional.empty();
        }
    }

    @Override
    public void delete(Integer id) {
        pagoRepository.deleteById(id);
    }

    @Override
    public Pago update(Pago pago) {
        return pagoRepository.save(pago);
    }
}
