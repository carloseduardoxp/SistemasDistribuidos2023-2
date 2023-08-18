import java.net.ServerSocket;
import java.net.Socket;

public class ServidorSocket1 {
    public static void main(String[] args) throws Exception {
        ServerSocket s = new ServerSocket(2001);
        Thread.currentThread().setName("ThreadServidor");
        while (true) {
            System.out.print("Esperando conectar..."); 
            Socket conexao = s.accept();
            System.out.println(" Conectou!");
            ServidorSocketThread sst = new ServidorSocketThread(conexao);
            Thread threadCliente = new Thread(sst,"ThreadConexao");
            threadCliente.start();
        }

    }
}
