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

    //CONTA QUANTAS ARESTAS POSSUEM ENTRE DOIS USUÁRIOS,
    private int contarTagsEmComum(Usuario user1, Usuario user2) {
        int contador = 0;
        for (String tag : user1.getTags()) {
            if (user2.getTags().contains(tag)) {
                contador++;
            }
        }
        return contador;
    }

    //FAZ A CONEXÃO DO GRAFO ENTRE USUÁRIOS E PRODUTOS
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


        //VERIFICA OS PRODUTOS PRIORITÁRIOS, QUE SÃO OS PRODUTOS COMPRADOS PELOS USUÁRIOS QUE TEM
        //PRODUTOS EM COMUM COM O USUÁRIO PRINCIPAL
        for (Arestas aresta : arestas) {
            Usuario usuarioC = aresta.getUsuario();
            for (Produto produto : produtos) {
                if (usuarioC.getTags().containsAll(produto.getTagPrincipal())
                        && !produtosPrioritarios.contains(produto)
                        && !usuario.getTags().containsAll(produto.getTagPrincipal())) {
                    produtosPrioritarios.add(produto);
                }
            }
        }

        //VERIFICA OS PRODUTOS SECUNDÁRIOS, DE NÍVEL 2, QUE ESTÃO CONECTADOS COM OS PRODUTOS
        //JÁ CONECTADOS DOS USUÁRIOS ANTERIORES
        for (Arestas aresta : arestas) {
            Usuario usuarioPrimario = aresta.getUsuario();
            LinkedList<Arestas> produtosSecundarios = usuarioPrimario.getArestas();
            for (Arestas arestasSecundarias : produtosSecundarios) {
                Usuario usuarioSecundario = arestasSecundarias.getUsuario();
                if (!usuarioSecundario.equals(usuario)) {
                    for (Produto produto : produtos) {
                        if (usuarioSecundario.getTags().containsAll(produto.getTagPrincipal())
                                && !produtosPrioritarios.contains(produto)
                                && !produtosNaoPrioritarios.contains(produto)) {
                                produtosNaoPrioritarios.add(produto);
                        }
                    }
                }
            }
        }

        //ADICIONA TODOS OS PRODUTOS EM UMA LISTA SÓ EM ORDEM
        for (Produto p : produtosPrioritarios) {
            recomendacoes.add(p.nome);
        }

        for (Produto p : produtosNaoPrioritarios) {
            recomendacoes.add(p.nome);
        }

        //IMPRIME OS PRODUTOS PRIORITÁRIOS
        if (!produtosPrioritarios.isEmpty()) {
            System.out.print("Produtos Prioritários: ");
            for (int i = 0; i < produtosPrioritarios.size(); i++) {
                System.out.print(produtosPrioritarios.get(i).getNome() + " | ");
            }
            System.out.println();
        }

        //IMPRIME OS PRODUTOS SECUNDÁRIOS
        if (!produtosNaoPrioritarios.isEmpty()) {
            System.out.print("Produtos Secundários: ");
            for (int i = 0; i < produtosNaoPrioritarios.size(); i++) {
                System.out.print(produtosNaoPrioritarios.get(i).getNome() + " | ");
            }
            System.out.println();
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

}
