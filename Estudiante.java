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

    public void mostrarTablaNotas() {
        System.out.println("\n" + "=".repeat(50));
        System.out.printf("                 ESTUDIANTE %s\n", getIdentificacion());
        System.out.println("=".repeat(50));
        
        // Encabezado de la tabla
        System.out.println("┌─────────┬────────────┬─────────────────┐");
        System.out.println("│ vrNota  │ porcentaje │ vrNotaPorcentaje│");
        System.out.println("├─────────┼────────────┼─────────────────┤");
        
        // Filas de notas
        for (Nota nota : notas) {
            System.out.printf("│  %4.1f   │     %2.0f     │      %5.2f      │%n",
                nota.getNota(),
                nota.getPorcentaje(), 
                nota.getValorPorcentual());
        }
        
        System.out.println("└─────────┴────────────┴─────────────────┘");
        
        // Información final
        System.out.println("┌─────────────┬─────────┐");
        System.out.printf("│ notaFinal   │  %5.2f  │%n", getNotaFinal());
        System.out.printf("│ cantNotas   │    %d    │%n", notas.size());
        System.out.println("└─────────────┴─────────┘");
        
        // Estado del estudiante
        String estado = estaAprobado() ? "APROBADO" : "REPROBADO";
        System.out.printf("\nEstado académico: %s\n", estado);
        
        if (getNotaFinal() >= 9.0) {
            System.out.println("🏆 ¡EXCELENTE RENDIMIENTO!");
        } else if (getNotaFinal() >= 8.0) {
            System.out.println("⭐ ¡MUY BUEN RENDIMIENTO!");
        } else if (getNotaFinal() >= 7.0) {
            System.out.println("✓ Buen rendimiento");
        } else if (getNotaFinal() >= 6.0) {
            System.out.println("✓ Rendimiento aceptable");
        } else {
            System.out.println("⚠️  Necesita mejorar");
        }
    }

    public void imprimirNotas() {
        mostrarTablaNotas();
    }

    // Getters
    public String getIdentificacion() {
        return identificacion;
    }

    public String getNombreCompleto() {
        return nombre + " " + apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public Componente getComponente() {
        return componente;
    }

    public List<Nota> getNotas() {
        return new ArrayList<>(notas); // Retorna una copia para proteger la encapsulación
    }

    public int getCantidadNotas() {
        return notas.size();
    }

    public boolean estaAprobado() {
        return getNotaFinal() >= 6.0;
    }
}