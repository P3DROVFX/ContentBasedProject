import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws NullPointerException {
        try {
            GrafoRecomendacao grafo = new GrafoRecomendacao();
            adicionarUsuarios(grafo);
            LinkedList<Produto> produtos = adicionarProdutos();
            Scanner scanner = new Scanner(System.in);

            System.out.print("Informe o nome do usuário: ");
            String entrada = scanner.nextLine().toLowerCase();
            LinkedList<String> recomendacoes = grafo.recomendarProdutos(grafo.getUsuario(entrada), produtos);
            System.out.println("\n--- LOG DE CONEXOES ---");
            grafo.imprimirConexoes();
            scanner.close();
        } catch (NullPointerException e) {
            System.out.println("Usuário não encontrado");
        }

    }

    //FUNCOES PARA EVITAR REPETIÇÃO DE CÓDIGO

    //LISTA DE USUÁRIOS, PRIMEIRO VEM O NOME DO USUÁRIO EM MINUSCULO E DEPOIS AS TAGS DOS PRODUTOS QUE ELE COMPROU
    private static void adicionarUsuarios(GrafoRecomendacao grafo) {
        String[][] usuariosTags = {
                {"pedro", "geladeira", "fogao"},
                {"lucas", "mesa", "monitor"},
                {"ana", "cadeira", "mesa"},
                {"joao", "air fryer", "fogao"},
                {"maria", "cama", "notebook"},
                {"laura", "monitor", "cama", "air fryer"},
                {"rafael", "teclado"},
                {"matheus", "celular", "notebook"},
                {"roberto", "air fryer", "teclado"},
                {"fernanda", "cadeira", "celular"},
                {"lizandra", "cama", "geladeira"}
        };

        for (String[] usuarioTag : usuariosTags) {
            Usuario usuario = new Usuario(usuarioTag[0]);
            for (int i = 1; i < usuarioTag.length; i++) {
                usuario.adicionarTag(usuarioTag[i]);
            }
            grafo.adicionarUsuario(usuario);

        }
    }

    //LISTA DE PRODUTOS, PRIMEIRO VEM O NOME DO PRODUTO, QUE É GUARDADO APENAS COMO STRING, DEPOIS
    //VEM A TAG DO PRODUTO, QUE É O QUE VAI SER A ARESTA DE LIGAÇÃO DOS USUÁRIOS
    private static LinkedList<Produto> adicionarProdutos() {
        String[][] produtosTags = {
                {"Geladeira Brastemp", "geladeira"},
                {"Fogao 4 bocas", "fogao"},
                {"Mesa de escritorio", "mesa"},
                {"Cama ortoruim", "cama"},
                {"Monitor samsung", "monitor"},
                {"Monitor LG", "monitor"},
                {"Cadeira de escritorio", "cadeira"},
                {"Air Fryer top", "air fryer"},
                {"Air Fryer 250w", "air fryer"},
                {"Teclado Skyloong 60%", "teclado"},
                {"Notebook Lenovo Ideapad 3", "notebook"},
                {"Notebook dell inspiron", "notebook"},
                {"Samsung pocket", "celular"},
                {"Xiaomi redmi note 7", "celular"}
        };

        LinkedList<Produto> produtos = new LinkedList<>();
        for (String[] produtoTag : produtosTags) {
            Produto produto = new Produto(produtoTag[0]);
            produto.adicionarTag(produtoTag[1]);
            produtos.add(produto);
        }
        return produtos;
    }

}
