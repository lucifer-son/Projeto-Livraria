package model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class RelatorioVendas {

    public static class VendaResumo {
        private Date data;
        private long totalPedidos;
        private double receita;

        public VendaResumo(Date data, long totalPedidos, double receita) {
            this.data = data;
            this.totalPedidos = totalPedidos;
            this.receita = receita;
        }

        public Date getData() {
            return data;
        }

        public long getTotalPedidos() {
            return totalPedidos;
        }

        public double getReceita() {
            return receita;
        }

        @Override
        public String toString() {
            return "VendaResumo{" +
                    "data=" + data +
                    ", totalPedidos=" + totalPedidos +
                    ", receita=" + receita +
                    '}';
        }
    }

    public List<VendaResumo> gerarResumoSimulado(Date inicio, Date fim) {
        List<VendaResumo> lista = new ArrayList<>();

        Calendar c = Calendar.getInstance();
        c.setTime(inicio);

        while (!c.getTime().after(fim)) {
            // só simular valores aleatórios ou fixos
            long pedidos = (long) (Math.random() * 10 + 1);
            double receita = pedidos * 100.0;  // supondo valor médio de 100
            lista.add(new VendaResumo(c.getTime(), pedidos, receita));
            c.add(Calendar.DATE, 1);
        }

        return lista;
    }
}
