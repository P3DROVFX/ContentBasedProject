import java.util.LinkedList;

class Produto {
    String nome;  //NOME É O NOME DO PRODUTO, DIFERENTE DA TAG
    LinkedList<String> tagPrincipal;  //A TAG VAI SER A CATEGORIA DELE, NÃO USAR COMO NOME DO PRODUTO

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


    public String getNome() {
        return nome;
    }
}
