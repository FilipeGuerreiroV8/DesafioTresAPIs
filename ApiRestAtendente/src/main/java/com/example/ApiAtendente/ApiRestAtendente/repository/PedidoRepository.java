package com.example.ApiAtendente.ApiRestAtendente.repository;

import com.example.ApiAtendente.ApiRestAtendente.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
