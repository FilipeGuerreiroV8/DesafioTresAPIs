package com.example.ApiAtendente.ApiRestAtendente.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;

@Entity
public class ItemPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pedido_id")
    @JsonIgnore
    private Pedido pedido;

    private String nomeProduto;
    private Integer qtdUnidades;
    @Nullable
    private Double preco;

    public ItemPedido() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public Integer getQtdUnidades() {
        return qtdUnidades;
    }

    public void setQtdUnidades(Integer qtdUnidades) {
        this.qtdUnidades = qtdUnidades;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Nullable
    public Double getPreco() {
        return preco;
    }

    public void setPreco(@Nullable Double preco) {
        this.preco = preco;
    }
}
