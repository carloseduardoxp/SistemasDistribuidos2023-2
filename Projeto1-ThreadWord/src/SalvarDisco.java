import java.io.File;
import java.io.PrintWriter;

public class SalvarDisco implements Runnable {

    private Texto texto;

    private File file;

    public SalvarDisco(Texto texto,File file) {
        this.texto = texto;
        this.file = file;
    }

    public void run() {
        try {            
            while (true) {
                PrintWriter pw = new PrintWriter(file);
                System.out.println("Salvando em disco:"+texto.lerTexto());
                pw.write(texto.lerTexto().toString());                            
                pw.close();
                Thread.sleep(20000);
            }
        } catch (Exception e) {               
            e.printStackTrace();
        }
    }
    
}
