package com.example.ApiAdministrativo.ApiRestAdministrativo.repository;

import com.example.ApiAdministrativo.ApiRestAdministrativo.model.NotaFiscal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotaFiscalRepository extends JpaRepository<NotaFiscal, Long> {
}
