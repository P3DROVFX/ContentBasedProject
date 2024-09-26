import java.util.LinkedList;

class Usuario {
    String nome; //NOME DO USUÁRIO
    LinkedList<String> tags;  //as tags que cada usuário vai receber
    LinkedList<Arestas> arestas;

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

    public LinkedList<Arestas> getArestas() {
        return arestas;
    }

    @Override
    public String toString() {
        return nome;
    }

    public String getNome() {
        return nome;
    }
}
