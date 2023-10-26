package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.ItemPedido;
import com.example.demo.service.ItemPedidoService;

@RestController
public class ItemPedidoController {

    @Autowired
    private ItemPedidoService service;

    @PostMapping("/itemPedidos")
    public ItemPedido salvar(@RequestBody ItemPedido item) {
        return service.salvar(item);
    }
    
}
