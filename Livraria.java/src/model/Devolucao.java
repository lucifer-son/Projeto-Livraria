package model;

import excecoes.OperacaoNaoPermitidaExcecao;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public class Devolucao {
    private String id;
    private Pedido pedido;
    private String motivo;
    private Date dataSolicitacao;
    private String status;

    public Devolucao(String id, String motivo, Date dataSolicitacao, String status) {
        this.id = id;
        this.motivo = motivo;
        this.dataSolicitacao = dataSolicitacao;
        this.status = status;
    }

    public Devolucao(Pedido pedido, String motivo) {
        this.id = UUID.randomUUID().toString();
        this.pedido = pedido;
        this.motivo = motivo;
        this.dataSolicitacao = new Date();
        this.status = "EM_ANALISE";
    }

    public Devolucao(String id, Pedido pedido, String motivo, Date dataSolicitacao, String status) {
        this.id = id;
        this.pedido = pedido;
        this.motivo = motivo;
        this.dataSolicitacao = dataSolicitacao;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Date getDataSolicitacao() {
        return dataSolicitacao;
    }

    public void setDataSolicitacao(Date dataSolicitacao) {
        this.dataSolicitacao = dataSolicitacao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void aprovar() throws OperacaoNaoPermitidaExcecao {
        if (!"EM_ANALISE".equalsIgnoreCase(this.status)) {
            throw new OperacaoNaoPermitidaExcecao(this.id, "aprovar devolução", this.status);
        }
        this.status = "APROVADA";
    }

    public void recusar() throws OperacaoNaoPermitidaExcecao {
        if (!"EM_ANALISE".equalsIgnoreCase(this.status)) {
            throw new OperacaoNaoPermitidaExcecao(this.id, "recusar devolução", this.status);
        }
        this.status = "RECUSADA";
    }

    @Override
    public String toString() {
        return "Devolucao{" +
                "id='" + id + '\'' +
                ", motivo='" + motivo + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Devolucao devolucao = (Devolucao) o;
        return Objects.equals(id, devolucao.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
