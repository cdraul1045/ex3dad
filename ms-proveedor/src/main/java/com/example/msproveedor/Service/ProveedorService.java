package com.example.msproveedor.Service;

import com.example.msproveedor.Entity.Proveedor;

import java.util.List;
import java.util.Optional;

public interface ProveedorService {
    public List<Proveedor> list();
    public Proveedor save(Proveedor proveedor);
    public Proveedor update(Proveedor proveedor);
    public Optional<Proveedor> findById(Integer id);

    Optional<Proveedor> listarPorId(Integer id);

    public void deleteById(Integer id);
}
