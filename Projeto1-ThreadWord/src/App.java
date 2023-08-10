import java.io.File;

public class App {
    public static void main(String[] args) throws Exception {
        Texto t = new Texto();
        File file = new File("c:\\teste\\arquivo.txt");
        LeituraTeclado lt = new LeituraTeclado(t);
        SalvarDisco sd = new SalvarDisco(t, file);
        VerificadorOrtografico vo = new VerificadorOrtografico(t);
        Thread thread1 = new Thread(lt);
        Thread thread2 = new Thread(sd);
        Thread thread3 = new Thread(vo);
        thread1.start();
        thread2.start();
        thread3.start();
        System.out.println("terminando o programa principal");
    }
}
