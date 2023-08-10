public class Texto {
    

    private StringBuffer texto;

    public Texto() {
        texto = new StringBuffer("");
    }

    public void inserirTexto(String newText) {
        texto.append(newText+"\n");
    }

    public String lerTexto() {
        return texto.toString();
    }
}
