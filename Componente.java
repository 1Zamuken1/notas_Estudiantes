public class Componente {
    private String codigoComponente;
    private String nombreComponente;
    private String profesor;

    public Componente(String codigoComponente, String nombre, String profesor) {
        this.codigoComponente = codigoComponente;
        this.nombreComponente = nombre;
        this.profesor = profesor;
    }

    public String getCodigoComponente() {
        return codigoComponente;
    }

    public String getNombreComponente() {
        return nombreComponente;
    }

    public String getProfesor() {
        return profesor;
    }
}
