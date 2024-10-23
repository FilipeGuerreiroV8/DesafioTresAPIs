package com.example.ApiAtendente.ApiRestAtendente.repository;

import com.example.ApiAtendente.ApiRestAtendente.model.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long> {
}
