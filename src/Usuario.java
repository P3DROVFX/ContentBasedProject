import java.util.LinkedList;

class Usuario {
    String nome;
    LinkedList<String> tags;
    LinkedList<Usuario> arestas;

    public Usuario(String nome) {
        this.nome = nome;
        this.tags = new LinkedList<>();
        this.arestas = new LinkedList<>();
    }

    public void adicionarTag(String tag) {
        tags.add(tag);
    }

    public LinkedList<String> getTags() {
        return tags;
    }

    public LinkedList<Usuario> getArestas() {
        return arestas;
    }

    @Override
    public String toString() {
        return nome;
    }
}
