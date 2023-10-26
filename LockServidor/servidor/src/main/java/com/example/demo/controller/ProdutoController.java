package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Produto;
import com.example.demo.repository.ProdutoRepository;

@RestController
public class ProdutoController {

    @Autowired
    private ProdutoRepository repository;

    @GetMapping("/produtos/{id}")
    public Produto getProduto(@PathVariable Integer id) {
        return repository.findById(id).get();
    }
    
}
