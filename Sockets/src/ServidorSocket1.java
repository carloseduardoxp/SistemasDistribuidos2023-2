import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorSocket1 {
    public static void main(String[] args) throws Exception {
        ServerSocket s = new ServerSocket(2001);
        while (true) {
            System.out.print("Esperando conectar..."); 
            Socket conexao = s.accept();
            System.out.println(" Conectou!");
            DataInputStream entrada = new DataInputStream(conexao.getInputStream());
            DataOutputStream saida = new DataOutputStream(conexao.getOutputStream());
            String linha = entrada.readUTF();
            while (linha != null && !(linha.trim().equals(""))) {
                    saida.writeUTF(linha);
                    linha = entrada.readUTF();
            }
            saida.writeUTF(linha);
            conexao.close();
        }

    }
}
