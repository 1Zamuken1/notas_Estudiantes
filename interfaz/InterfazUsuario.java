package interfaz;
import java.util.List;
import java.util.Scanner;

import app.Administrador;
import app.Estudiante;
import app.Nota;
import app.Profesor;
import app.Usuario;
import logica.Componente;
import logica.GestorComponentes;
import logica.SistemaAutenticacion;

public class InterfazUsuario {
    // --- Atributos principales ---
    private SistemaAutenticacion sistema;
    private Scanner scanner;
    private GestorComponentes gestorComponentes;

    // --- Constructor ---
    public InterfazUsuario() {
        this.sistema = new SistemaAutenticacion();
        this.gestorComponentes = new GestorComponentes();
        this.scanner = new Scanner(System.in);
    }

    // ==========================================================
    // =================== MENÚS PRINCIPALES ====================
    // ==========================================================

    public void mostrarMenuPrincipal() {
        boolean continuar = true;
        while (continuar) {
            System.out.println("┌─────────────────────────────────────────────┐");
            System.out.println("│               Menú Principal                │");
            System.out.println("├─────────────────────────────────────────────┤");
            System.out.println("│ 1. Iniciar sesión                           │");
            System.out.println("│ 2. Registrarse (Profesor/Estudiante)        │");
            System.out.println("│ 3. Salir                                    │");
            System.out.println("└─────────────────────────────────────────────┘");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    iniciarSesion();
                    break;
                case 2:
                    registrarUsuario(); // Solo permite Profesor o Estudiante
                    break;
                case 3:
                    continuar = false;
                    System.out.println();
                    System.out.println("┌─────────────────────────────────────────────┐");
                    System.out.println("│                   Salir                     │");
                    System.out.println("├─────────────────────────────────────────────┤");
                    System.out.println("│           Saliendo del sistema...           │");
                    System.out.println("└─────────────────────────────────────────────┘");
                    break;
                default:
                    System.out.println();
                    System.out.println("┌─────────────────────────────────────────────┐");
                    System.out.println("│                   Error                     │");
                    System.out.println("├─────────────────────────────────────────────┤");
                    System.out.println("│       Opción no válida. Intente de nuevo    │");
                    System.out.println("└─────────────────────────────────────────────┘");
            }
        }
    }

    private void iniciarSesion() {
        System.out.print("Ingrese su ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        System.out.print("Ingrese su contraseña: ");
        String passwordStr = scanner.nextLine();
        char[] password = passwordStr.toCharArray();

        iniciarSesion(id, password);
    }

    private void iniciarSesion(int id, char[] password) {
        boolean credencialValida = sistema.validarCredenciales(id, password);

        if (credencialValida) {
            Usuario usuario = sistema.obtenerUsuarioPorId(id);
            System.out.println();
            System.out.println("┌─────────────────────────────────────────────┐");
            System.out.println("│            Inicio de Sesión Exitoso         │");
            System.out.println("└─────────────────────────────────────────────┘");

            if (usuario instanceof Administrador) {
                System.out.println();
                System.out.println("┌─────────────────────────────────────────────┐");
                System.out.println("│                 Bienvenido                  │");
                System.out.println("├─────────────────────────────────────────────┤");
                System.out.println("│ Administrador " + String.format("%-28s", usuario.getNombre()) + " │");
                System.out.println("└─────────────────────────────────────────────┘");
                mostrarMenuAdministrador((Administrador) usuario);
            } else if (usuario instanceof Profesor) {
                System.out.println();
                System.out.println("┌─────────────────────────────────────────────┐");
                System.out.println("│                 Bienvenido                  │");
                System.out.println("├─────────────────────────────────────────────┤");
                System.out.println("│ Profesor " + String.format("%-33s", usuario.getNombre()) + " │");
                System.out.println("└─────────────────────────────────────────────┘");
                menuProfesor((Profesor) usuario);
            } else if (usuario instanceof Estudiante) {
                System.out.println();
                System.out.println("┌─────────────────────────────────────────────┐");
                System.out.println("│                 Bienvenido                  │");
                System.out.println("├─────────────────────────────────────────────┤");
                System.out.println("│ Estudiante " + String.format("%-31s", usuario.getNombre()) + " │");
                System.out.println("└─────────────────────────────────────────────┘");
                menuEstudiante((Estudiante) usuario);
            }
        } else {
            System.out.println();
            System.out.println("┌─────────────────────────────────────────────┐");
            System.out.println("│                   Error                     │");
            System.out.println("├─────────────────────────────────────────────┤");
            System.out.println("│      Credenciales inválidas                 │");
            System.out.println("│           Intente de nuevo                  │");
            System.out.println("└─────────────────────────────────────────────┘");
        }
    }

    // ==========================================================
    // =================== MENÚS POR ROL ========================
    // ==========================================================

    private void mostrarMenuAdministrador(Administrador administrador) {
        int opcion;
        do {
            System.out.println();
            System.out.println("┌─────────────────────────────────────────────┐");
            System.out.println("│              MENÚ ADMINISTRADOR             │");
            System.out.println("├─────────────────────────────────────────────┤");
            System.out.println("│ 1. Gestión de usuarios                      │");
            System.out.println("│ 2. Gestión de componentes                   │");
            System.out.println("│ 3. Gestión de notas                         │");
            System.out.println("│ 4. Cerrar sesión                            │");
            System.out.println("└─────────────────────────────────────────────┘");

            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    menuGestionUsuarios(administrador);
                    break;
                case 2:
                    menuGestionComponentes();
                    break;
                case 3:
                    menuGestionNotasAdmin();
                    break;
                case 4:
                    System.out.println();
                    System.out.println("┌─────────────────────────────────────────────┐");
                    System.out.println("│                Cerrar Sesión                │");
                    System.out.println("├─────────────────────────────────────────────┤");
                    System.out.println("│             Sesión cerrada                  │");
                    System.out.println("└─────────────────────────────────────────────┘");
                    break;
                default:
                    System.out.println();
                    System.out.println("┌─────────────────────────────────────────────┐");
                    System.out.println("│                   Error                     │");
                    System.out.println("├─────────────────────────────────────────────┤");
                    System.out.println("│              Opción inválida                │");
                    System.out.println("└─────────────────────────────────────────────┘");
            }
        } while (opcion != 4);
    }

    private void menuProfesor(Profesor profesor) {
        int opcion;
        do {
            System.out.println("\n┌─────────────────────────────────────────────┐");
            System.out.println("│                MENÚ PROFESOR                  │");
            System.out.println("├───────────────────────────────────────────────┤");
            System.out.println("│ 1. Consultar todos los usuarios               │");
            System.out.println("│ 2. Consultar usuario por ID                   │");
            System.out.println("│ 3. Registrar estudiante                       │");
            System.out.println("│ 4. Gestión de componentes                     │");
            System.out.println("│ 5. Gestión de notas                           │");
            System.out.println("│ 6. Cerrar sesión                              │");
            System.out.println("└───────────────────────────────────────────────┘");

            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    consultarTodosLosUsuarios(profesor);
                    break;
                case 2:
                    consultarUsuarioPorId();
                    break;
                case 3:
                    registrarEstudiantesPorProfesor();
                    break;
                case 4:
                    menuGestionComponentes();
                    break;
                case 5:
                    menuGestionNotasProfesor(profesor);
                    break;
                case 6:
                    System.out.println("\n┌─────────────────────────────────────────────┐");
                    System.out.println("│            Sesión cerrada                    │");
                    System.out.println("└──────────────────────────────────────────────┘");
                    break;
                default:
                    System.out.println("\n┌─────────────────────────────────────────────┐");
                    System.out.println("│              Opción inválida                 │");
                    System.out.println("└──────────────────────────────────────────────┘");
            }
        } while (opcion != 6);
    }

    private void menuEstudiante(Estudiante estudiante) {
        int opcion;
        do {
            System.out.println("\n┌─────────────────────────────────────────────┐");
            System.out.println("│              MENÚ ESTUDIANTE                 │");
            System.out.println("├──────────────────────────────────────────────┤");
            System.out.println("│ 1. Consultar todos los usuarios              │");
            System.out.println("│ 2. Consultar usuario por ID                  │");
            System.out.println("│ 3. Ver mis notas por componente              │");
            System.out.println("│ 4. Cerrar sesión                             │");
            System.out.println("└──────────────────────────────────────────────┘");

            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    consultarTodosLosUsuarios(estudiante);
                    break;
                case 2:
                    consultarUsuarioPorId();
                    break;
                case 3:
                    verMisNotasPorComponente(estudiante);
                    break;
                case 4:
                    System.out.println("\n┌─────────────────────────────────────────────┐");
                    System.out.println("│              Sesión cerrada                  │");
                    System.out.println("└──────────────────────────────────────────────┘");
                    break;
                default:
                    System.out.println("\n┌─────────────────────────────────────────────┐");
                    System.out.println("│               Opción inválida                │");
                    System.out.println("└──────────────────────────────────────────────┘");
            }
        } while (opcion != 4);
    }

    // ==========================================================
    // =================== MENÚS DE GESTIÓN =====================
    // ==========================================================

    private void menuGestionUsuarios(Administrador administrador) {
        int opcion;
        do {
            System.out.println("\n┌─────────────────────────────────────────────┐");
            System.out.println("│            GESTIÓN DE USUARIOS               │");
            System.out.println("├──────────────────────────────────────────────┤");
            System.out.println("│ 1. Consultar todos los usuarios              │");
            System.out.println("│ 2. Consultar usuario por ID                  │");
            System.out.println("│ 3. Registrar nuevo usuario                   │");
            System.out.println("│ 4. Eliminar usuario                          │");
            System.out.println("│ 5. Volver                                    │");
            System.out.println("└──────────────────────────────────────────────┘");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    consultarTodosLosUsuarios(administrador);
                    break;
                case 2:
                    consultarUsuarioPorId();
                    break;
                case 3:
                    registrarUsuarioComoAdmin();
                    break;
                case 4:
                    eliminarUsuarioPorId();
                    break;
                case 5:
                    System.out.println("\n┌─────────────────────────────────────────────┐");
                    System.out.println("│          Volviendo al menú anterior...       │");
                    System.out.println("└──────────────────────────────────────────────┘");
                    break;
                default:
                    System.out.println("\n┌─────────────────────────────────────────────┐");
                    System.out.println("│               Opción inválida                │");
                    System.out.println("└──────────────────────────────────────────────┘");
            }
        } while (opcion != 5);
    }

    private void menuGestionComponentes() {
        int opcion;
        do {
            System.out.println("\n┌─────────────────────────────────────────────┐");
            System.out.println("│          Gestión de Componentes              │");
            System.out.println("├──────────────────────────────────────────────┤");
            System.out.println("│ 1. Registrar componente                      │");
            System.out.println("│ 2. Consultar componentes                     │");
            System.out.println("│ 3. Asociar estudiante a componente           │");
            System.out.println("│ 4. Volver                                    │");
            System.out.println("└──────────────────────────────────────────────┘");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    registrarComponente();
                    break;
                case 2:
                    gestorComponentes.listarComponentes();
                    break;
                case 3:
                    asociarEstudianteAComponente();
                    break;
                case 4:
                    System.out.println("\n┌─────────────────────────────────────────────┐");
                    System.out.println("│         Volviendo al menú anterior...        │");
                    System.out.println("└──────────────────────────────────────────────┘");
                    break;
                default:
                    System.out.println("\n┌─────────────────────────────────────────────┐");
                    System.out.println("│               Opción inválida                │");
                    System.out.println("└──────────────────────────────────────────────┘");
            }
        } while (opcion != 4);
    }

    private void menuGestionNotasAdmin() {
        int opcion;
        do {
            System.out.println();
            System.out.println("┌─────────────────────────────────────────────┐");
            System.out.println("│           GESTIÓN DE NOTAS (ADMIN)          │");
            System.out.println("├─────────────────────────────────────────────┤");
            System.out.println("│ 1. Agregar nota                             │");
            System.out.println("│ 2. Editar nota                              │");
            System.out.println("│ 3. Eliminar nota                            │");
            System.out.println("│ 4. Ver notas de un estudiante por           │");
            System.out.println("│    componente                               │");
            System.out.println("│ 5. Volver                                   │");
            System.out.println("└─────────────────────────────────────────────┘");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    agregarNotaAEstudiante();
                    break;
                case 2:
                    editarNotaDeEstudiante();
                    break;
                case 3:
                    eliminarNotaDeEstudiante();
                    break;
                case 4:
                    verNotasEstudiantePorComponente();
                    break;
                case 5:
                    System.out.println();
                    System.out.println("┌─────────────────────────────────────────────┐");
                    System.out.println("│                  Volver                     │");
                    System.out.println("├─────────────────────────────────────────────┤");
                    System.out.println("│         Volviendo al menú anterior...       │");
                    System.out.println("└─────────────────────────────────────────────┘");
                    break;
                default:
                    System.out.println();
                    System.out.println("┌─────────────────────────────────────────────┐");
                    System.out.println("│                   Error                     │");
                    System.out.println("├─────────────────────────────────────────────┤");
                    System.out.println("│              Opción inválida                │");
                    System.out.println("└─────────────────────────────────────────────┘");
            }
        } while (opcion != 5);
    }

    private void menuGestionNotasProfesor(Profesor profesor) {
        int opcion;
        do {
            System.out.println("\n┌─────────────────────────────────────────────┐");
            System.out.println("│         GESTIÓN DE NOTAS (PROFESOR)          │");
            System.out.println("├──────────────────────────────────────────────┤");
            System.out.println("│ 1. Agregar nota                              │");
            System.out.println("│ 2. Editar nota                               │");
            System.out.println("│ 3. Eliminar nota                             │");
            System.out.println("│ 4. Ver notas de un estudiante por comp.      │");
            System.out.println("│ 5. Volver                                    │");
            System.out.println("└──────────────────────────────────────────────┘");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    agregarNotaAEstudiante();
                    break;
                case 2:
                    editarNotaDeEstudiante();
                    break;
                case 3:
                    eliminarNotaDeEstudiante();
                    break;
                case 4:
                    verNotasEstudiantePorComponente();
                    break;
                case 5:
                    System.out.println("\n┌─────────────────────────────────────────────┐");
                    System.out.println("│        Volviendo al menú anterior...         │");
                    System.out.println("└──────────────────────────────────────────────┘");
                    break;
                default:
                    System.out.println("\n┌─────────────────────────────────────────────┐");
                    System.out.println("│               Opción inválida                │");
                    System.out.println("└──────────────────────────────────────────────┘");
            }
        } while (opcion != 5);
    }

    // ==========================================================
    // =============== REGISTRO Y CONSULTA DE USUARIOS ==========
    // ==========================================================

    private void registrarUsuarioComoAdmin() {
        String continuar;
        do {
            System.out.println();
            System.out.println("┌─────────────────────────────────────────────┐");
            System.out.println("│           Registro de Nuevo Usuario         │");
            System.out.println("├─────────────────────────────────────────────┤");
            System.out.println("│ 1. Administrador                            │");
            System.out.println("│ 2. Profesor                                 │");
            System.out.println("│ 3. Estudiante                               │");
            System.out.println("└─────────────────────────────────────────────┘");
            System.out.print("Opción: ");
            int tipo = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Ingrese nombre: ");
            String nombre = scanner.nextLine();

            System.out.print("Ingrese apellido: ");
            String apellido = scanner.nextLine();

            System.out.print("Ingrese contraseña: ");
            String passStr = scanner.nextLine();
            char[] password = passStr.toCharArray();

            if (tipo == 1) {
                Administrador admin = new Administrador(nombre, apellido, passStr);
                sistema.registrarAdministrador(admin);
                System.out.println();
                System.out.println("┌─────────────────────────────────────────────┐");
                System.out.println("│              Registro Exitoso               │");
                System.out.println("├─────────────────────────────────────────────┤");
                System.out.println("│ Administrador registrado exitosamente       │");
                System.out.println("│ ID: " + String.format("%-38s", admin.getId()) + " │");
                System.out.println("└─────────────────────────────────────────────┘");
            } else if (tipo == 2) {
                Profesor profe = new Profesor(nombre, apellido, password);
                sistema.registrarProfesor(profe);
                System.out.println();
                System.out.println("┌─────────────────────────────────────────────┐");
                System.out.println("│              Registro Exitoso               │");
                System.out.println("├─────────────────────────────────────────────┤");
                System.out.println("│ Profesor registrado exitosamente            │");
                System.out.println("│ ID: " + String.format("%-38s", profe.getId()) + " │");
                System.out.println("└─────────────────────────────────────────────┘");
            } else if (tipo == 3) {
                Estudiante est = new Estudiante(nombre, apellido, password);
                sistema.registrarEstudiante(est);
                System.out.println();
                System.out.println("┌─────────────────────────────────────────────┐");
                System.out.println("│              Registro Exitoso               │");
                System.out.println("├─────────────────────────────────────────────┤");
                System.out.println("│ Estudiante registrado exitosamente          │");
                System.out.println("│ ID: " + String.format("%-38s", est.getId()) + " │");
                System.out.println("└─────────────────────────────────────────────┘");
            } else {
                System.out.println();
                System.out.println("┌─────────────────────────────────────────────┐");
                System.out.println("│                   Error                     │");
                System.out.println("├─────────────────────────────────────────────┤");
                System.out.println("│              Opción inválida                │");
                System.out.println("└─────────────────────────────────────────────┘");
            }

            System.out.print("¿Desea registrar otro usuario? (s/n): ");
            continuar = scanner.nextLine().trim().toLowerCase();
        } while (continuar.equals("s"));
    }

    /**
     * Registrar usuario desde menú de Profesor:
     * Solo puede registrar estudiantes o a sí mismo (profesor).
     */
    private void registrarUsuario() {
        System.out.println();
        System.out.println("┌─────────────────────────────────────────────┐");
        System.out.println("│           Registro de Nuevo Usuario         │");
        System.out.println("├─────────────────────────────────────────────┤");
        System.out.println("│ 1. Profesor                                 │");
        System.out.println("│ 2. Estudiante                               │");
        System.out.println("└─────────────────────────────────────────────┘");
        System.out.print("Opción: ");
        int tipo = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Ingrese nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Ingrese apellido: ");
        String apellido = scanner.nextLine();

        System.out.print("Ingrese contraseña: ");
        String passStr = scanner.nextLine();
        char[] password = passStr.toCharArray();

        Usuario nuevoUsuario = null;

        if (tipo == 1) {
            Profesor profe = new Profesor(nombre, apellido, password);
            sistema.registrarProfesor(profe);
            nuevoUsuario = profe;
            System.out.println();
            System.out.println("┌─────────────────────────────────────────────┐");
            System.out.println("│              Registro Exitoso               │");
            System.out.println("├─────────────────────────────────────────────┤");
            System.out.println("│ Profesor registrado exitosamente            │");
            System.out.println("└─────────────────────────────────────────────┘");
        } else if (tipo == 2) {
            Estudiante est = new Estudiante(nombre, apellido, password);
            sistema.registrarEstudiante(est);
            nuevoUsuario = est;
            System.out.println();
            System.out.println("┌─────────────────────────────────────────────┐");
            System.out.println("│              Registro Exitoso               │");
            System.out.println("├─────────────────────────────────────────────┤");
            System.out.println("│ Estudiante registrado exitosamente          │");
            System.out.println("└─────────────────────────────────────────────┘");
        } else {
            System.out.println();
            System.out.println("┌─────────────────────────────────────────────┐");
            System.out.println("│                   Error                     │");
            System.out.println("├─────────────────────────────────────────────┤");
            System.out.println("│              Opción inválida                │");
            System.out.println("└─────────────────────────────────────────────┘");
            return;
        }

        // Mostrar datos y auto-login
        System.out.println();
        System.out.println("┌─────────────────────────────────────────────┐");
        System.out.println("│             Datos de Registro               │");
        System.out.println("├─────────────────────────────────────────────┤");
        System.out.println("│ Su ID es: " + String.format("%-32s", nuevoUsuario.getId()) + " │");
        System.out.println("│ Nombre: " + String.format("%-34s", nuevoUsuario.getNombre()) + " │");
        System.out.println("│ Contraseña: " + String.format("%-30s", passStr) + " │");
        System.out.println("└─────────────────────────────────────────────┘");
        iniciarSesion(nuevoUsuario.getId(), password);
    }

    private void registrarUsuarioProfesor(Profesor profesorActual) {
        System.out.println();
        System.out.println("┌─────────────────────────────────────────────┐");
        System.out.println("│           Registro de Nuevo Usuario         │");
        System.out.println("├─────────────────────────────────────────────┤");
        System.out.println("│ 1. Profesor (usted mismo)                   │");
        System.out.println("│ 2. Estudiante                               │");
        System.out.println("└─────────────────────────────────────────────┘");
        System.out.print("Opción: ");
        int tipo = scanner.nextInt();
        scanner.nextLine();

        if (tipo == 1) {
            System.out.println();
            System.out.println("┌─────────────────────────────────────────────┐");
            System.out.println("│        Registrando como Profesor            │");
            System.out.println("└─────────────────────────────────────────────┘");
            System.out.print("Ingrese nombre: ");
            String nombre = scanner.nextLine();

            System.out.print("Ingrese apellido: ");
            String apellido = scanner.nextLine();

            System.out.print("Ingrese contraseña: ");
            String passStr = scanner.nextLine();
            char[] password = passStr.toCharArray();

            Profesor profe = new Profesor(nombre, apellido, password);
            sistema.registrarProfesor(profe);
            System.out.println();
            System.out.println("┌─────────────────────────────────────────────┐");
            System.out.println("│              Registro Exitoso               │");
            System.out.println("├─────────────────────────────────────────────┤");
            System.out.println("│ Profesor registrado exitosamente            │");
            System.out.println("└─────────────────────────────────────────────┘");
        } else if (tipo == 2) {
            System.out.print("Ingrese nombre del estudiante: ");
            String nombre = scanner.nextLine();

            System.out.print("Ingrese apellido del estudiante: ");
            String apellido = scanner.nextLine();

            System.out.print("Ingrese contraseña del estudiante: ");
            String passStr = scanner.nextLine();
            char[] password = passStr.toCharArray();

            Estudiante est = new Estudiante(nombre, apellido, password);
            sistema.registrarEstudiante(est);
            System.out.println();
            System.out.println("┌─────────────────────────────────────────────┐");
            System.out.println("│              Registro Exitoso               │");
            System.out.println("├─────────────────────────────────────────────┤");
            System.out.println("│  Estudiante registrado exitosamente         │");
            System.out.println("└─────────────────────────────────────────────┘");
        } else {
            System.out.println();
            System.out.println("┌─────────────────────────────────────────────┐");
            System.out.println("│                   Error                     │");
            System.out.println("├─────────────────────────────────────────────┤");
            System.out.println("│ Opción inválida. Solo puede registrar       │");
            System.out.println("│ profesores (usted mismo) o estudiantes      │");
            System.out.println("└─────────────────────────────────────────────┘");
        }
    }

    /**
     * Consultar todos los usuarios: muestra todos los administradores, profesores y
     * estudiantes.
     */
    private void consultarTodosLosUsuarios(Usuario actual) {
        if (actual instanceof Administrador) {
            // Mostrar todos
            System.out.println();
            System.out.println("┌─────────────────────────────────────────────┐");
            System.out.println("│             Lista de Administradores        │");
            System.out.println("├─────────────────────────────────────────────┤");
            for (Administrador a : sistema.obtenerAdministradores()) {
                System.out.println("│ " + String.format("%-43s", a.toString()) + " │");
            }
            System.out.println("└─────────────────────────────────────────────┘");
        }

        System.out.println();
        System.out.println("┌─────────────────────────────────────────────┐");
        System.out.println("│               Lista de Profesores           │");
        System.out.println("├─────────────────────────────────────────────┤");
        for (Profesor p : sistema.obtenerProfesores()) {
            System.out.println("│ " + String.format("%-43s", p.toString()) + " │");
        }
        System.out.println("└─────────────────────────────────────────────┘");

        System.out.println();
        System.out.println("┌─────────────────────────────────────────────┐");
        System.out.println("│               Lista de Estudiantes          │");
        System.out.println("├─────────────────────────────────────────────┤");
        for (Estudiante e : sistema.obtenerEstudiantes()) {
            System.out.println("│ " + String.format("%-43s", e.toString()) + " │");
        }
        System.out.println("└─────────────────────────────────────────────┘");
    }

    /**
     * Consultar un usuario por ID.
     */
    private void consultarUsuarioPorId() {
        System.out.print("Ingrese ID del usuario a consultar: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Usuario usuario = sistema.obtenerUsuarioPorId(id);
        if (usuario != null) {
            System.out.println();
            System.out.println("┌─────────────────────────────────────────────┐");
            System.out.println("│              Usuario Encontrado             │");
            System.out.println("├─────────────────────────────────────────────┤");
            System.out.println("│ " + String.format("%-43s", usuario.toString()) + " │");
            System.out.println("└─────────────────────────────────────────────┘");
        } else {
            System.out.println();
            System.out.println("┌─────────────────────────────────────────────┐");
            System.out.println("│                   Error                     │");
            System.out.println("├─────────────────────────────────────────────┤");
            System.out.println("│ Usuario no encontrado con ID: " + String.format("%-12s", id) + " │");
            System.out.println("└─────────────────────────────────────────────┘");
        }
    }

    /**
     * Eliminar usuario por ID (solo admins pueden hacerlo).
     */
    private void eliminarUsuarioPorId() {
        System.out.print("Ingrese el ID del usuario a eliminar: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        boolean eliminado = sistema.eliminarUsuarioPorId(id);
        if (eliminado) {
            System.out.println();
            System.out.println("┌─────────────────────────────────────────────┐");
            System.out.println("│              Operación Exitosa              │");
            System.out.println("├─────────────────────────────────────────────┤");
            System.out.println("│        Usuario eliminado correctamente      │");
            System.out.println("└─────────────────────────────────────────────┘");
        } else {
            System.out.println();
            System.out.println("┌─────────────────────────────────────────────┐");
            System.out.println("│                   Error                     │");
            System.out.println("├─────────────────────────────────────────────┤");
            System.out.println("│      No se encontró usuario con ese ID      │");
            System.out.println("└─────────────────────────────────────────────┘");
        }
    }

    // ==========================================================
    // ================== GESTIÓN DE COMPONENTES ================
    // ==========================================================

    public void registrarComponente() {
        System.out.print("Ingrese el nombre del componente: ");
        String nombre = scanner.nextLine();

        System.out.print("Ingrese el ID del profesor asignado: ");
        int idProfesor = scanner.nextInt();
        scanner.nextLine();

        Profesor profesor = null;
        for (Profesor p : sistema.obtenerProfesores()) {
            if (p.getId() == idProfesor) {
                profesor = p;
                break;
            }
        }
        if (profesor == null) {
            System.out.println("\n┌─────────────────────────────────────────────┐");
            System.out.println("│           Profesor no encontrado             │");
            System.out.println("└──────────────────────────────────────────────┘");
            return;
        }

        gestorComponentes.registrarComponente(nombre, profesor);
    }

    private void asociarEstudianteAComponente() {
        System.out.print("Ingrese el ID del componente: ");
        int idComponente = scanner.nextInt();
        scanner.nextLine();

        Componente componente = gestorComponentes.buscarComponentePorId(idComponente);
        if (componente == null) {
            System.out.println("\n┌─────────────────────────────────────────────┐");
            System.out.println("│           Componente no encontrado           │");
            System.out.println("└──────────────────────────────────────────────┘");
            return;
        }

        String continuar;
        do {
            System.out.print("Ingrese el ID del estudiante: ");
            int idEstudiante = scanner.nextInt();
            scanner.nextLine();

            Estudiante estudiante = null;
            for (Estudiante e : sistema.obtenerEstudiantes()) {
                if (e.getId() == idEstudiante) {
                    estudiante = e;
                    break;
                }
            }
            if (estudiante == null) {
                System.out.println("\n┌─────────────────────────────────────────────┐");
                System.out.println("│            Estudiante no encontrado          │");
                System.out.println("└──────────────────────────────────────────────┘");
            } else {
                if (componente.agregarEstudiante(estudiante)) {
                    System.out.println("\n┌─────────────────────────────────────────────┐");
                    System.out.println("│  Estudiante asociado correctamente al comp.  │");
                    System.out.println("└──────────────────────────────────────────────┘");
                } else {
                    System.out.println("\n┌─────────────────────────────────────────────┐");
                    System.out.println("│  El estudiante ya está asociado a este comp. │");
                    System.out.println("└──────────────────────────────────────────────┘");
                }
            }

            System.out.print("¿Desea asociar otro estudiante a este componente? (s/n): ");
            continuar = scanner.nextLine().trim().toLowerCase();
        } while (continuar.equals("s"));
    }

    // ==========================================================
    // ================== GESTIÓN DE NOTAS ======================
    // ==========================================================

    private void agregarNotaAEstudiante() {
        System.out.print("ID del estudiante: ");
        int idEst = scanner.nextInt();
        scanner.nextLine();
        Estudiante estudiante = buscarEstudiantePorId(idEst);

        System.out.print("ID del componente: ");
        int idComp = scanner.nextInt();
        scanner.nextLine();
        Componente componente = gestorComponentes.buscarComponentePorId(idComp);

        System.out.print("Valor de la nota: ");
        double valorNota = scanner.nextDouble();
        System.out.print("Porcentaje: ");
        int porcentaje = scanner.nextInt();
        scanner.nextLine();

        Nota nota = new Nota(valorNota, porcentaje);
        estudiante.agregarNota(componente, nota);

        System.out.println("\n┌─────────────────────────────────────────────┐");
        System.out.println("│           Nota agregada correctamente        │");
        System.out.println("└──────────────────────────────────────────────┘");
    }

    private void mostrarNotasPorComponente(Estudiante estudiante, Componente componente) {
        List<Nota> notas = estudiante.getNotasPorComponente(componente);

        System.out.println("\n┌─────────────────────────────────────────────────────────┐");
        System.out.println("│                    NOTAS DEL ESTUDIANTE                 │");
        System.out.println("├─────────────────────────────────────────────────────────┤");
        System.out.println("│  Valor   │ Porcentaje │ Valor Porcentaje            │");
        System.out.println("├─────────────────────────────────────────────────────────┤");

        double suma = 0;
        for (Nota n : notas) {
            double vnp = n.getValorNotaPorcentaje();
            suma += vnp;
            System.out.printf("│  %-7.2f│    %-7d│  %-26.2f│\n",
                    n.getValorNota(), n.getPorcentaje(), vnp);
        }

        System.out.println("├─────────────────────────────────────────────────────────┤");
        System.out.printf("│ Nota Final: %-43.2f│\n", suma);
        System.out.printf("│ Cantidad de Notas: %-33d│\n", notas.size());
        System.out.println("└─────────────────────────────────────────────────────────┘");
    }

    private void editarNotaDeEstudiante() {
        System.out.print("ID del estudiante: ");
        int idEst = scanner.nextInt();
        scanner.nextLine();
        Estudiante estudiante = buscarEstudiantePorId(idEst);

        if (estudiante == null) {
            System.out.println("\n┌─────────────────────────────────────────────┐");
            System.out.println("│           Estudiante no encontrado           │");
            System.out.println("└──────────────────────────────────────────────┘");
            return;
        }

        System.out.print("ID del componente: ");
        int idComp = scanner.nextInt();
        scanner.nextLine();
        Componente componente = gestorComponentes.buscarComponentePorId(idComp);

        if (componente == null) {
            System.out.println("\n┌─────────────────────────────────────────────┐");
            System.out.println("│           Componente no encontrado           │");
            System.out.println("└──────────────────────────────────────────────┘");
            return;
        }

        List<Nota> notas = estudiante.getNotasPorComponente(componente);
        if (notas.isEmpty()) {
            System.out.println("\n┌─────────────────────────────────────────────┐");
            System.out.println("│      No hay notas para este componente       │");
            System.out.println("└──────────────────────────────────────────────┘");
            return;
        }

        System.out.println("\n┌─────────────────────────────────────────────────────────┐");
        System.out.println("│                   NOTAS ACTUALES                        │");
        System.out.println("├─────────────────────────────────────────────────────────┤");
        System.out.println("│ No. │  Valor   │ Porcentaje                          │");
        System.out.println("├─────────────────────────────────────────────────────────┤");

        for (int i = 0; i < notas.size(); i++) {
            Nota n = notas.get(i);
            System.out.printf("│ %-3d │  %-7.2f│    %-31d│\n",
                    i + 1, n.getValorNota(), n.getPorcentaje());
        }
        System.out.println("└─────────────────────────────────────────────────────────┘");

        System.out.print("Seleccione el número de la nota a editar: ");
        int idx = scanner.nextInt() - 1;
        scanner.nextLine();

        if (idx < 0 || idx >= notas.size()) {
            System.out.println("\n┌─────────────────────────────────────────────┐");
            System.out.println("│            Selección inválida                │");
            System.out.println("└──────────────────────────────────────────────┘");
            return;
        }

        System.out.print("Nuevo valor de la nota: ");
        double nuevoValor = scanner.nextDouble();
        System.out.print("Nuevo porcentaje: ");
        int nuevoPorcentaje = scanner.nextInt();
        scanner.nextLine();

        notas.set(idx, new Nota(nuevoValor, nuevoPorcentaje));

        System.out.println("\n┌─────────────────────────────────────────────┐");
        System.out.println("│         Nota editada correctamente           │");
        System.out.println("└──────────────────────────────────────────────┘");
    }

    private void eliminarNotaDeEstudiante() {
        System.out.print("ID del estudiante: ");
        int idEst = scanner.nextInt();
        scanner.nextLine();
        Estudiante estudiante = buscarEstudiantePorId(idEst);

        if (estudiante == null) {
            System.out.println("\n┌─────────────────────────────────────────────┐");
            System.out.println("│          Estudiante no encontrado           │");
            System.out.println("└─────────────────────────────────────────────┘");
            return;
        }

        System.out.print("ID del componente: ");
        int idComp = scanner.nextInt();
        scanner.nextLine();
        Componente componente = gestorComponentes.buscarComponentePorId(idComp);

        if (componente == null) {
            System.out.println("\n┌─────────────────────────────────────────────┐");
            System.out.println("│           Componente no encontrado           │");
            System.out.println("└──────────────────────────────────────────────┘");
            return;
        }

        List<Nota> notas = estudiante.getNotasPorComponente(componente);
        if (notas.isEmpty()) {
            System.out.println("\n┌─────────────────────────────────────────────┐");
            System.out.println("│      No hay notas para este componente       │");
            System.out.println("└──────────────────────────────────────────────┘");
            return;
        }

        System.out.println("\n┌─────────────────────────────────────────────────────────┐");
        System.out.println("│                   NOTAS ACTUALES                        │");
        System.out.println("├─────────────────────────────────────────────────────────┤");
        System.out.println("│ No. │  Valor   │ Porcentaje                          │");
        System.out.println("├─────────────────────────────────────────────────────────┤");

        for (int i = 0; i < notas.size(); i++) {
            Nota n = notas.get(i);
            System.out.printf("│ %-3d │  %-7.2f│    %-31d│\n",
                    i + 1, n.getValorNota(), n.getPorcentaje());
        }
        System.out.println("└─────────────────────────────────────────────────────────┘");

        System.out.print("Seleccione el número de la nota a eliminar: ");
        int idx = scanner.nextInt() - 1;
        scanner.nextLine();

        if (idx < 0 || idx >= notas.size()) {
            System.out.println("\n┌─────────────────────────────────────────────┐");
            System.out.println("│            Selección inválida                │");
            System.out.println("└──────────────────────────────────────────────┘");
            return;
        }

        notas.remove(idx);

        System.out.println("\n┌─────────────────────────────────────────────┐");
        System.out.println("│        Nota eliminada correctamente          │");
        System.out.println("└──────────────────────────────────────────────┘");
    }

    private void verMisNotasPorComponente(Estudiante estudiante) {
        System.out.print("Ingrese el ID del componente: ");
        int idComp = scanner.nextInt();
        scanner.nextLine();
        Componente componente = gestorComponentes.buscarComponentePorId(idComp);

        if (componente == null) {
            System.out.println("\n┌─────────────────────────────────────────────┐");
            System.out.println("│           Componente no encontrado           │");
            System.out.println("└──────────────────────────────────────────────┘");
            return;
        }
        mostrarNotasPorComponente(estudiante, componente);
    }

    private void verNotasEstudiantePorComponente() {
        System.out.print("ID del estudiante: ");
        int idEst = scanner.nextInt();
        scanner.nextLine();
        Estudiante estudiante = buscarEstudiantePorId(idEst);

        if (estudiante == null) {
            System.out.println("\n┌─────────────────────────────────────────────┐");
            System.out.println("│           Estudiante no encontrado           │");
            System.out.println("└──────────────────────────────────────────────┘");
            return;
        }

        System.out.print("ID del componente: ");
        int idComp = scanner.nextInt();
        scanner.nextLine();
        Componente componente = gestorComponentes.buscarComponentePorId(idComp);

        if (componente == null) {
            System.out.println("\n┌─────────────────────────────────────────────┐");
            System.out.println("│           Componente no encontrado           │");
            System.out.println("└──────────────────────────────────────────────┘");
            return;
        }
        mostrarNotasPorComponente(estudiante, componente);
    }

    private void registrarEstudiantesPorProfesor() {
        System.out.println();
        System.out.println("┌─────────────────────────────────────────────┐");
        System.out.println("│        Registro de Nuevo Estudiante         │");
        System.out.println("├─────────────────────────────────────────────┤");
        System.out.print("Ingrese nombre del estudiante: ");
        String nombre = scanner.nextLine();

        System.out.print("Ingrese apellido del estudiante: ");
        String apellido = scanner.nextLine();

        System.out.print("Ingrese contraseña del estudiante: ");
        String passStr = scanner.nextLine();
        char[] password = passStr.toCharArray();

        Estudiante est = new Estudiante(nombre, apellido, password);
        sistema.registrarEstudiante(est);

        System.out.println();
        System.out.println("┌─────────────────────────────────────────────┐");
        System.out.println("│     Estudiante registrado exitosamente      │");
        System.out.println("│ ID: " + String.format("%-38s", est.getId()) + " │");
        System.out.println("└─────────────────────────────────────────────┘");
    }

    private Estudiante buscarEstudiantePorId(int id) {
        for (Estudiante e : sistema.obtenerEstudiantes()) {
            if (e.getId() == id) {
                return e;
            }
        }
        return null;
    }
}