package logica;
import java.util.ArrayList;
import java.util.List;

import app.Estudiante;
import app.Profesor;

public class Componente {
    private int id;
    private static int contadorId = 1;
    private String nombre;
    private Profesor profesor;
    private List<Estudiante> estudiantes = new ArrayList<>();

    public Componente(String nombre, Profesor profesor) {
        this.id = contadorId++;
        this.nombre = nombre;
        this.profesor = profesor;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public boolean agregarEstudiante(Estudiante estudiante) {
        if (!estudiantes.contains(estudiante)) {
            estudiantes.add(estudiante);
            return true;
        }
        return false;
    }

    public List<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    @Override
    public String toString() {
        // Crear representación en formato tabla elegante
        StringBuilder sb = new StringBuilder();
        
        // Determinar información del profesor
        String profesorInfo = "No asignado";
        if (profesor != null) {
            String apellidoProf = (profesor.getApellido() != null && !profesor.getApellido().trim().isEmpty()) 
                                 ? profesor.getApellido() : "";
            profesorInfo = profesor.getNombre() + 
                          (!apellidoProf.isEmpty() ? " " + apellidoProf : "");
        }
        
        sb.append("┌────────────────────────────────────────────────────────┐\n");
        sb.append("│                      COMPONENTE                        │\n");
        sb.append("├────────────────────────────────────────────────────────┤\n");
        sb.append("│ ID: ").append(String.format("%-47s", id)).append("│\n");
        sb.append("│ Nombre: ").append(String.format("%-43s", truncarTexto(nombre, 43))).append("│\n");
        sb.append("│ Profesor: ").append(String.format("%-41s", truncarTexto(profesorInfo, 41))).append("│\n");
        sb.append("│ Estudiantes inscritos: ").append(String.format("%-30s", estudiantes.size())).append("│\n");
        
        // Estado del componente
        String estado = (profesor != null) ? "Activo" : "Pendiente asignación";
        sb.append("│ Estado: ").append(String.format("%-43s", estado)).append("│\n");
        
        // Información adicional
        String capacidad = estudiantes.size() > 0 ? 
                          String.format("%d/%d", estudiantes.size(), 50) : "0/50";
        sb.append("│ Capacidad: ").append(String.format("%-40s", capacidad)).append("│\n");
        sb.append("└────────────────────────────────────────────────────────┘");
        
        return sb.toString();
    }
    
    /**
     * Método para mostrar información básica en una línea (para listados)
     * @return String con formato compacto para tablas de listado
     */
    public String toStringCompacto() {
        String profesorCompacto = "Sin asignar";
        if (profesor != null) {
            String apellidoProf = (profesor.getApellido() != null && !profesor.getApellido().trim().isEmpty()) 
                                 ? " " + profesor.getApellido() : "";
            profesorCompacto = truncarTexto(profesor.getNombre() + apellidoProf, 18);
        }
        
        return String.format("%-4d │ %-20s │ %-18s │ %-4d", 
                           id, 
                           truncarTexto(nombre, 20),
                           profesorCompacto,
                           estudiantes.size());
    }
    
    /**
     * Método para crear encabezado de tabla para listados de componentes
     * @return String con el encabezado de tabla
     */
    public static String getEncabezadoTabla() {
        StringBuilder sb = new StringBuilder();
        sb.append("┌──────┬──────────────────────┬──────────────────┬──────┐\n");
        sb.append("│  ID  │        NOMBRE        │     PROFESOR     │ EST. │\n");
        sb.append("├──────┼──────────────────────┼──────────────────┼──────┤");
        return sb.toString();
    }
    
    /**
     * Método para crear pie de tabla para listados de componentes
     * @return String con el pie de tabla
     */
    public static String getPieTabla() {
        return "└──────┴──────────────────────┴──────────────────┴──────┘";
    }
    
    /**
     * Método para mostrar información detallada del componente
     */
    public void mostrarDetalles() {
        System.out.println("┌────────────────────────────────────────────────────────┐");
        System.out.println("│                  DETALLES COMPONENTE                   │");
        System.out.println("├────────────────────────────────────────────────────────┤");
        System.out.println("│ ID: " + String.format("%-47s", id) + "│");
        System.out.println("│ Nombre: " + String.format("%-43s", truncarTexto(nombre, 43)) + "│");
        
        if (profesor != null) {
            String profesorCompleto = profesor.getNombre();
            if (profesor.getApellido() != null && !profesor.getApellido().trim().isEmpty()) {
                profesorCompleto += " " + profesor.getApellido();
            }
            System.out.println("│ Profesor asignado: " + String.format("%-32s", 
                              truncarTexto(profesorCompleto, 32)) + "│");
            System.out.println("│ ID Profesor: " + String.format("%-38s", profesor.getId()) + "│");
        } else {
            System.out.println("│ Profesor asignado: " + String.format("%-32s", "No asignado") + "│");
        }
        
        System.out.println("├────────────────────────────────────────────────────────┤");
        System.out.println("│ Estudiantes inscritos: " + String.format("%-30s", estudiantes.size()) + "│");
        System.out.println("│ Capacidad máxima: " + String.format("%-35s", "50 estudiantes") + "│");
        System.out.println("│ Espacios disponibles: " + String.format("%-30s", (50 - estudiantes.size())) + "│");
        
        String porcentaje = String.format("%.1f%%", (estudiantes.size() / 50.0) * 100);
        System.out.println("│ Ocupación: " + String.format("%-40s", porcentaje) + "│");
        
        System.out.println("└────────────────────────────────────────────────────────┘");
    }
    
    /**
     * Método para mostrar lista de estudiantes inscritos
     */
    public void mostrarEstudiantes() {
        if (estudiantes.isEmpty()) {
            System.out.println("┌────────────────────────────────────────────────────────┐");
            System.out.println("│                      ATENCIÓN                         │");
            System.out.println("├────────────────────────────────────────────────────────┤");
            System.out.println("│         No hay estudiantes inscritos en este          │");
            System.out.println("│                     componente                         │");
            System.out.println("└────────────────────────────────────────────────────────┘");
            return;
        }
        
        System.out.println("┌────────────────────────────────────────────────────────┐");
        System.out.println("│              ESTUDIANTES DEL COMPONENTE               │");
        System.out.println("│ " + String.format("%-52s", nombre) + " │");
        System.out.println("├──────┬─────────────────────────┬─────────────────────────┤");
        System.out.println("│  ID  │         NOMBRE          │        APELLIDO         │");
        System.out.println("├──────┼─────────────────────────┼─────────────────────────┤");
        
        for (Estudiante estudiante : estudiantes) {
            String apellidoEst = (estudiante.getApellido() != null && !estudiante.getApellido().trim().isEmpty()) 
                               ? estudiante.getApellido() : "No especificado";
            System.out.println("│ " + String.format("%-4d", estudiante.getId()) + 
                             " │ " + String.format("%-23s", truncarTexto(estudiante.getNombre(), 23)) + 
                             " │ " + String.format("%-23s", truncarTexto(apellidoEst, 23)) + " │");
        }
        
        System.out.println("└──────┴─────────────────────────┴─────────────────────────┘");
        System.out.println("┌────────────────────────────────────────────────────────┐");
        System.out.println("│ Total estudiantes: " + String.format("%-34s", estudiantes.size()) + "│");
        System.out.println("└────────────────────────────────────────────────────────┘");
    }
    
    /**
     * Método auxiliar para truncar texto si es demasiado largo
     * @param texto El texto a truncar
     * @param longitudMaxima La longitud máxima permitida
     * @return String truncado si es necesario
     */
    private String truncarTexto(String texto, int longitudMaxima) {
        if (texto == null) return "";
        if (texto.length() <= longitudMaxima) {
            return texto;
        }
        return texto.substring(0, longitudMaxima - 3) + "...";
    }
}