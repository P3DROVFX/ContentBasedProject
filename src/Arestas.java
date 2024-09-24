import java.util.LinkedList;

public class Arestas {
    Usuario usuario;
    public int peso;

    public Arestas(Usuario usuario, int peso) {
        this.usuario = usuario;
        this.peso = peso;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public int getPeso() {
        return peso;
    }

}
