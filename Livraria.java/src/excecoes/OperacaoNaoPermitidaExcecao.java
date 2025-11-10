package excecoes;

public class OperacaoNaoPermitidaExcecao extends Exception {
    private final String idEntidade;
    private final String operacaoTentada;
    private final String estadoAtual;

    public OperacaoNaoPermitidaExcecao(String idEntidade, String operacaoTentada, String estadoAtual) {
        super("Operação \"" + operacaoTentada + "\" não permitida para a entidade ID " + idEntidade +
              " no estado atual: " + estadoAtual + ".");
        this.idEntidade = idEntidade;
        this.operacaoTentada = operacaoTentada;
        this.estadoAtual = estadoAtual;
    }

    public String getIdEntidade() {
        return idEntidade;
    }

    public String getOperacaoTentada() {
        return operacaoTentada;
    }

    public String getEstadoAtual() {
        return estadoAtual;
    }
}
