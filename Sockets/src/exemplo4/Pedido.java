package exemplo4;

public class Pedido {

    private String cpf;

    private Double valor;

    public Pedido(String cpf, Double valor) {
        this.cpf = cpf;
        this.valor = valor;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
    
}
