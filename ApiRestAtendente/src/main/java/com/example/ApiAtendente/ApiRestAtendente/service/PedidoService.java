package com.example.ApiAtendente.ApiRestAtendente.service;

import com.example.ApiAtendente.ApiRestAtendente.model.Pedido;
import com.example.ApiAtendente.ApiRestAtendente.repository.ItemPedidoRepository;
import com.example.ApiAtendente.ApiRestAtendente.repository.PedidoRepository;
import com.example.ApiAtendente.ApiRestAtendente.service.dto.PedidoDTO;
import com.example.ApiAtendente.ApiRestAtendente.service.records.DadosItensNota;
import com.example.ApiAtendente.ApiRestAtendente.service.records.DadosNotaFiscal;
import com.example.ApiAtendente.ApiRestAtendente.service.records.DadosProduto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoService {
    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    public DadosProduto retirarProdutoNoEstoque(String nome, Integer qtd){
        String url = "http://localhost:8080/estoque/retirar?nome=" + nome + "&qtd="+qtd;
        return restTemplate.getForObject(url, DadosProduto.class);
    }

    public void enviarNotaFiscal(Pedido pedido){
        String url = "http://localhost:8082/notafiscal";
        List<DadosItensNota> listaItensNota = new ArrayList<>();
        pedido.getProdutos().forEach(p ->{
        DadosItensNota itemNota = new DadosItensNota(
                p.getNomeProduto(),
                p.getQtdUnidades(),
                p.getPreco()
                );
        listaItensNota.add(itemNota);
        });
        DadosNotaFiscal notaFiscal = new DadosNotaFiscal(pedido.getCliente(), listaItensNota, pedido.getPrecoTotal());
        restTemplate.postForEntity(url, notaFiscal, DadosNotaFiscal.class);
    }

    public List<Pedido> listar(){
        return pedidoRepository.findAll();
    }

    public PedidoDTO criar(Pedido pedido){
        List<DadosProduto> dadosProdutos = new ArrayList<>();
        pedido.getProdutos().forEach(produtoAtual -> {
            DadosProduto produtoEstoque = retirarProdutoNoEstoque(produtoAtual.getNomeProduto(), produtoAtual.getQtdUnidades());
            dadosProdutos.add(produtoEstoque);
            produtoAtual.setPedido(pedido);
            produtoAtual.setPreco(produtoEstoque.preco());
        });
        PedidoDTO pedidoResposta = new PedidoDTO(pedido.getId(), pedido.getCliente(),dadosProdutos);
        pedido.setPrecoTotal(pedidoResposta.getPrecoTotal());
        pedidoRepository.save(pedido);
        enviarNotaFiscal(pedido);
        return pedidoResposta;
    }


}
