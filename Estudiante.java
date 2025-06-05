import java.util.*;

public class Estudiante {
    private String identificacion;
    private String nombre;
    private String apellido;
    private Componente componente;
    private List<Nota> notas;

    public Estudiante(String identificacion, String nombre, String apellido, Componente componente) {
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.apellido = apellido;
        this.componente = componente;
        this.notas = new ArrayList<>();
    }

    public void agregarNota(Nota nota) {
        notas.add(nota);
    }

    public float getNotaFinal() {
        float suma = 0;
        for (Nota nota : notas) {
            suma += nota.getValorPorcentual();
        }
        return suma;
    }

    public void imprimirNotas() {
        System.out.println("Estudiante ID: " + identificacion);
        System.out.println("Nombre: " + getNombreCompleto());
        System.out.println("ID: " + componente.getCodigoComponente());
        System.out.println("Nombre: " + componente.getNombreComponente());
        System.out.println("Profesor: " + componente.getProfesor());
        System.out.println("Notas:");
        for (Nota nota : notas) {
            System.out.printf("Nota: %.1f | Porcentaje: %.1f%%\n", nota.getNota(), nota.getPorcentaje());
        }
        System.out.println("Cantidad de notas: " + notas.size());
        System.out.printf("Nota Final: %.1f\n", getNotaFinal());
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public String getNombreCompleto() {
        return nombre + " " + apellido;
    }
}
