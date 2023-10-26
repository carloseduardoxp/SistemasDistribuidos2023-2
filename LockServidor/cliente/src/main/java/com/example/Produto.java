package com.example;

import java.io.Serializable;

public class Produto implements Serializable {

    private Integer id;

    private String nome;
 
    private Integer estoque;

    private Double preco;

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
