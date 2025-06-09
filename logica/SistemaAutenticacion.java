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

        Administrador adminDefault = new Administrador("Admin", "123");
        administradores.add(adminDefault);

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
