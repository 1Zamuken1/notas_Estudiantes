public class Nota {
    private float nota;
    private float porcentaje;

    public Nota(float nota, float porcentaje) {
        this.nota = nota;
        this.porcentaje = porcentaje;
    }

    public float getNota() {
        return nota;
    }

    public float getPorcentaje() {
        return porcentaje;
    }

    public float getValorPorcentual() {
        return (nota * porcentaje) / 100;
    }
}
