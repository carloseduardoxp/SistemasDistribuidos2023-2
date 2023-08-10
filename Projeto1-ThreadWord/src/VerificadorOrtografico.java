public class VerificadorOrtografico implements Runnable {

    private Texto texto;

    public VerificadorOrtografico(Texto texto) {
        this.texto = texto;
    }

    public void run() {
        while (true) {
            System.out.println("Verificando a ortografia do texto:"+texto.lerTexto());
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {               
                e.printStackTrace();
            }
        }
    }
    
}
