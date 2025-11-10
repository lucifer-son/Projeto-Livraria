package excecoes;

/**
 * Exceção lançada quando uma operação tenta definir um valor de estoque inválido (ex: negativo).
 * É uma exceção checada (checked exception), exigindo tratamento ou declaração.
 */
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
