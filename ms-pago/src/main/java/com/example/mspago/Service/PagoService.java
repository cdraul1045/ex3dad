package com.example.mspago.Service;

import com.example.mspago.Entity.Pago; // Importa la entidad Pago

import java.util.List;
import java.util.Optional;

public interface PagoService {
    public List<Pago> list(); // Lista todos los pagos.

    public Pago save(Pago pago); // Guarda un nuevo pago.

    public Pago update(Pago pago); // Actualiza un pago existente.

    Optional<Pago> findById(Integer id); // Busca un pago por ID y obtiene el cliente y detalles.

    public void delete(Integer id); // Elimina un pago por ID.
}
