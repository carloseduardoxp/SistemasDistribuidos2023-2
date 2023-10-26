package com.example;


public class Main {
	public static void main(String[] args) throws Exception {
        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(new ThreadProcessaRequisicao());
            t.start();
            //Thread.sleep(100);
        }
    }
};