package exemplo4;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorSocket4 {

    public static void main(String[] args) throws Exception {
        ServerSocket s = new ServerSocket(2001);
        Thread.currentThread().setName("ThreadServidor");
        while (true) {
            System.out.print("Esperando conectar..."); 
            Socket conexao = s.accept();
            System.out.println(" Conectou!");
            try (DataInputStream entrada = new DataInputStream(conexao.getInputStream())) {
                DataOutputStream saida = new DataOutputStream(conexao.getOutputStream());
                String linha = entrada.readUTF();
                while (linha != null && !(linha.trim().equals(""))) {
                    saida.writeUTF(linha);
                    linha = entrada.readUTF();
                }
                saida.writeUTF(linha);
                conexao.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
}
