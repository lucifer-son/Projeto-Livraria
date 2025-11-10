package excecoes;

public class EntidadeNaoEncontradaExcecao extends Exception {
    private final String idEntidade;

    public EntidadeNaoEncontradaExcecao(String idEntidade) {
        super("Entidade com ID " + idEntidade + " não encontrada.");
        this.idEntidade = idEntidade;
    }

    public EntidadeNaoEncontradaExcecao(String idEntidade, String mensagem) {
        super(mensagem);
        this.idEntidade = idEntidade;
    }

    public String getIdEntidade() {
        return idEntidade;
    }
}
