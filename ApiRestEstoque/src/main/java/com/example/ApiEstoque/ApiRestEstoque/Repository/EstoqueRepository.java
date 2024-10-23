package com.example.ApiEstoque.ApiRestEstoque.Repository;

import com.example.ApiEstoque.ApiRestEstoque.Model.Estoque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EstoqueRepository extends JpaRepository<Estoque, Long> {

    Optional<Estoque> findByNomeProduto(@Param("nome") String nome);
}
