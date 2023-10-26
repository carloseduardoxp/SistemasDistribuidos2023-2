package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.domain.Produto;

import jakarta.persistence.LockModeType;

public interface ProdutoRepository extends JpaRepository<Produto,Integer> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT p FROM Produto p WHERE p.id = ?1")
    public Produto getProduto(Integer id);
    
}
