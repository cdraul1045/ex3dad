package com.example.mspedido.service.impl;

import com.example.mspedido.dto.ClienteDto;
import com.example.mspedido.dto.ProductoDto;
import com.example.mspedido.entity.Pedido;
import com.example.mspedido.entity.PedidoDetalle;
import com.example.mspedido.feign.ClienteFeign;
import com.example.mspedido.feign.ProductoFeign;
import com.example.mspedido.repository.PedidoRepository;
import com.example.mspedido.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoServiceImpl implements PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteFeign clienteFeign;

    @Autowired
    private ProductoFeign productoFeign;

    @Override
    public List<Pedido> list() {
        return pedidoRepository.findAll();
    }

    @Override
    public Pedido save(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    @Override
    public Optional<Pedido> findById(Integer id) {
        Optional<Pedido> pedidoOptional = pedidoRepository.findById(id);

        // Verifica si el Optional contiene un valor
        if (pedidoOptional.isPresent()) {
            Pedido pedido = pedidoOptional.get();

            // Obtener cliente mediante el cliente Feign
            ClienteDto clienteDto = clienteFeign.getById(pedido.getClienteId()).getBody();
            pedido.setClienteDto(clienteDto);

            // Recorre los detalles del pedido solo si el pedido existe
            for (PedidoDetalle detallePedido : pedido.getPedidodetalle()) {
                // Obtiene el producto asociado al detalle del pedido y lo establece
                ProductoDto productoDto = productoFeign.getById(detallePedido.getProductoId()).getBody();
                detallePedido.setProductoDto(productoDto);
            }

            // Retorna el Optional con el pedido modificado
            return Optional.of(pedido);
        } else {
            // Maneja el caso en que el pedido no se encuentra, por ejemplo:
            // Lanzar una excepción personalizada o retornar un Optional vacío
            return Optional.empty();
        }
    }

    @Override
    public void delete(Integer id) {
        pedidoRepository.deleteById(id);
    }

    @Override
    public Pedido update(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }
}
