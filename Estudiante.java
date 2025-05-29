import java.util.ArrayList;
import java.util.List;

public class Estudiante {
    private int id;
    private String nombre;
    private List<Double> notas;

    public Estudiante(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.notas = new ArrayList<>();
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public List<Double> getNotas() { return notas; }

    public void agregarNota(double nota) {
        notas.add(nota);
    }

    public boolean eliminarNota(int index) {
        if (index >= 0 && index < notas.size()) {
            notas.remove(index);
            return true;
        }
        System.out.println("Índice de nota inválido.");
        return false;
    }

    public void actualizarNota(int index, double nuevaNota) {
        if (index >= 0 && index < notas.size()) {
            notas.set(index, nuevaNota);
        } else {
            System.out.println("Índice de nota inválido.");
        }
    }

    public double getTotalNotas() {
        return notas.stream().mapToDouble(Double::doubleValue).sum();
    }

    public double getPromedioNotas() {
        if (notas.isEmpty()) return 0.0;
        return getTotalNotas() / notas.size();
    }
}