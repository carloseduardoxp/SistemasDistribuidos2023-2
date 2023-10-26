package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.domain.ItemPedido;
import com.example.demo.domain.Produto;
import com.example.demo.repository.ItemPedidoRepository;
import com.example.demo.repository.ProdutoRepository;

@Service
public class ItemPedidoService {

    @Autowired
    private ItemPedidoRepository repository;

    @Autowired
    private ProdutoRepository produtoRepository;


    @Transactional
    public ItemPedido salvar(ItemPedido item) {
        if (item.getQuantidade() <= 0) {
            
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Quantidade precisa ser maior do que 0",null); 
        }
        Produto produto = produtoRepository.getProduto(item.getProduto().getId());
        int estoque = produto.getEstoque();
        int novoEstoque = estoque - item.getQuantidade();
        
        if (novoEstoque >= 0) {
            item.getProduto().setEstoque(novoEstoque);
            produtoRepository.save(item.getProduto());
            return repository.save(item);
        } else {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Sem estoque disponível", null); 
        }
        
    }

    
}
