package com.example.ApiAdministrativo.ApiRestAdministrativo.controller;

import com.example.ApiAdministrativo.ApiRestAdministrativo.model.NotaFiscal;
import com.example.ApiAdministrativo.ApiRestAdministrativo.service.NotaFiscalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notafiscal")
public class NotaFiscalController {
    @Autowired
    private NotaFiscalService notaFiscalService;

    @GetMapping
    public ResponseEntity<List<NotaFiscal>> buscarTodas(){
        return ResponseEntity.status(HttpStatus.FOUND).body(notaFiscalService.buscarTodas());
    }

    @PostMapping
    public ResponseEntity<NotaFiscal> criar(@RequestBody @Validated NotaFiscal notaFiscal){
        return ResponseEntity.status(HttpStatus.CREATED).body(notaFiscalService.criar(notaFiscal));
    }
}
