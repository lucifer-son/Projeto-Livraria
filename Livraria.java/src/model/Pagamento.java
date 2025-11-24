package model;

import java.util.Date;
import java.util.Objects;

public class Pagamento {
    private String id;
    private String pedidoId;
    private String tipo;
    private String dados;
    private String status;
    private Date data;
    private double valorTotal;

    public Pagamento(String id, String pedidoId, String tipo, String dados, String status, Date data, double valorTotal) {
        this.id = id;
        this.pedidoId = pedidoId;
        this.tipo = tipo;
        this.dados = dados;
        this.status = status;
        this.data = data;
        this.valorTotal = valorTotal;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(String pedidoId) {
        this.pedidoId = pedidoId;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDados() {
        return dados;
    }

    public void setDados(String dados) {
        this.dados = dados;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    @Override
    public String toString() {
        return "Pagamento{" +
                "id='" + id + '\'' +
                ", pedidoId='" + pedidoId + '\'' +
                ", tipo='" + tipo + '\'' +
                ", status='" + status + '\'' +
                ", data=" + data +
                ", valorTotal=" + valorTotal +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pagamento pagamento = (Pagamento) o;
        return Objects.equals(id, pagamento.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
