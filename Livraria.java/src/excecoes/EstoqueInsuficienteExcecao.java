package excecoes;

public class EstoqueInsuficienteExcecao extends Exception {
    private final String idLivro;
    private final int quantidadeSolicitada;
    private final int estoqueDisponivel;

    public EstoqueInsuficienteExcecao(String idLivro, int quantidadeSolicitada, int estoqueDisponivel) {
        super("Estoque insuficiente para o livro ID " + idLivro +
              ". Solicitado: " + quantidadeSolicitada + ", Disponível: " + estoqueDisponivel + ".");
        this.idLivro = idLivro;
        this.quantidadeSolicitada = quantidadeSolicitada;
        this.estoqueDisponivel = estoqueDisponivel;
    }

    public EstoqueInsuficienteExcecao(String idLivro, int quantidadeSolicitada, int estoqueDisponivel, String mensagem) {
        super(mensagem);
        this.idLivro = idLivro;
        this.quantidadeSolicitada = quantidadeSolicitada;
        this.estoqueDisponivel = estoqueDisponivel;
    }

    public String getIdLivro() {
        return idLivro;
    }

    public int getQuantidadeSolicitada() {
        return quantidadeSolicitada;
    }

    public int getEstoqueDisponivel() {
        return estoqueDisponivel;
    }
}

