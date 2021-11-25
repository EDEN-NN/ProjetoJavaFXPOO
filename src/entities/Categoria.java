package entities;

public class Categoria extends AbstractObjectModel<Integer>{

    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
