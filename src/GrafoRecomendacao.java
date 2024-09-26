import java.util.LinkedList;

public class GrafoRecomendacao {
    private LinkedList<Usuario> usuarios;

    public GrafoRecomendacao() {
        usuarios = new LinkedList<>();
    }

    public void adicionarUsuario(Usuario usuario) {
        usuarios.add(usuario);
        conectarUsuariosPorTags(usuario);
    }

    private int contarTagsEmComum(Usuario user1, Usuario user2) {
        int contador = 0;
        for (String tag : user1.getTags()) {
            if (user2.getTags().contains(tag)) {
                contador++;
            }
        }
        return contador;
    }

    private void conectarUsuariosPorTags(Usuario novoUsuario) {
        for (Usuario usuario : usuarios) {
            if (!usuario.equals(novoUsuario)) {
                int tagsEmComum = contarTagsEmComum(novoUsuario, usuario);
                if (tagsEmComum > 0) {
                    novoUsuario.getArestas().add(new Arestas(usuario, tagsEmComum));
                    usuario.getArestas().add(new Arestas(novoUsuario, tagsEmComum));
                }
            }
        }
    }


    public LinkedList<String> recomendarProdutos(Usuario usuario, LinkedList<Produto> produtos) {
        LinkedList<String> recomendacoes = new LinkedList<>();
        LinkedList<Arestas> arestas = usuario.getArestas();
        LinkedList<Produto> produtosPrioritarios = new LinkedList<>();
        LinkedList<Produto> produtosNaoPrioritarios = new LinkedList<>();

        //TEMOS UMA LISTA DE PRODUTOS PRIORITÁRIOS E NÃO PRIORITÁRIOS, TODOS OS PRODUTOS QUE TEM A
        //MESMA TAG APARECERÃO COMO PRIORITÁRIOS E NA LISTA FICARÃO ANTES DOS OUTROS

        for (Produto produto : produtos) {
            if (usuario.getTags().containsAll(produto.getTagPrincipal()) && !produtosPrioritarios.contains(produto)) {
                produtosPrioritarios.add(produto);
            }
        }

        for (Arestas aresta : arestas) {
            Usuario usuarioC = aresta.getUsuario();
            for (Produto produto : produtos) {
                if (usuarioC.getTags().containsAll(produto.getTagPrincipal()) && !produtosPrioritarios.contains(produto)) {
                    produtosNaoPrioritarios.add(produto);
                }
            }
        }

        for (Produto p : produtosPrioritarios) {
            recomendacoes.add(p.nome);
        }
        for (Produto p : produtosNaoPrioritarios) {
            recomendacoes.add(p.nome);
        }

        return recomendacoes;

    }

    private boolean temTagsEmComum(Usuario u1, Usuario u2) {
        for (String tag : u1.getTags()) {
            if (u2.getTags().contains(tag)) {
                return true;
            }
        }
        return false;
    }

    //PERCORRER A LISTA DE USUÁRIOS PARA PROCURAR O USUÁRIO REQUERIDO
    public Usuario getUsuario(String nome) {
        for (Usuario usuario : usuarios) {
            if (usuario.getNome().equals(nome)) {
                return usuario;
            }
        }
        return null;
    }

    //FUNCAO PARA IMPRIMIR AS ARESTAS / ADJACENCIAS DO VERTICES (USUARIOS)
    public void imprimirConexoes() {
        int contadorVertices = 1;
        for (Usuario usuario : usuarios) {
            System.out.print("Vertice " + contadorVertices + ": " + usuario.getNome());
            contadorVertices++;
            for (Arestas aresta : usuario.getArestas()) {
                System.out.print(" -> " + aresta.getUsuario().getNome());
            }
            System.out.println();
        }
    }

    //FUNCAO PARA IMPRIMIR MAIS BONITINHO AS ARESTAS (PRODUTOS)
    public void imprimirProdutosRecomendados(Usuario usuario, LinkedList<Produto> produtos) {
        LinkedList<Produto> produtosPrioritarios = new LinkedList<>();
        LinkedList<Produto> produtosNaoPrioritarios = new LinkedList<>();

        for (Produto produto : produtos) {
            if (usuario.getTags().containsAll(produto.getTagPrincipal()) && !produtosPrioritarios.contains(produto)) {
                produtosPrioritarios.add(produto);
            }
        }

        for (Arestas aresta : usuario.getArestas()) {
            Usuario usuarioConectado = aresta.getUsuario();
            for (Produto produto : produtos) {
                if (usuarioConectado.getTags().containsAll(produto.getTagPrincipal()) && !produtosPrioritarios.contains(produto)) {
                    produtosNaoPrioritarios.add(produto);
                }
            }
        }

        if (!produtosPrioritarios.isEmpty()) {
            System.out.print("Produtos Prioritários: ");
            for (int i = 0; i < produtosPrioritarios.size(); i++) {
                System.out.print(produtosPrioritarios.get(i).getNome() + " | ");
            }
            System.out.println();
        }

        if (!produtosNaoPrioritarios.isEmpty()) {
            System.out.print("Produtos Secundários: ");
            for (int i = 0; i < produtosNaoPrioritarios.size(); i++) {
                System.out.print(produtosNaoPrioritarios.get(i).getNome() + " | ");
            }
            System.out.println();
        }
    }
}
