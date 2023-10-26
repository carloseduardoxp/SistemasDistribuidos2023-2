package com.example;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Random;

import com.google.gson.Gson;

public class ThreadProcessaRequisicao implements Runnable {

    @Override
    public void run() {
        try {
            Produto produto = requisicaoProduto();
            ItemPedido item = new ItemPedido();
            item.setProduto(produto);
            Random random = new Random();
            int quantidadeRandom = random.nextInt(5) + 1; //evitar quantidade 0
            item.setQuantidade(quantidadeRandom);
           
            String resposta = enviarItem(item);
            System.out.println(resposta);
        } catch(Exception e) {
            System.out.println("ERRO ----------------------------------------------------");
            System.out.println(e.getMessage());
        }
   
    }
        
    private String enviarItem(ItemPedido item) throws Exception {
        Gson g = new Gson();
        HttpRequest request1 = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/itemPedidos"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(g.toJson(item)))
                .build();

        return HttpClient.newHttpClient().send(request1, HttpResponse.BodyHandlers.ofString()).body();
    }

    public Produto requisicaoProduto() throws Exception {        
        int codigo = 1;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/produtos/"+codigo))
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = null;
        response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        Gson g = new Gson();
        return g.fromJson(response.body(), Produto.class);
    }

}
