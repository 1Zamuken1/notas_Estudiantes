public class Profesor {
    private int id;
    private String nombre;

    public Profesor(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }

    public double getTotalNotasEstudiantes(Curso curso) {
        double total = 0;
        for (Estudiante estudiante : curso.getEstudiantes()) {
            total += estudiante.getTotalNotas();
        }
        return total;
    }

    public double getPromedioNotasEstudiantes(Curso curso) {
        if (curso.getEstudiantes().isEmpty()) return 0.0;
        double sumaPromedios = 0;
        for (Estudiante estudiante : curso.getEstudiantes()) {
            sumaPromedios += estudiante.getPromedioNotas();
        }
        return sumaPromedios / curso.getEstudiantes().size();
    }
}