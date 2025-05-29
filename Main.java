import java.util.*;

public class Main {
    static List<Profesor> profesores = new ArrayList<>();
    static List<Curso> cursos = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            printBoxedMenu("MENÚ PRINCIPAL", Arrays.asList(
                    "1. Ingresar como Profesor",
                    "2. Ingresar como Estudiante",
                    "3. Salir"));
            System.out.print("Seleccione una opción: ");
            int op = inputInt();
            switch (op) {
                case 1 -> menuProfesor();
                case 2 -> menuEstudiante();
                case 3 -> {
                    printBoxedMessage("¡Hasta luego!");
                    return;
                }
                default -> printBoxedMessage("Opción inválida.");
            }
        }
    }

    // ------------- MENÚ PROFESOR -------------
    static void menuProfesor() {
        Profesor profesor = seleccionarProfesor();
        if (profesor == null) return;
        while (true) {
            printBoxedMenu("MENÚ PROFESOR (" + profesor.getNombre() + ")", Arrays.asList(
                    "1. Crear curso",
                    "2. Consultar cursos",
                    "3. Eliminar curso",
                    "4. Volver al menú principal"));
            System.out.print("Seleccione una opción: ");
            int op = inputInt();
            switch (op) {
                case 1 -> crearCurso();
                case 2 -> menuConsultarCursos(profesor);
                case 3 -> eliminarCurso();
                case 4 -> { return; }
                default -> printBoxedMessage("Opción inválida.");
            }
        }
    }

    // Menú para consultar cursos
    static void menuConsultarCursos(Profesor profesor) {
        if (cursos.isEmpty()) {
            printBoxedMessage("No hay cursos registrados.");
            return;
        }
        while (true) {
            printBoxedMenu("CONSULTAR CURSOS", getCursosOpciones());
            System.out.println((cursos.size() + 1) + ". Volver");
            System.out.print("Seleccione un curso: ");
            int op = inputInt();
            if (op == cursos.size() + 1) return;
            if (op < 1 || op > cursos.size()) {
                printBoxedMessage("Opción inválida.");
                continue;
            }
            Curso curso = cursos.get(op - 1);
            menuCursoOpciones(profesor, curso);
        }
    }

    // Menú de opciones de un curso
    static void menuCursoOpciones(Profesor profesor, Curso curso) {
        while (true) {
            printBoxedMenu("CURSO: " + curso.getNombre(), Arrays.asList(
                    "1. Ver notas de los alumnos",
                    "2. Calcular promedio de notas",
                    "3. Volver"
            ));
            System.out.print("Seleccione una opción: ");
            int op = inputInt();
            switch (op) {
                case 1 -> mostrarNotasCursoTabla(curso);
                case 2 -> mostrarPromedioCursoTabla(profesor, curso);
                case 3 -> { return; }
                default -> printBoxedMessage("Opción inválida.");
            }
        }
    }

    // Muestra las notas de los alumnos en tabla
    static void mostrarNotasCursoTabla(Curso curso) {
        List<Estudiante> estudiantes = curso.getEstudiantes();
        if (estudiantes.isEmpty()) {
            printBoxedMessage("No hay estudiantes en este curso.");
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("┌───────────────┬────────────────────────────┐\n");
        sb.append("│  Alumno       │ Notas                      │\n");
        sb.append("├───────────────┼────────────────────────────┤\n");
        for (Estudiante e : estudiantes) {
            String notas = e.getNotas().isEmpty() ? "Sin notas" :
                    e.getNotas().toString().replace("[", "").replace("]", "");
            sb.append(String.format("│ %-13s │ %-26s │\n", e.getNombre(), notas));
        }
        sb.append("└───────────────┴────────────────────────────┘\n");
        System.out.print(sb.toString());
    }

    // Muestra el total y promedio de notas por alumno y promedio del curso en tabla
    static void mostrarPromedioCursoTabla(Profesor profesor, Curso curso) {
        List<Estudiante> estudiantes = curso.getEstudiantes();
        if (estudiantes.isEmpty()) {
            printBoxedMessage("No hay estudiantes en este curso.");
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("┌───────────────┬─────────────┬─────────────┐\n");
        sb.append("│  Alumno       │   Total     │  Promedio   │\n");
        sb.append("├───────────────┼─────────────┼─────────────┤\n");
        for (Estudiante e : estudiantes) {
            double total = e.getTotalNotas();
            double promedio = e.getPromedioNotas();
            sb.append(String.format("│ %-13s │ %9.2f   │ %9.2f   │\n", e.getNombre(), total, promedio));
        }
        sb.append("└───────────────┴─────────────┴─────────────┘\n");
        double promedioCurso = profesor.getPromedioNotasEstudiantes(curso);
        sb.append(String.format("Promedio del curso: %.2f\n", promedioCurso));
        System.out.print(sb.toString());
    }

    // Eliminar curso
    static void eliminarCurso() {
        if (cursos.isEmpty()) {
            printBoxedMessage("No hay cursos para eliminar.");
            return;
        }
        printBoxedMenu("ELIMINAR CURSO", getCursosOpciones());
        System.out.print("Seleccione el curso a eliminar: ");
        int op = inputInt();
        if (op < 1 || op > cursos.size()) {
            printBoxedMessage("Opción inválida.");
            return;
        }
        printBoxedMessage("Curso " + cursos.get(op - 1).getNombre() + " eliminado.");
        cursos.remove(op - 1);
    }

    // ------------- MENÚ ESTUDIANTE -------------
    static void menuEstudiante() {
        if (cursos.isEmpty()) {
            printBoxedMessage("No hay cursos registrados. Solicite a un profesor que cree uno.");
            return;
        }
        Curso curso = seleccionarCurso();
        if (curso == null) return;
        Estudiante estudiante = seleccionarEstudiante(curso);
        if (estudiante == null) return;
        while (true) {
            printBoxedMenu("MENÚ ESTUDIANTE (" + estudiante.getNombre() + ")", Arrays.asList(
                    "1. Ver notas",
                    "2. Agregar nota",
                    "3. Editar nota",
                    "4. Eliminar nota",
                    "5. Volver al menú principal"));
            System.out.print("Seleccione una opción: ");
            int op = inputInt();
            switch (op) {
                case 1 -> mostrarNotasEstudianteTabla(estudiante);
                case 2 -> {
                    System.out.print("Ingrese la nueva nota: ");
                    double nota = inputDouble();
                    estudiante.agregarNota(nota);
                    printBoxedMessage("Nota agregada.");
                }
                case 3 -> {
                    if (estudiante.getNotas().isEmpty()) {
                        printBoxedMessage("No hay notas para editar.");
                        break;
                    }
                    mostrarNotasEstudianteTabla(estudiante);
                    System.out.print("Seleccione el índice de la nota a editar (empezando en 0): ");
                    int idx = inputInt();
                    if (idx < 0 || idx >= estudiante.getNotas().size()) {
                        printBoxedMessage("Índice inválido.");
                        break;
                    }
                    System.out.print("Ingrese la nota nueva: ");
                    double nueva = inputDouble();
                    estudiante.actualizarNota(idx, nueva);
                    printBoxedMessage("Nota actualizada.");
                }
                case 4 -> {
                    if (estudiante.getNotas().isEmpty()) {
                        printBoxedMessage("No hay notas para eliminar.");
                        break;
                    }
                    mostrarNotasEstudianteTabla(estudiante);
                    System.out.print("Seleccione el índice de la nota a eliminar (empezando en 0): ");
                    int idx = inputInt();
                    if (estudiante.eliminarNota(idx)) {
                        printBoxedMessage("Nota eliminada.");
                    } else {
                        printBoxedMessage("Índice inválido.");
                    }
                }
                case 5 -> { return; }
                default -> printBoxedMessage("Opción inválida.");
            }
        }
    }

    // ----------- AUXILIARES SELECCIÓN/CREACIÓN -----------
    static Profesor seleccionarProfesor() {
        if (profesores.isEmpty()) {
            printBoxedMessage("No hay profesores. Cree uno nuevo.");
            System.out.print("Nombre del profesor: ");
            scanner.nextLine(); // limpia buffer
            String nombre = scanner.nextLine();
            Profesor p = new Profesor(profesores.size() + 1, nombre);
            profesores.add(p);
            return p;
        } else {
            List<String> opciones = new ArrayList<>();
            for (Profesor prof : profesores)
                opciones.add(prof.getNombre());
            opciones.add("Crear nuevo profesor");
            printBoxedMenu("SELECCIONAR PROFESOR", opciones);
            System.out.print("Seleccione: ");
            int op = inputInt();
            if (op == profesores.size() + 1) {
                System.out.print("Nombre del nuevo profesor: ");
                scanner.nextLine();
                String nombre = scanner.nextLine();
                Profesor p = new Profesor(profesores.size() + 1, nombre);
                profesores.add(p);
                return p;
            } else if (op > 0 && op <= profesores.size()) {
                return profesores.get(op - 1);
            }
        }
        return null;
    }

    static Curso seleccionarCurso() {
        if (cursos.isEmpty()) {
            printBoxedMessage("No hay cursos. Cree uno nuevo.");
            return crearCurso();
        } else {
            List<String> opciones = getCursosOpciones();
            opciones.add("Crear nuevo curso");
            printBoxedMenu("SELECCIONAR CURSO", opciones);
            System.out.print("Seleccione: ");
            int op = inputInt();
            if (op == cursos.size() + 1) return crearCurso();
            else if (op > 0 && op <= cursos.size()) return cursos.get(op - 1);
        }
        return null;
    }

    static Curso crearCurso() {
        System.out.print("Nombre del curso: ");
        scanner.nextLine();
        String nombre = scanner.nextLine();
        Curso c = new Curso(nombre);
        cursos.add(c);
        printBoxedMessage("Curso creado correctamente.");
        return c;
    }

    static List<String> getCursosOpciones() {
        List<String> opciones = new ArrayList<>();
        for (Curso c : cursos)
            opciones.add(c.getNombre() + " (" + c.getNumeroIntegrantes() + " estudiantes)");
        return opciones;
    }

    static Estudiante seleccionarEstudiante(Curso curso) {
        if (curso.getEstudiantes().isEmpty()) {
            printBoxedMessage("No hay estudiantes. Cree uno nuevo.");
            return crearEstudiante(curso);
        } else {
            List<String> opciones = new ArrayList<>();
            for (Estudiante est : curso.getEstudiantes())
                opciones.add(est.getNombre());
            opciones.add("Crear nuevo estudiante");
            printBoxedMenu("SELECCIONAR ESTUDIANTE", opciones);
            System.out.print("Seleccione: ");
            int op = inputInt();
            if (op == curso.getEstudiantes().size() + 1) return crearEstudiante(curso);
            else if (op > 0 && op <= curso.getEstudiantes().size()) return curso.getEstudiantes().get(op - 1);
        }
        return null;
    }

    static Estudiante crearEstudiante(Curso curso) {
        System.out.print("Nombre del estudiante: ");
        scanner.nextLine();
        String nombre = scanner.nextLine();
        Estudiante e = new Estudiante(curso.getEstudiantes().size() + 1, nombre);
        curso.agregarEstudiante(e);
        printBoxedMessage("Estudiante creado correctamente.");
        return e;
    }

    // ----------- UTILIDADES DE ENTRADA SEGURA -----------
    static int inputInt() {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException ex) {
                System.out.print("Ingrese un número válido: ");
                scanner.nextLine();
            }
        }
    }

    static double inputDouble() {
        while (true) {
            try {
                return scanner.nextDouble();
            } catch (InputMismatchException ex) {
                System.out.print("Ingrese un número válido: ");
                scanner.nextLine();
            }
        }
    }

    // ----------- UTILIDADES DE FORMATO VISUAL -----------
    static void printBoxedMenu(String titulo, List<String> opciones) {
        int maxLen = titulo.length();
        for (String op : opciones) maxLen = Math.max(maxLen, op.length());
        maxLen += 4;
        String barra = "┌" + "─".repeat(maxLen) + "┐";
        String barraMid = "├" + "─".repeat(maxLen) + "┤";
        String barraBot = "└" + "─".repeat(maxLen) + "┘";
        System.out.println(barra);
        System.out.printf("│ %-" + (maxLen-2) + "s │\n", titulo);
        System.out.println(barraMid);
        for (String op : opciones)
            System.out.printf("│ %-" + (maxLen-2) + "s │\n", op);
        System.out.println(barraBot);
    }

    static void printBoxedMessage(String mensaje) {
        int len = mensaje.length() + 4;
        String barra = "┌" + "─".repeat(len) + "┐";
        String barraBot = "└" + "─".repeat(len) + "┘";
        System.out.println(barra);
        System.out.printf("│ %-" + (len - 2) + "s │\n", mensaje);
        System.out.println(barraBot);
    }

    static void mostrarNotasEstudianteTabla(Estudiante estudiante) {
        List<Double> notas = estudiante.getNotas();
        if (notas.isEmpty()) {
            printBoxedMessage("No hay notas registradas.");
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("┌─────┬───────────┐\n");
        sb.append("│ N°  │   Nota    │\n");
        sb.append("├─────┼───────────┤\n");
        for (int i = 0; i < notas.size(); i++)
            sb.append(String.format("│ %-3d │ %8.2f │\n", i, notas.get(i)));
        sb.append("└─────┴───────────┘\n");
        System.out.print(sb.toString());
    }
}