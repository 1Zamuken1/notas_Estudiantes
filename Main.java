import java.util.*;

public class Main {

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
            // Mostrar menú principal
            System.out.println("\n┌─────────────────────────────────────────────┐");
            System.out.println("│              SISTEMA ACADÉMICO              │");
            System.out.println("│               GESTIÓN ESTUDIANTIL           │");
            System.out.println("├─────────────────────────────────────────────┤");
            System.out.println("│  1. Registrar nuevo estudiante              │");
            System.out.println("│  2. Consultar notas de estudiante           │");
            System.out.println("│  3. Ver resumen general de estudiantes      │");
            System.out.println("│  4. Ver estadísticas generales              │");
            System.out.println("│  5. Salir del sistema                       │");
            System.out.println("└─────────────────────────────────────────────┘");
            System.out.print("Seleccione una opción: ");

            try {
                int opcion = Integer.parseInt(sc.nextLine());

                switch (opcion) {
                    case 1:
                        registrarEstudiante(sc, estudiantes, componentes);
                        break;

                    case 2:
                        consultarNotasEstudiante(sc, estudiantes);
                        break;

                    case 3:
                        mostrarResumenGeneral(estudiantes);
                        break;

                    case 4:
                        mostrarEstadisticas(estudiantes);
                        break;

                    case 5:
                        System.out.println("\n┌─────────────────────────────────────────────┐");
                        System.out.println("│             CERRANDO SISTEMA                │");
                        System.out.println("│           ¡Hasta la próxima sesión!         │");
                        System.out.println("└─────────────────────────────────────────────┘");
                        sc.close();
                        return;

                    default:
                        System.out.println("\n┌─────────────────────────────────────────────┐");
                        System.out.println("│     Opción inválida. Intente nuevamente    │");
                        System.out.println("└─────────────────────────────────────────────┘");
                        break;
                }

                // Pausa para que el usuario pueda leer los resultados
                System.out.println("\nPresione Enter para continuar...");
                sc.nextLine();

            } catch (NumberFormatException e) {
                System.out.println("\n┌─────────────────────────────────────────────┐");
                System.out.println("│     Por favor ingrese un número válido     │");
                System.out.println("└─────────────────────────────────────────────┘");
                System.out.println("\nPresione Enter para continuar...");
                sc.nextLine();
            } catch (Exception e) {
                System.out.println("\n┌─────────────────────────────────────────────┐");
                System.out.printf("│     Error inesperado: %-18s    │%n", e.getMessage());
                System.out.println("└─────────────────────────────────────────────┘");
                System.out.println("\nPresione Enter para continuar...");
                sc.nextLine();
            }
        }
    }

    private static void registrarEstudiante(Scanner sc, List<Estudiante> estudiantes, List<Componente> componentes) {
        System.out.println("\n┌─────────────────────────────────────────────┐");
        System.out.println("│           REGISTRO DE ESTUDIANTE            │");
        System.out.println("└─────────────────────────────────────────────┘");

        // Datos del estudiante
        System.out.println("\n┌─────────────────────────────────────────────┐");
        System.out.println("│              DATOS DEL ESTUDIANTE           │");
        System.out.println("├─────────────────────────────────────────────┤");

        String idEstudiante;
        do {
            System.out.print("│ Número de identificación: ");
            idEstudiante = sc.nextLine().trim();

            if (idEstudiante.isEmpty()) {
                System.out.println("│ El ID no puede estar vacío. Intente nuevamente.");
                continue;
            }

            // Verificar si ya existe un estudiante con este ID usando bucle tradicional
            boolean existe = false;
            for (Estudiante estudiante : estudiantes) {
                if (estudiante.getIdentificacion().equals(idEstudiante)) {
                    existe = true;
                    break;
                }
            }

            if (existe) {
                System.out.println("│ Ya existe un estudiante con este ID. Intente con otro.");
            } else {
                break;
            }
        } while (true);

        System.out.print("│ Nombre del estudiante: ");
        String nombreEstudiante = sc.nextLine().trim();

        System.out.print("│ Apellido del estudiante: ");
        String apellidoEstudiante = sc.nextLine().trim();

        System.out.println("└─────────────────────────────────────────────┘");

        // Mostrar componentes disponibles
        System.out.println("\n┌─────────────────────────────────────────────────────────────────┐");
        System.out.println("│                      COMPONENTES DISPONIBLES                    │");
        System.out.println("└─────────────────────────────────────────────────────────────────┘");

        // Tabla de componentes
        System.out.println("┌─────┬──────────────────────┬─────────────────────────┐");
        System.out.println("│  Nº │ Componente           │ Profesor                │");
        System.out.println("├─────┼──────────────────────┼─────────────────────────┤");

        for (int i = 0; i < componentes.size(); i++) {
            Componente comp = componentes.get(i);
            System.out.printf("│ %-3s │ %-20s │ %-23s │%n",
                    (i + 1),
                    comp.getNombreComponente(),
                    comp.getProfesor());
        }

        System.out.println("└─────┴──────────────────────┴─────────────────────────┘");
        System.out.print("Seleccione un componente: ");

        int opcionComponente = Integer.parseInt(sc.nextLine());
        if (opcionComponente < 1 || opcionComponente > componentes.size()) {
            System.out.println("\n┌─────────────────────────────────────────────┐");
            System.out.println("│        Opción de componente inválida       │");
            System.out.println("└─────────────────────────────────────────────┘");
            return;
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
            System.out.println("\n┌─────────────────────────────────────────────┐");
            System.out.println("│      Debe ingresar al menos una nota       │");
            System.out.println("└─────────────────────────────────────────────┘");
            return;
        }

        // Ingreso de notas con porcentajes personalizados
        System.out.println("\n┌─────────────────────────────────────────────┐");
        System.out.println("│               INGRESO DE NOTAS              │");
        System.out.println("├─────────────────────────────────────────────┤");
        System.out.println("│ Ingrese cada nota con su porcentaje         │");
        System.out.println("└─────────────────────────────────────────────┘");

        float porcentajeTotal = 0;

        for (int i = 0; i < cantidadNotas; i++) {
            System.out.printf("\n--- NOTA %d ---\n", (i + 1));

            // Solicitar valor de la nota
            float valorNota;
            do {
                System.out.print("Valor de la nota (0-10): ");
                valorNota = Float.parseFloat(sc.nextLine());

                if (valorNota < 0 || valorNota > 10) {
                    System.out.println("La nota debe estar entre 0 y 10. Intente nuevamente.");
                }
            } while (valorNota < 0 || valorNota > 10);

            // Solicitar porcentaje
            float porcentaje;
            do {
                System.out.printf("Porcentaje de esta nota (restante: %.1f%%): ", (100 - porcentajeTotal));
                porcentaje = Float.parseFloat(sc.nextLine());

                if (porcentaje <= 0 || porcentaje > (100 - porcentajeTotal)) {
                    System.out.printf("El porcentaje debe ser mayor a 0 y no mayor a %.1f%%. Intente nuevamente.\n",
                            (100 - porcentajeTotal));
                }
            } while (porcentaje <= 0 || porcentaje > (100 - porcentajeTotal));

            porcentajeTotal += porcentaje;
            estudiante.agregarNota(new Nota(valorNota, porcentaje));

            System.out.printf("✓ Nota registrada: %.1f con %.1f%% = %.2f puntos\n",
                    valorNota, porcentaje, (valorNota * porcentaje) / 100);
        }

        // Verificar si los porcentajes suman 100%
        if (Math.abs(porcentajeTotal - 100) > 0.01) {
            System.out.printf("\n⚠️  ADVERTENCIA: Los porcentajes suman %.1f%% (no 100%%)\n", porcentajeTotal);
            System.out.println("¿Desea continuar de todas formas? (s/n): ");
            String respuesta = sc.nextLine().toLowerCase();
            if (!respuesta.equals("s") && !respuesta.equals("si")) {
                System.out.println("Registro cancelado.");
                return;
            }
        }

        estudiantes.add(estudiante);

        // Mostrar resumen del registro
        System.out.println("\n" + "=".repeat(50));
        System.out.println("          ESTUDIANTE REGISTRADO EXITOSAMENTE   ");
        System.out.println("=".repeat(50));
        estudiante.mostrarTablaNotas();
    }

    private static void consultarNotasEstudiante(Scanner sc, List<Estudiante> estudiantes) {
        System.out.println("\n┌─────────────────────────────────────────────┐");
        System.out.println("│             CONSULTA DE NOTAS               │");
        System.out.println("└─────────────────────────────────────────────┘");
        System.out.print("Ingrese el ID del estudiante: ");
        String idBuscado = sc.nextLine();
        boolean encontrado = false;

        for (Estudiante e : estudiantes) {
            if (e.getIdentificacion().equals(idBuscado)) {
                System.out.println("\n" + "=".repeat(60));
                System.out.printf("           RESULTADOS DE: %s\n", e.getNombreCompleto());
                System.out.println("=".repeat(60));
                e.mostrarTablaNotas();
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            System.out.println("\n┌─────────────────────────────────────────────┐");
            System.out.println("│           Estudiante no encontrado         │");
            System.out.println("└─────────────────────────────────────────────┘");
        }
    }

    private static void mostrarResumenGeneral(List<Estudiante> estudiantes) {
        if (estudiantes.isEmpty()) {
            System.out.println("\n┌─────────────────────────────────────────────┐");
            System.out.println("│             NO HAY ESTUDIANTES              │");
            System.out.println("│              REGISTRADOS                    │");
            System.out.println("└─────────────────────────────────────────────┘");
            return;
        }

        System.out.println(
                "\n┌─────────────────────────────────────────────────────────────────────────────────────────┐");
        System.out
                .println("│                                    RESUMEN ACADÉMICO                                    │");
        System.out
                .println("└─────────────────────────────────────────────────────────────────────────────────────────┘");

        // Tabla de resumen
        System.out.println(
                "┌─────────────────┬─────────────────────────┬──────────────────────┬────────────┬──────────┐");
        System.out.println(
                "│ ID Estudiante   │ Nombre Completo         │ Componente           │ Nota Final │ Estado   │");
        System.out.println(
                "├─────────────────┼─────────────────────────┼──────────────────────┼────────────┼──────────┤");

        for (Estudiante e : estudiantes) {
            String estado = e.estaAprobado() ? "APROBADO" : "REPROBADO";
            System.out.printf("│ %-15s │ %-23s │ %-20s │ %-10.1f │ %-8s │%n",
                    e.getIdentificacion(),
                    e.getNombreCompleto(),
                    e.getComponente().getNombreComponente(),
                    e.getNotaFinal(),
                    estado);
        }

        System.out.println(
                "└─────────────────┴─────────────────────────┴──────────────────────┴────────────┴──────────┘");
    }

    private static void mostrarEstadisticas(List<Estudiante> estudiantes) {
        if (estudiantes.isEmpty()) {
            System.out.println("\n┌─────────────────────────────────────────────┐");
            System.out.println("│             NO HAY ESTUDIANTES              │");
            System.out.println("│              REGISTRADOS                    │");
            System.out.println("└─────────────────────────────────────────────┘");
            return;
        }

        long aprobados = estudiantes.stream().filter(Estudiante::estaAprobado).count();
        long reprobados = estudiantes.size() - aprobados;

        double promedioGeneral = estudiantes.stream()
                .mapToDouble(Estudiante::getNotaFinal)
                .average()
                .orElse(0.0);

        double notaMaxima = estudiantes.stream()
                .mapToDouble(Estudiante::getNotaFinal)
                .max()
                .orElse(0.0);

        double notaMinima = estudiantes.stream()
                .mapToDouble(Estudiante::getNotaFinal)
                .min()
                .orElse(0.0);

        System.out.println("\n┌─────────────────────────────────────────────┐");
        System.out.println("│            ESTADÍSTICAS GENERALES          │");
        System.out.println("├─────────────────────────────────────────────┤");
        System.out.printf("│ Total de estudiantes: %-17d │%n", estudiantes.size());
        System.out.printf("│ Estudiantes aprobados: %-16d │%n", aprobados);
        System.out.printf("│ Estudiantes reprobados: %-15d │%n", reprobados);
        System.out.printf("│ Promedio general: %-19.1f │%n", promedioGeneral);
        System.out.printf("│ Nota más alta: %-22.1f │%n", notaMaxima);
        System.out.printf("│ Nota más baja: %-22.1f │%n", notaMinima);
        System.out.printf("│ Porcentaje de aprobación: %-13.1f%% │%n",
                (double) aprobados / estudiantes.size() * 100);
        System.out.println("└─────────────────────────────────────────────┘");
    }
}