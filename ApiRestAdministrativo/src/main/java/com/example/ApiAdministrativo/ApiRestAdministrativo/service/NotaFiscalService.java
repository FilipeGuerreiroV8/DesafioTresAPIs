package com.example.ApiAdministrativo.ApiRestAdministrativo.service;

import com.example.ApiAdministrativo.ApiRestAdministrativo.model.NotaFiscal;
import com.example.ApiAdministrativo.ApiRestAdministrativo.repository.NotaFiscalRepository;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotaFiscalService {
    @Autowired
    NotaFiscalRepository notaFiscalRepository;

    public NotaFiscal criar(NotaFiscal notaFiscal){
        notaFiscal.getItensNota().forEach(itemAtual -> itemAtual.setNotaFiscal(notaFiscal));
        return notaFiscalRepository.save(notaFiscal);
    }
    public List<NotaFiscal> buscarTodas(){
        return notaFiscalRepository.findAll();
    }
}
