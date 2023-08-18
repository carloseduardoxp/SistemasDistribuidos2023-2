import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ServidorSocketThread implements Runnable {

    private Socket socketClient;

    public ServidorSocketThread(Socket socketClient) {
        this.socketClient = socketClient;
    }

    @Override
    public void run() {
         try (DataInputStream entrada = new DataInputStream(socketClient.getInputStream())) {
            DataOutputStream saida = new DataOutputStream(socketClient.getOutputStream());
            String linha = entrada.readUTF();
            while (linha != null && !(linha.trim().equals(""))) {
                saida.writeUTF(linha);
                linha = entrada.readUTF();
            }
            saida.writeUTF(linha);
            socketClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
         
    }
    
}
