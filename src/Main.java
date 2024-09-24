import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        Usuario usuario1 = new Usuario("Pedro");
        usuario1.adicionarTag("geladeira");
        usuario1.adicionarTag("fogao");

        Usuario usuario2 = new Usuario("Lucas");
        usuario2.adicionarTag("mesa");
        usuario2.adicionarTag("monitor");

        Usuario usuario3 = new Usuario("Ana");
        usuario3.adicionarTag("cadeira");
        usuario3.adicionarTag("mesa");

        Usuario usuario4 = new Usuario("Joao");
        usuario4.adicionarTag("air fryer");
        usuario4.adicionarTag("fogao");

        Usuario usuario5 = new Usuario("Maria");
        usuario5.adicionarTag("cama");

        Usuario usuario6 = new Usuario("Laura");
        usuario6.adicionarTag("monitor");
        usuario6.adicionarTag("cama");
        usuario6.adicionarTag("air fryer");


        GrafoRecomendacao grafo = new GrafoRecomendacao();
        grafo.adicionarUsuario(usuario1);
        grafo.adicionarUsuario(usuario2);
        grafo.adicionarUsuario(usuario3);
        grafo.adicionarUsuario(usuario4);
        grafo.adicionarUsuario(usuario5);
        grafo.adicionarUsuario(usuario6);


        Produto produto1 = new Produto("Geladeira Brastemp");
        produto1.adicionarTag("geladeira");

        Produto produto2 = new Produto("Fogao 4 bocas");
        produto2.adicionarTag("fogao");

        Produto produto3 = new Produto("Mesa de escritorio");
        produto3.adicionarTag("mesa");

        Produto produto4 = new Produto("Monitor samsung");
        produto4.adicionarTag("monitor");

        Produto produto5 = new Produto("Cadeira de escritorio");
        produto5.adicionarTag("cadeira");

        Produto produto6 = new Produto("Air Fryer top");
        produto6.adicionarTag("air fryer");

        Produto produto7 = new Produto("Cama ortoruim");
        produto7.adicionarTag("cama");

        Produto produto8 = new Produto("Monitor LG");
        produto8.adicionarTag("monitor");

        Produto produto9 = new Produto("Air Fryer 250w");
        produto9.adicionarTag("air fryer");

        LinkedList<Produto> produtos = new LinkedList<>();
        produtos.add(produto1);
        produtos.add(produto2);
        produtos.add(produto3);
        produtos.add(produto4);
        produtos.add(produto5);
        produtos.add(produto6);
        produtos.add(produto7);
        produtos.add(produto8);
        produtos.add(produto9);

        LinkedList<String> recomendacoes = grafo.recomendarProdutos(usuario6, produtos);
        System.out.println("Produtos recomendadas para " + usuario6 + ": " + recomendacoes);


    }
}
