package com.example.demo.domain;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;

@Entity
@Table(name="TB_PRODUTO")
public class Produto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="COD_PRODUTO")
    private Integer id;

    @Column(name="DES_PRODUTO")
    private String nome;

    @Column(name="QTD_ESTOQUE")
    private Integer estoque;

    @Column(name="VLR_PRODUTO")
    private Double preco;

    @Version
    @Column(name="COD_VRS")    
    private Integer version;

    public Produto() {
    }

    

    public Produto(String nome, Integer estoque, Double preco) {
        this.nome = nome;
        this.estoque = estoque;
        this.preco = preco;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getEstoque() {
        return estoque;
    }

    public void setEstoque(Integer estoque) {
        this.estoque = estoque;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }



    public Integer getVersion() {
        return version;
    }



    public void setVersion(Integer version) {
        this.version = version;
    }


    
}
