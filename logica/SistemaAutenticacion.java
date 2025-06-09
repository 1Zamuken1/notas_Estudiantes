package logica;
import java.util.ArrayList;
import java.util.List;

import app.Administrador;
import app.Estudiante;
import app.Profesor;
import app.Usuario;

public class SistemaAutenticacion {
    private List<Administrador> administradores;
    private List<Profesor> profesores;
    private List<Estudiante> estudiantes;

    public SistemaAutenticacion() {
        administradores = new ArrayList<>();
        profesores = new ArrayList<>();
        estudiantes = new ArrayList<>();

        // Crear usuarios por defecto
        // Admin
        Administrador adminDefault = new Administrador("Admin", "123");
        administradores.add(adminDefault);

        // Profesores
        Profesor profesorDefault = new Profesor("Laura", "López", "123".toCharArray());
        profesores.add(profesorDefault);
        Profesor profesorDefault2 = new Profesor("Juan", "Pérez", "123".toCharArray());
        profesores.add(profesorDefault2);
        Profesor profesorDefault3 = new Profesor("Ana", "Martínez", "123".toCharArray());
        profesores.add(profesorDefault3);
        Profesor profesorDefault4 = new Profesor("Luis", "García", "123".toCharArray());
        profesores.add(profesorDefault4);
        Profesor profesorDefault5 = new Profesor("Marta", "Fernández", "123".toCharArray());
        profesores.add(profesorDefault5);

        // Estudiantes
        Estudiante estudianteDefault = new Estudiante("Carlos", "Gómez", "123".toCharArray());
        estudiantes.add(estudianteDefault);
        Estudiante estudianteDefault2 = new Estudiante("Sofía", "Ramírez", "123".toCharArray());
        estudiantes.add(estudianteDefault2);
        Estudiante estudianteDefault3 = new Estudiante("Diego", "Torres", "123".toCharArray());
        estudiantes.add(estudianteDefault3);
        Estudiante estudianteDefault4 = new Estudiante("Lucía", "Hernández", "123".toCharArray());
        estudiantes.add(estudianteDefault4);
        Estudiante estudianteDefault5 = new Estudiante("Andrés", "Vázquez", "123".toCharArray());
        estudiantes.add(estudianteDefault5);
        Estudiante estudianteDefault6 = new Estudiante("Valentina", "Morales", "123".toCharArray());
        estudiantes.add(estudianteDefault6);
        Estudiante estudianteDefault7 = new Estudiante("Mateo", "Jiménez", "123".toCharArray());
        estudiantes.add(estudianteDefault7);
        Estudiante estudianteDefault8 = new Estudiante("Camila", "Rojas", "123".toCharArray());
        estudiantes.add(estudianteDefault8);
        Estudiante estudianteDefault9 = new Estudiante("Sebastián", "Cruz", "123".toCharArray());
        estudiantes.add(estudianteDefault9);
        Estudiante estudianteDefault10 = new Estudiante("Isabella", "Castillo", "123".toCharArray());
        estudiantes.add(estudianteDefault10);
    }

    // Métodos para obtener listas
    public List<Administrador> obtenerAdministradores() {
        return administradores;
    }

    public List<Profesor> obtenerProfesores() {
        return profesores;
    }

    public List<Estudiante> obtenerEstudiantes() {
        return estudiantes;
    }

    /**
     * Obtener usuario por ID: busca en todos los tipos de usuarios
     */
    public Usuario obtenerUsuarioPorId(int id) {
        for (Administrador a : administradores) {
            if (a.getId() == id) {
                return a;
            }
        }
        for (Profesor p : profesores) {
            if (p.getId() == id) {
                return p;
            }
        }
        for (Estudiante e : estudiantes) {
            if (e.getId() == id) {
                return e;
            }
        }
        return null;
    }

    /**
     * Validar credenciales para inicio de sesión.
     */
    public boolean validarCredenciales(int id, char[] password) {
        Usuario usuario = obtenerUsuarioPorId(id);
        if (usuario == null)
            return false;
        return usuario.verificarPassword(password);
    }

    // Registrar usuarios
    public void registrarAdministrador(Administrador admin) {
        administradores.add(admin);
    }

    public void registrarProfesor(Profesor profesor) {
    profesores.add(profesor);
    Profesor.agregarProfesor(profesor);
}

    public void registrarEstudiante(Estudiante estudiante) {
        estudiantes.add(estudiante);
        Estudiante.agregarEstudiante(estudiante);
    }

    /**
     * Eliminar usuario por ID, retorna true si eliminado.
     */
    public boolean eliminarUsuarioPorId(int id) {
        for (int i = 0; i < administradores.size(); i++) {
            if (administradores.get(i).getId() == id) {
                administradores.remove(i);
                return true;
            }
        }
        for (int i = 0; i < profesores.size(); i++) {
            if (profesores.get(i).getId() == id) {
                profesores.remove(i);
                return true;
            }
        }
        for (int i = 0; i < estudiantes.size(); i++) {
            if (estudiantes.get(i).getId() == id) {
                estudiantes.remove(i);
                return true;
            }
        }
        return false;
    }
}
