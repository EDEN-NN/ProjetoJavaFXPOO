package entities;

public class Telefone extends AbstractObjectModel<Integer>{

    private String numero;

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
}
