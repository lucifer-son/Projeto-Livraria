package excecoes;


public class EstoqueInvalidoExcecao extends Exception {
    private final String idLivro;
    private final int estoqueTentado;

    public EstoqueInvalidoExcecao(String idLivro, int estoqueTentado) {
        super("Estoque inválido para o livro ID " + idLivro + ": " + estoqueTentado + ". O estoque não pode ser negativo.");
        this.idLivro = idLivro;
        this.estoqueTentado = estoqueTentado;
    }

    public EstoqueInvalidoExcecao(String idLivro, int estoqueTentado, String mensagem) {
        super(mensagem);
        this.idLivro = idLivro;
        this.estoqueTentado = estoqueTentado;
    }

    public String getIdLivro() {
        return idLivro;
    }

    public int getEstoqueTentado() {
        return estoqueTentado;
    }
}

