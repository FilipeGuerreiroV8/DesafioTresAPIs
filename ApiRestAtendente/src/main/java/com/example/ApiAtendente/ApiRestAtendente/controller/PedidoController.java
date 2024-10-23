package com.example.ApiAtendente.ApiRestAtendente.controller;

import com.example.ApiAtendente.ApiRestAtendente.model.Pedido;
import com.example.ApiAtendente.ApiRestAtendente.service.PedidoService;
import com.example.ApiAtendente.ApiRestAtendente.service.dto.PedidoDTO;
import com.example.ApiAtendente.ApiRestAtendente.service.records.DadosProduto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedido")
public class PedidoController {
    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<PedidoDTO> criarPedido(@RequestBody @Validated Pedido pedido){
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoService.criar(pedido));
    }

    @GetMapping
    public ResponseEntity<List<Pedido>> listarPedidos(){
        return ResponseEntity.status(HttpStatus.FOUND).body(pedidoService.listar());
    }
}
