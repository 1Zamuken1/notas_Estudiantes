package app;

public class Administrador extends Usuario {

    public Administrador(String nombre, String apellido, String password) {
        super(contadorId++, nombre, apellido, Rol.administrador, password.toCharArray());
    }

    public Administrador(String nombre, String password) {
        super(contadorId++, nombre, "", Rol.administrador, password.toCharArray());
    }

    @Override
    public String toString() {
        // Crear representación en formato tabla elegante
        StringBuilder sb = new StringBuilder();
        
        // Determinar si hay apellido para ajustar el formato
        boolean tieneApellido = getApellido() != null && !getApellido().trim().isEmpty();
        String apellidoMostrar = tieneApellido ? getApellido() : "No especificado";
        
        sb.append("┌────────────────────────────────────────────────────────┐\n");
        sb.append("│                    ADMINISTRADOR                       │\n");
        sb.append("├────────────────────────────────────────────────────────┤\n");
        sb.append("│ ID: ").append(String.format("%-47s", getId())).append("│\n");
        sb.append("│ Nombre: ").append(String.format("%-43s", truncarTexto(getNombre(), 43))).append("│\n");
        sb.append("│ Apellido: ").append(String.format("%-41s", truncarTexto(apellidoMostrar, 41))).append("│\n");
        sb.append("│ Rol: ").append(String.format("%-46s", "Administrador")).append("│\n");
        
        // Indicador de estado activo
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
        return String.format("%-4d │ %-20s │ %-20s │ %-12s", 
                           getId(), 
                           truncarTexto(getNombre(), 20),
                           truncarTexto(apellidoCompacto, 20),
                           "Admin");
    }
    
    /**
     * Método para crear encabezado de tabla para listados
     * @return String con el encabezado de tabla
     */
    public static String getEncabezadoTabla() {
        StringBuilder sb = new StringBuilder();
        sb.append("┌──────┬──────────────────────┬──────────────────────┬──────────────┐\n");
        sb.append("│  ID  │        NOMBRE        │       APELLIDO       │     ROL      │\n");
        sb.append("├──────┼──────────────────────┼──────────────────────┼──────────────┤");
        return sb.toString();
    }
    
    /**
     * Método para crear pie de tabla para listados
     * @return String con el pie de tabla
     */
    public static String getPieTabla() {
        return "└──────┴──────────────────────┴──────────────────────┴──────────────┘";
    }
    
    /**
     * Método para mostrar información detallada del administrador
     */
    public void mostrarDetalles() {
        System.out.println("┌────────────────────────────────────────────────────────┐");
        System.out.println("│                 DETALLES ADMINISTRADOR                 │");
        System.out.println("├────────────────────────────────────────────────────────┤");
        System.out.println("│ ID: " + String.format("%-47s", getId()) + "│");
        System.out.println("│ Nombre completo: " + String.format("%-34s", 
                          truncarTexto(getNombreCompleto(), 34)) + "│");
        System.out.println("│ Rol: " + String.format("%-46s", "Administrador del Sistema") + "│");
        System.out.println("│ Permisos: " + String.format("%-41s", "Completos") + "│");
        System.out.println("│ Estado: " + String.format("%-43s", "Activo") + "│");
        System.out.println("│ Fecha creación: " + String.format("%-35s", 
                          java.time.LocalDateTime.now().format(
                          java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))) + "│");
        System.out.println("└────────────────────────────────────────────────────────┘");
    }
    
    /**
     * Obtiene el nombre completo del administrador
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
    private String truncarTexto(String texto, int longitudMaxima) {
        if (texto == null) return "";
        if (texto.length() <= longitudMaxima) {
            return texto;
        }
        return texto.substring(0, longitudMaxima - 3) + "...";
    }
}