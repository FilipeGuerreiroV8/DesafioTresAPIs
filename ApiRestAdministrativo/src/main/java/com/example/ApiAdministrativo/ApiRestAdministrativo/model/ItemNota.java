package com.example.ApiAdministrativo.ApiRestAdministrativo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class ItemNota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "nota_fiscal_id")
    @JsonIgnore
    private NotaFiscal notaFiscal;

    private String nomeItem;
    private Integer qtdUnidades;
    private Double preco;

    public ItemNota() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public NotaFiscal getNotaFiscal() {
        return notaFiscal;
    }

    public void setNotaFiscal(NotaFiscal notaFiscal) {
        this.notaFiscal = notaFiscal;
    }

    public String getNomeItem() {
        return nomeItem;
    }

    public void setNomeItem(String nomeItem) {
        this.nomeItem = nomeItem;
    }

    public Integer getQtdUnidades() {
        return qtdUnidades;
    }

    public void setQtdUnidades(Integer qtdUnidades) {
        this.qtdUnidades = qtdUnidades;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }
}
