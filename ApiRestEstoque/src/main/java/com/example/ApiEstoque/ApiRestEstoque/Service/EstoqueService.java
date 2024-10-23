package com.example.ApiEstoque.ApiRestEstoque.Service;

import com.example.ApiEstoque.ApiRestEstoque.Model.Estoque;
import com.example.ApiEstoque.ApiRestEstoque.Repository.EstoqueRepository;
import com.example.ApiEstoque.ApiRestEstoque.Service.dto.EstoqueDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EstoqueService {

    @Autowired
    private EstoqueRepository estoqueRepository;

    public Estoque adicionar(Estoque produto){
        estoqueRepository.save(produto);
        return produto;
    }
    public List<Estoque> listar() {
        return estoqueRepository.findAll();
    }
    public Estoque buscarPorId(long idProduto){
        return estoqueRepository.findById(idProduto).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto de id " + idProduto + " não encontrado."));
    }
    public Estoque buscarPorNome(String nome){
       return estoqueRepository.findByNomeProduto(nome).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto " + nome + " não encontrado"));
    }
    public void remover(long idProduto){
        Estoque produto = buscarPorId(idProduto);
        estoqueRepository.delete(produto);
    }


    public EstoqueDTO retirar(String nome, Long qtd){
        Estoque produtoEstoque = buscarPorNome(nome);
        if(produtoEstoque.getQtdUnidades() < qtd){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Quantidade em estoque do produto " + produtoEstoque.getNomeProduto() + " menor do que Quantidade requerida.");
        }
        produtoEstoque.setQtdUnidades(produtoEstoque.getQtdUnidades() - qtd);
        estoqueRepository.save(produtoEstoque);
        return new EstoqueDTO(produtoEstoque.getNomeProduto(), produtoEstoque.getPreco(), produtoEstoque.getCategoria(), qtd);
    }
}
