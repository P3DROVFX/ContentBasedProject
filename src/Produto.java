import java.util.LinkedList;

class Produto {
    String nome;
    LinkedList<String> tags;

    public Produto(String nome) {
        this.nome = nome;
        this.tags = new LinkedList<>();
    }

    public void adicionarTag(String tag) {
        tags.add(tag);
    }

    public LinkedList<String> getTags() {
        return tags;
    }
}
