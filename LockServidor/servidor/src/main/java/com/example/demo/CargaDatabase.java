package com.example.demo;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.domain.Produto;
import com.example.demo.repository.ProdutoRepository;

@Configuration
public class CargaDatabase {

    private static final Logger log = LoggerFactory.getLogger(CargaDatabase.class);

    @Bean
    CommandLineRunner initDatabase(ProdutoRepository repository) {

    return args -> {
      log.info("Preloading " + repository.save(new Produto("Bombom", 10,2.45)));
      log.info("Preloading " + repository.save(new Produto("Havaiana", 15,9.54)));
    };
  }
}
