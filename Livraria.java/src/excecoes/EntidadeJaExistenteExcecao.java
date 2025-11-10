package excecoes;

public class EntidadeJaExistenteExcecao extends Exception {
    private final String idEntidade;

    public EntidadeJaExistenteExcecao(String idEntidade) {
        super("Entidade com ID " + idEntidade + " já existe.");
        this.idEntidade = idEntidade;
    }

    public EntidadeJaExistenteExcecao(String idEntidade, String mensagem) {
        super(mensagem);
        this.idEntidade = idEntidade;
    }

    public String getIdEntidade() {
        return idEntidade;
    }
}
