package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.ItemPedido;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido,Integer> {
    
}
