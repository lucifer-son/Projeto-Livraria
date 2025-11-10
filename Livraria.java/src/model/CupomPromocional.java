package model;

import java.util.Date;
import java.util.Objects;

public class CupomPromocional {
    private String id;
    private String codigo;
    private String tipoDesconto;
    private Date dataInicio;
    private Date dataFim;
    private double aplicacaoMinima;
    private int usoMaximo;

    public CupomPromocional(String id, String codigo, String tipoDesconto, Date dataInicio, Date dataFim, double aplicacaoMinima, int usoMaximo) {
        this.id = id;
        this.codigo = codigo;
        this.tipoDesconto = tipoDesconto;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.aplicacaoMinima = aplicacaoMinima;
        this.usoMaximo = usoMaximo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getTipoDesconto() {
        return tipoDesconto;
    }

    public void setTipoDesconto(String tipoDesconto) {
        this.tipoDesconto = tipoDesconto;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public Date getDataExpiracao() {
        return this.dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public double getAplicacaoMinima() {
        return aplicacaoMinima;
    }

    public void setAplicacaoMinima(double aplicacaoMinima) {
        this.aplicacaoMinima = aplicacaoMinima;
    }

    public int getUsoMaximo() {
        return usoMaximo;
    }

    public void setUsoMaximo(int usoMaximo) {
        this.usoMaximo = usoMaximo;
    }

    @Override
    public String toString() {
        return "CupomPromocional{" +
                "id='" + id + '\'' +
                ", codigo='" + codigo + '\'' +
                ", tipoDesconto='" + tipoDesconto + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CupomPromocional that = (CupomPromocional) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
