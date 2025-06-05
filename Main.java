import java.util.*;

public class Main {
    
    // Métodos para crear tablas estéticas
    private static void imprimirSeparadorHorizontal(int[] anchos) {
        System.out.print("┌");
        for (int i = 0; i < anchos.length; i++) {
            for (int j = 0; j < anchos[i] + 2; j++) {
                System.out.print("─");
            }
            if (i < anchos.length - 1) {
                System.out.print("┬");
            }
        }
        System.out.println("┐");
    }
    
    private static void imprimirSeparadorMedio(int[] anchos) {
        System.out.print("├");
        for (int i = 0; i < anchos.length; i++) {
            for (int j = 0; j < anchos[i] + 2; j++) {
                System.out.print("─");
            }
            if (i < anchos.length - 1) {
                System.out.print("┼");
            }
        }
        System.out.println("┤");
    }
    
    private static void imprimirSeparadorInferior(int[] anchos) {
        System.out.print("└");
        for (int i = 0; i < anchos.length; i++) {
            for (int j = 0; j < anchos[i] + 2; j++) {
                System.out.print("─");
            }
            if (i < anchos.length - 1) {
                System.out.print("┴");
            }
        }
        System.out.println("┘");
    }
    
    private static void imprimirFilaTabla(String[] datos, int[] anchos) {
        System.out.print("│");
        for (int i = 0; i < datos.length; i++) {
            String formato = " %-" + anchos[i] + "s ";
            System.out.printf(formato, datos[i]);
            System.out.print("│");
        }
        System.out.println();
    }
    
    private static void mostrarMenuPrincipal() {
        System.out.println("\n┌─────────────────────────────────────────────┐");
        System.out.println("│              SISTEMA ACADÉMICO              │");
        System.out.println("│               GESTIÓN ESTUDIANTIL           │");
        System.out.println("├─────────────────────────────────────────────┤");
        System.out.println("│  1. Registrar nuevo estudiante              │");
        System.out.println("│  2. Consultar notas de estudiante           │");
        System.out.println("│  3. Ver resumen general de estudiantes      │");
        System.out.println("│  4. Salir del sistema                       │");
        System.out.println("└─────────────────────────────────────────────┘");
        System.out.print("Seleccione una opción: ");
    }
    
    private static void mostrarComponentesDisponibles(List<Componente> componentes) {
        System.out.println("\n┌─────────────────────────────────────────────────────────────────┐");
        System.out.println("│                      COMPONENTES DISPONIBLES                    │");
        System.out.println("└─────────────────────────────────────────────────────────────────┘");
        
        int[] anchos = {3, 20, 25};
        String[] headers = {"Nº", "Componente", "Profesor"};
        
        imprimirSeparadorHorizontal(anchos);
        imprimirFilaTabla(headers, anchos);
        imprimirSeparadorMedio(anchos);
        
        for (int i = 0; i < componentes.size(); i++) {
            Componente comp = componentes.get(i);
            String[] fila = {
                String.valueOf(i + 1),
                comp.getNombreComponente(),
                comp.getProfesor()
            };
            imprimirFilaTabla(fila, anchos);
        }
        
        imprimirSeparadorInferior(anchos);
        System.out.print("Seleccione un componente: ");
    }
    
    private static void mostrarFormularioRegistro() {
        System.out.println("\n┌─────────────────────────────────────────────┐");
        System.out.println("│           REGISTRO DE ESTUDIANTE            │");
        System.out.println("└─────────────────────────────────────────────┘");
    }
    
    private static void mostrarResumenEstudiantes(List<Estudiante> estudiantes) {
        if (estudiantes.isEmpty()) {
            System.out.println("\n┌─────────────────────────────────────────────┐");
            System.out.println("│             NO HAY ESTUDIANTES              │");
            System.out.println("│              REGISTRADOS                    │");
            System.out.println("└─────────────────────────────────────────────┘");
            return;
        }
        
        System.out.println("\n┌─────────────────────────────────────────────────────────────────┐");
        System.out.println("│                      RESUMEN ACADÉMICO                          │");
        System.out.println("└─────────────────────────────────────────────────────────────────┘");
        
        int[] anchos = {15, 25, 15, 10};
        String[] headers = {"ID Estudiante", "Nombre Completo", "Componente", "Nota Final"};
        
        imprimirSeparadorHorizontal(anchos);
        imprimirFilaTabla(headers, anchos);
        imprimirSeparadorMedio(anchos);
        
        for (Estudiante e : estudiantes) {
            String[] fila = {
                e.getIdentificacion(),
                e.getNombreCompleto(),
                "N/A", // Necesitarás agregar un método para obtener el componente del estudiante
                String.format("%.1f", e.getNotaFinal())
            };
            imprimirFilaTabla(fila, anchos);
        }
        
        imprimirSeparadorInferior(anchos);
    }
    
    private static void mostrarMensajeExito(String mensaje) {
        System.out.println("\n┌─────────────────────────────────────────────┐");
        System.out.printf("│             %-28s │\n", mensaje);
        System.out.println("└─────────────────────────────────────────────┘");
    }
    
    private static void mostrarMensajeError(String mensaje) {
        System.out.println("\n┌─────────────────────────────────────────────┐");
        System.out.printf("│             %-28s │\n", mensaje);
        System.out.println("└─────────────────────────────────────────────┘");
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Estudiante> estudiantes = new ArrayList<>();
        List<Componente> componentes = new ArrayList<>();

        // Inicializar componentes con información más detallada
        componentes.add(new Componente("101", "Matemáticas", "Prof. Ana Rodríguez"));
        componentes.add(new Componente("102", "Lengua Castellana", "Prof. Juan Pérez"));
        componentes.add(new Componente("103", "Historia", "Prof. Carla Torres"));
        componentes.add(new Componente("104", "Ciencias Naturales", "Prof. Luis García"));
        componentes.add(new Componente("105", "Inglés", "Prof. María Silva"));

        while (true) {
            mostrarMenuPrincipal();
            
            try {
                int opcion = Integer.parseInt(sc.nextLine());

                switch (opcion) {
                    case 1:
                        mostrarFormularioRegistro();
                        
                        // Tabla de datos del estudiante
                        System.out.println("\n┌─────────────────────────────────────────────┐");
                        System.out.println("│              DATOS DEL ESTUDIANTE           │");
                        System.out.println("├─────────────────────────────────────────────┤");
                        
                        System.out.print("│ Número de identificación: ");
                        String idEstudiante = sc.nextLine();
                        
                        System.out.print("│ Nombre del estudiante: ");
                        String nombreEstudiante = sc.nextLine();
                        
                        System.out.print("│ Apellido del estudiante: ");
                        String apellidoEstudiante = sc.nextLine();
                        
                        System.out.println("└─────────────────────────────────────────────┘");

                        mostrarComponentesDisponibles(componentes);
                        
                        int opcionComponente = Integer.parseInt(sc.nextLine());
                        if (opcionComponente < 1 || opcionComponente > componentes.size()) {
                            mostrarMensajeError("Opción de componente inválida");
                            break;
                        }
                        
                        Componente componenteSeleccionado = componentes.get(opcionComponente - 1);
                        Estudiante estudiante = new Estudiante(idEstudiante, nombreEstudiante, 
                                                               apellidoEstudiante, componenteSeleccionado);

                        // Solicitar cantidad de notas
                        System.out.println("\n┌─────────────────────────────────────────────┐");
                        System.out.print("│ ¿Cuántas notas desea ingresar? ");
                        int cantidadNotas = Integer.parseInt(sc.nextLine());
                        System.out.println("└─────────────────────────────────────────────┘");

                        if (cantidadNotas <= 0) {
                            mostrarMensajeError("Debe ingresar al menos una nota");
                            break;
                        }

                        float porcentajeAutomatico = 100f / cantidadNotas;

                        // Tabla para ingresar notas
                        System.out.println("\n┌─────────────────────────────────────────────┐");
                        System.out.println("│               INGRESO DE NOTAS              │");
                        System.out.println("├─────────────────────────────────────────────┤");
                        
                        for (int i = 0; i < cantidadNotas; i++) {
                            System.out.printf("│ Nota %d (%.1f%%): ", (i + 1), porcentajeAutomatico);
                            float valorNota = Float.parseFloat(sc.nextLine());
                            
                            if (valorNota < 0 || valorNota > 10) {
                                mostrarMensajeError("La nota debe estar entre 0 y 10");
                                i--; // Repetir esta iteración
                                continue;
                            }
                            
                            estudiante.agregarNota(new Nota(valorNota, porcentajeAutomatico));
                        }
                        
                        System.out.println("└─────────────────────────────────────────────┘");

                        estudiantes.add(estudiante);
                        mostrarMensajeExito("Estudiante registrado exitosamente");
                        break;

                    case 2:
                        System.out.println("\n┌─────────────────────────────────────────────┐");
                        System.out.println("│             CONSULTA DE NOTAS               │");
                        System.out.println("└─────────────────────────────────────────────┘");
                        System.out.print("Ingrese el ID del estudiante: ");
                        String idBuscado = sc.nextLine();
                        boolean encontrado = false;

                        for (Estudiante e : estudiantes) {
                            if (e.getIdentificacion().equals(idBuscado)) {
                                System.out.println("\n┌─────────────────────────────────────────────────────────┐");
                                System.out.printf("│   RESULTADOS ACADÉMICOS DE: %-24s │\n", 
                                                e.getNombreCompleto());
                                System.out.println("│   Componente: [Información disponible en detalle]     │");
                                System.out.println("└─────────────────────────────────────────────────────────┘");
                                e.imprimirNotas();
                                encontrado = true;
                                break;
                            }
                        }

                        if (!encontrado) {
                            mostrarMensajeError("Estudiante no encontrado");
                        }
                        break;

                    case 3:
                        mostrarResumenEstudiantes(estudiantes);
                        break;

                    case 4:
                        System.out.println("\n┌─────────────────────────────────────────────┐");
                        System.out.println("│             CERRANDO SISTEMA                │");
                        System.out.println("│           ¡Hasta la próxima sesión!         │");
                        System.out.println("└─────────────────────────────────────────────┘");
                        sc.close();
                        return;

                    default:
                        mostrarMensajeError("Opción inválida. Intente nuevamente");
                        break;
                }
                
                // Pausa para que el usuario pueda leer los resultados
                System.out.println("\nPresione Enter para continuar...");
                sc.nextLine();
                
            } catch (NumberFormatException e) {
                mostrarMensajeError("Por favor ingrese un número válido");
                System.out.println("\nPresione Enter para continuar...");
                sc.nextLine();
            } catch (Exception e) {
                mostrarMensajeError("Error inesperado: " + e.getMessage());
                System.out.println("\nPresione Enter para continuar...");
                sc.nextLine();
            }
        }
    }
}