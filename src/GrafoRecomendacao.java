import java.util.LinkedList;

class GrafoRecomendacao {
    private LinkedList<Usuario> usuarios;

    public GrafoRecomendacao() {
        usuarios = new LinkedList<>();
    }

    public void adicionarUsuario(Usuario usuario) {
        usuarios.add(usuario);
        conectarUsuariosPorTags(usuario);
    }

    private void conectarUsuariosPorTags(Usuario novoUsuario) {
        for (Usuario usuario : usuarios) {
            if (!usuario.equals(novoUsuario) && temTagsEmComum(novoUsuario, usuario)) {
                novoUsuario.getArestas().add(usuario);
                usuario.getArestas().add(novoUsuario);
            }
        }
    }

    public LinkedList<String> recomendarProdutos(Usuario usuario, LinkedList<Produto> produtos) {
        LinkedList<String> recomendacoes = new LinkedList<>();
        LinkedList<Usuario> arestas = usuario.getArestas();
        LinkedList<Produto> produtosPrioritarios = new LinkedList<>();
        LinkedList<Produto> produtosNaoPrioritarios = new LinkedList<>();

        //TEMOS UMA LISTA DE PRODUTOS PRIORITÁRIOS E NÃO PRIORITÁRIOS, TODOS OS PRODUTOS QUE TEM A
        //MESMA TAG APARECERÃO COMO PRIORITÁRIOS E NA LISTA FICARÃO ANTES DOS OUTROS

        for (Produto produto : produtos) {
            if (usuario.getTags().containsAll(produto.getTags()) && !produtosPrioritarios.contains(produto)) {
                produtosPrioritarios.add(produto);
            }
        }

        for (Usuario aresta : arestas) {
            for (Produto produto : produtos) {
                if (aresta.getTags().containsAll(produto.getTags()) && !produtosPrioritarios.contains(produto)) {
                    produtosNaoPrioritarios.add(produto);
                }
            }
        }


        for (Produto p : produtosPrioritarios) {
            recomendacoes.add(p.nome);
        }


        for (Produto p : produtosNaoPrioritarios) {
            System.out.println(p.nome);
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

    public LinkedList<Usuario> getUsuarios() {
        return usuarios;
    }
}
