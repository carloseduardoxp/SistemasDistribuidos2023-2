import java.util.Scanner;

public class LeituraTeclado implements Runnable {

    private Scanner scanner;

    private Texto texto;

    public LeituraTeclado(Texto texto) {
        scanner = new Scanner(System.in);
        this.texto = texto;
    }

    public void run() {
        while (true) {
            String s = scanner.nextLine();
            System.out.println("Texto digitado: "+s.toString());
            texto.inserirTexto(s);
        }
        
        
    }
}