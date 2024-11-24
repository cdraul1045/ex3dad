package com.example.mspedido.service;
import com.example.mspedido.entity.Pedido;
import java.util.List;
import java.util.Optional;

public interface PedidoService {
    public List<Pedido> list();
    public Pedido save(Pedido Pedido);
    public Pedido update(Pedido Pedido);
    public Optional<Pedido> findById(Integer id);
    public void delete(Integer id);
}

