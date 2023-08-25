package exemplo4;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClienteSocket4 {
    public static void main(String args[]) throws Exception{
        Socket conexao = new Socket("127.0.0.1", 2001);
        DataInputStream entrada = new DataInputStream(conexao.getInputStream());
        DataOutputStream saida = new DataOutputStream(conexao.getOutputStream());
        while (true) {
            String linha = "";
            Pedido pedido = new Pedido("050.123.454-25",56.00);
            String mensagem = convertePedidoMensagem(pedido);
            saida.writeUTF(mensagem);
            linha = entrada.readUTF();
            if (linha.equalsIgnoreCase("")) {
                System.out.println("Conexao encerrada!");
                conexao.close();
                break;
            } 
            System.out.println(linha);
        }

    }

    private static String convertePedidoMensagem(Pedido pedido) {
       StringBuilder sb = new StringBuilder("<cpf>")
                                .append(pedido.getCpf())
                                .append("</cpf>")
                                .append("<valor>")
                                .append(pedido.getValor())
                                .append("</valor>");
        return sb.toString();
    }
}
