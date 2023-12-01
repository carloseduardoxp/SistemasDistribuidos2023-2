package br.edu.iftm.sd;

import java.io.IOException;
import java.util.StringTokenizer;

import javax.naming.Context;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class ContaDevGptMapper
        extends Mapper<Object, Text, Text, IntWritable> {

    private final static IntWritable numeroUm = new IntWritable(1);
    private final Text palavra = new Text();

    @Override
    public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
        StringTokenizer tk = new StringTokenizer(value.toString());
        boolean proximo = false;
        while (tk.hasMoreTokens()) {
            String token = tk.nextToken();
            if (proximo) {
                String[] extensoes = token.split("\\.(?=[^\\.]+$)");
                System.out.println(extensoes.length);
                System.out.println(extensoes);
                if (extensoes.length > 0) {
                    String extensao = extensoes[extensoes.length-1];
                    System.out.println(extensao);
                    palavra.set(extensao.toLowerCase()
                        .replaceAll("[^a-zA-Z# ]", ""));
                    context.write(palavra, numeroUm);
                }
                proximo = false;
            }
            if (token.contains("FileName")) {
                proximo = true;
            } 
        }
    }
}