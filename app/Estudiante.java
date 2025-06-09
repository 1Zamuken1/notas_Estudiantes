package app;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import logica.Componente;

public class Estudiante extends Usuario {

    public Estudiante(String nombre, String apellido, char[] password) {
        super(contadorId++, nombre, apellido, Rol.estudiante, password);
    }

    private static List<Estudiante> estudiantes = new ArrayList<>();

    public static void agregarEstudiante(Estudiante estudiante) {
        estudiantes.add(estudiante);
        
        // Tabla de confirmación de registro de estudiante
        System.out.println("┌──────────────────────────────────────────────────────┐");
        System.out.println("│                ESTUDIANTE REGISTRADO                 │");
        System.out.println("├──────────────────────────────────────────────────────┤");
        System.out.println("│ ID: " + String.format("%-47s", estudiante.getId()) + "│");
        System.out.println("│ Nombre: " + String.format("%-43s", truncarTexto(estudiante.getNombre(), 43)) + "│");
        System.out.println("│ Apellido: " + String.format("%-41s", truncarTexto(estudiante.getApellido(), 41)) + "│");
        System.out.println("│ Rol: " + String.format("%-46s", "Estudiante") + "│");
        System.out.println("│ Total estudiantes: " + String.format("%-32s", estudiantes.size()) + "│");
        System.out.println("└──────────────────────────────────────────────────────┘");
    }

    // Mapa: Componente -> Lista de Notas
    private Map<Componente, List<Nota>> notasPorComponente = new HashMap<>();

    public void agregarNota(Componente componente, Nota nota) {
        notasPorComponente.computeIfAbsent(componente, k -> new ArrayList<>()).add(nota);
    }

    public List<Nota> getNotasPorComponente(Componente componente) {
        return notasPorComponente.getOrDefault(componente, new ArrayList<>());
    }

    public Map<Componente, List<Nota>> getNotasPorComponente() {
        return notasPorComponente;
    }

    @Override
    public String toString() {
        // Crear representación en formato tabla elegante
        StringBuilder sb = new StringBuilder();
        
        String apellidoMostrar = (getApellido() != null && !getApellido().trim().isEmpty()) 
                                ? getApellido() : "No especificado";
        
        sb.append("┌────────────────────────────────────────────────────────┐\n");
        sb.append("│                      ESTUDIANTE                        │\n");
        sb.append("├────────────────────────────────────────────────────────┤\n");
        sb.append("│ ID: ").append(String.format("%-47s", getId())).append("│\n");
        sb.append("│ Nombre: ").append(String.format("%-43s", truncarTexto(getNombre(), 43))).append("│\n");
        sb.append("│ Apellido: ").append(String.format("%-41s", truncarTexto(apellidoMostrar, 41))).append("│\n");
        sb.append("│ Rol: ").append(String.format("%-46s", "Estudiante")).append("│\n");
        
        // Información académica
        int totalComponentes = notasPorComponente.size();
        int totalNotas = notasPorComponente.values().stream()
                        .mapToInt(List::size)
                        .sum();
        
        sb.append("│ Componentes inscritos: ").append(String.format("%-30s", totalComponentes)).append("│\n");
        sb.append("│ Total de notas: ").append(String.format("%-37s", totalNotas)).append("│\n");
        sb.append("│ Estado: ").append(String.format("%-43s", "Activo")).append("│\n");
        sb.append("└────────────────────────────────────────────────────────┘");
        
        return sb.toString();
    }
    
    /**
     * Método para mostrar información básica en una línea (para listados)
     * @return String con formato compacto para tablas de listado
     */
    public String toStringCompacto() {
        String apellidoCompacto = (getApellido() != null && !getApellido().trim().isEmpty()) 
                                 ? getApellido() : "N/A";
        int componentes = notasPorComponente.size();
        return String.format("%-4d │ %-20s │ %-20s │ %-6s │ %-4d", 
                           getId(), 
                           truncarTexto(getNombre(), 20),
                           truncarTexto(apellidoCompacto, 20),
                           "Est.",
                           componentes);
    }
    
    /**
     * Método para crear encabezado de tabla para listados de estudiantes
     * @return String con el encabezado de tabla
     */
    public static String getEncabezadoTabla() {
        StringBuilder sb = new StringBuilder();
        sb.append("┌──────┬──────────────────────┬──────────────────────┬────────┬──────┐\n");
        sb.append("│  ID  │        NOMBRE        │       APELLIDO       │  ROL   │ COMP │\n");
        sb.append("├──────┼──────────────────────┼──────────────────────┼────────┼──────┤");
        return sb.toString();
    }
    
    /**
     * Método para crear pie de tabla para listados de estudiantes
     * @return String con el pie de tabla
     */
    public static String getPieTabla() {
        return "└──────┴──────────────────────┴──────────────────────┴────────┴──────┘";
    }
    
    /**
     * Método auxiliar para truncar texto si es demasiado largo
     * @param texto El texto a truncar
     * @param longitudMaxima La longitud máxima permitida
     * @return String truncado si es necesario
     */
    private static String truncarTexto(String texto, int longitudMaxima) {
        if (texto == null) return "";
        if (texto.length() <= longitudMaxima) {
            return texto;
        }
        return texto.substring(0, longitudMaxima - 3) + "...";
    }
}