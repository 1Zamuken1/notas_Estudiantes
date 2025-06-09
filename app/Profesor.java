package app;
import java.util.ArrayList;
import java.util.List;

public class Profesor extends Usuario {

    // Lista estática para almacenar todos los profesores
    private static List<Profesor> profesores = new ArrayList<>();

    public Profesor(String nombre, String apellido, char[] password) {
        super(contadorId++, nombre, apellido, Rol.profesor, password);
    }

    // Método para agregar un profesor a la lista
    public static void agregarProfesor(Profesor profesor) {
        profesores.add(profesor);
        
        // Tabla de confirmación de registro de profesor
        System.out.println("┌──────────────────────────────────────────────────────┐");
        System.out.println("│                 PROFESOR REGISTRADO                  │");
        System.out.println("├──────────────────────────────────────────────────────┤");
        System.out.println("│ ID: " + String.format("%-47s", profesor.getId()) + "│");
        System.out.println("│ Nombre: " + String.format("%-43s", truncarTexto(profesor.getNombre(), 43)) + "│");
        System.out.println("│ Apellido: " + String.format("%-41s", truncarTexto(profesor.getApellido(), 41)) + "│");
        System.out.println("│ Rol: " + String.format("%-46s", "Profesor") + "│");
        System.out.println("│ Total profesores: " + String.format("%-33s", profesores.size()) + "│");
        System.out.println("└──────────────────────────────────────────────────────┘");
    }

    // Método para listar todos los profesores
    public static void listarProfesores() {
        if (profesores.isEmpty()) {
            System.out.println("┌──────────────────────────────────────────────────────┐");
            System.out.println("│                      ATENCIÓN                       │");
            System.out.println("├──────────────────────────────────────────────────────┤");
            System.out.println("│             No hay profesores registrados           │");
            System.out.println("└──────────────────────────────────────────────────────┘");
            return;
        }

        System.out.println("┌──────┬─────────────────────────┬─────────────────────────┐");
        System.out.println("│  ID  │         NOMBRE          │        APELLIDO         │");
        System.out.println("├──────┼─────────────────────────┼─────────────────────────┤");
        
        for (Profesor p : profesores) {
            String apellidoMostrar = (p.getApellido() != null && !p.getApellido().trim().isEmpty()) 
                                   ? p.getApellido() : "No especificado";
            System.out.println("│ " + String.format("%-4d", p.getId()) + 
                             " │ " + String.format("%-23s", truncarTexto(p.getNombre(), 23)) + 
                             " │ " + String.format("%-23s", truncarTexto(apellidoMostrar, 23)) + " │");
        }
        
        System.out.println("└──────┴─────────────────────────┴─────────────────────────┘");
        
        // Información adicional
        System.out.println("┌──────────────────────────────────────────────────────┐");
        System.out.println("│ Total de profesores registrados: " + String.format("%-17s", profesores.size()) + "│");
        System.out.println("└──────────────────────────────────────────────────────┘");
    }

    // Método para buscar un profesor por ID
    public static Profesor buscarProfesorPorId(int id) {
        for (Profesor p : profesores) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        // Crear representación en formato tabla elegante
        StringBuilder sb = new StringBuilder();
        
        String apellidoMostrar = (getApellido() != null && !getApellido().trim().isEmpty()) 
                                ? getApellido() : "No especificado";
        
        sb.append("┌────────────────────────────────────────────────────────┐\n");
        sb.append("│                       PROFESOR                         │\n");
        sb.append("├────────────────────────────────────────────────────────┤\n");
        sb.append("│ ID: ").append(String.format("%-47s", getId())).append("│\n");
        sb.append("│ Nombre: ").append(String.format("%-43s", truncarTexto(getNombre(), 43))).append("│\n");
        sb.append("│ Apellido: ").append(String.format("%-41s", truncarTexto(apellidoMostrar, 41))).append("│\n");
        sb.append("│ Rol: ").append(String.format("%-46s", "Profesor")).append("│\n");
        sb.append("│ Estado: ").append(String.format("%-43s", "Activo")).append("│\n");
        sb.append("│ Fecha registro: ").append(String.format("%-37s", 
                  java.time.LocalDateTime.now().format(
                  java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy")))) 
                  .append("│\n");
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
        return String.format("%-4d │ %-20s │ %-20s │ %-10s", 
                           getId(), 
                           truncarTexto(getNombre(), 20),
                           truncarTexto(apellidoCompacto, 20),
                           "Profesor");
    }
    
    /**
     * Método para crear encabezado de tabla para listados de profesores
     * @return String con el encabezado de tabla
     */
    public static String getEncabezadoTabla() {
        StringBuilder sb = new StringBuilder();
        sb.append("┌──────┬──────────────────────┬──────────────────────┬────────────┐\n");
        sb.append("│  ID  │        NOMBRE        │       APELLIDO       │    ROL     │\n");
        sb.append("├──────┼──────────────────────┼──────────────────────┼────────────┤");
        return sb.toString();
    }
    
    /**
     * Método para crear pie de tabla para listados de profesores
     * @return String con el pie de tabla
     */
    public static String getPieTabla() {
        return "└──────┴──────────────────────┴──────────────────────┴────────────┘";
    }
    
    /**
     * Método para mostrar información detallada del profesor
     */
    public void mostrarDetalles() {
        System.out.println("┌────────────────────────────────────────────────────────┐");
        System.out.println("│                   DETALLES PROFESOR                    │");
        System.out.println("├────────────────────────────────────────────────────────┤");
        System.out.println("│ ID: " + String.format("%-47s", getId()) + "│");
        System.out.println("│ Nombre completo: " + String.format("%-34s", 
                          truncarTexto(getNombreCompleto(), 34)) + "│");
        System.out.println("│ Rol: " + String.format("%-46s", "Profesor del Sistema") + "│");
        System.out.println("│ Estado: " + String.format("%-43s", "Activo") + "│");
        System.out.println("│ Componentes asignados: " + String.format("%-28s", "Ver gestión") + "│");
        System.out.println("└────────────────────────────────────────────────────────┘");
    }
    
    /**
     * Obtener lista de todos los profesores (getter estático)
     * @return Lista de profesores
     */
    public static List<Profesor> getProfesores() {
        return new ArrayList<>(profesores);
    }
    
    /**
     * Obtiene el nombre completo del profesor
     * @return String con nombre completo
     */
    private String getNombreCompleto() {
        if (getApellido() != null && !getApellido().trim().isEmpty()) {
            return getNombre() + " " + getApellido();
        }
        return getNombre();
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