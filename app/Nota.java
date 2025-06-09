package app;
public class Nota {
    private double valorNota;
    private int porcentaje;

    public Nota(double valorNota, int porcentaje) {
        this.valorNota = valorNota;
        this.porcentaje = porcentaje;
    }

    public double getValorNota() {
        return valorNota;
    }

    public int getPorcentaje() {
        return porcentaje;
    }

    public double getValorNotaPorcentaje() {
        return valorNota * porcentaje / 100.0;
    }
}