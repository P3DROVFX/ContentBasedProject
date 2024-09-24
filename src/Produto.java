import java.util.LinkedList;

class Produto {
    String nome;
    LinkedList<String> tagPrincipal;

    public Produto(String nome) {
        this.nome = nome;
        this.tagPrincipal = new LinkedList<>();
    }

    public void adicionarTag(String tag) {
        tagPrincipal.add(tag);
    }

    public LinkedList<String> getTagPrincipal() {
        return tagPrincipal;
    }
}
