import java.util.LinkedList;

public class Arestas {
    Usuario usuario;
    public int peso; //APENAS PRA INDICAR SE ESTÁCONECTADA A OUTROS VÉRTICES

    public Arestas(Usuario usuario, int peso) {
        this.usuario = usuario;
        this.peso = peso;
    }

    public Usuario getUsuario() {
        return usuario;
    }
}
