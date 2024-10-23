package com.example.ApiEstoque.ApiRestEstoque.Controller;


import com.example.ApiEstoque.ApiRestEstoque.Model.*;
import com.example.ApiEstoque.ApiRestEstoque.Service.EstoqueService;
import com.example.ApiEstoque.ApiRestEstoque.Service.dto.EstoqueDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estoque")
public class EstoqueController {
    @Autowired
    private EstoqueService estoqueService;

    @GetMapping
//    public ResponseEntity<CollectionModel<EntityModel<Estoque>>> listarProdutos(){
//        List<Estoque> produtos = estoqueService.listar();
//
//        List<EntityModel<Estoque>> entityModels = produtos.stream()
//                .map(p -> EntityModel.of(p,
//                        linkTo(methodOn(EstoqueController.class).adicionarProduto())))
//                .collect(Collectors.toList());
//
//        return ResponseEntity.ok(CollectionModel.of(entityModels,
//                linkTo(methodOn(EstoqueController.class).adicionarProduto())));
//    }

    public ResponseEntity<List<Estoque>> listarProdutos(){
        return ResponseEntity.status(HttpStatus.FOUND).body(estoqueService.listar());
    }

    @GetMapping("/buscar")
    public ResponseEntity<EstoqueDTO> buscarPorNome(@RequestParam(name = "nome", required = false) String nome){
        Estoque produtoEncontrado = estoqueService.buscarPorNome(nome);
        return ResponseEntity.status(HttpStatus.CREATED).body(new EstoqueDTO(produtoEncontrado.getNomeProduto(), produtoEncontrado.getPreco(),produtoEncontrado.getCategoria(),produtoEncontrado.getQtdUnidades()));
    }

    @GetMapping("/retirar")
    public ResponseEntity<EstoqueDTO> retirarPorNome(@RequestParam(name = "nome", required = true) String nome, @RequestParam(name = "qtd", required = true) long qtd){
        EstoqueDTO produtoRetirado = estoqueService.retirar(nome, qtd);
        return ResponseEntity.status(HttpStatus.FOUND).body(produtoRetirado);
    }

    @PostMapping
    public ResponseEntity<Estoque> adicionarProduto(@RequestBody @Validated Estoque produto){
        Estoque novoProduto = estoqueService.adicionar(produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoProduto);
    }

    @DeleteMapping("/{idProduto}")
    public void removerProduto(@PathVariable long idProduto){
        estoqueService.remover(idProduto);
    }

}
