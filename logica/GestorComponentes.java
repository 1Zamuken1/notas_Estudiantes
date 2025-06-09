package logica;
import java.util.ArrayList;
import java.util.List;

import app.Profesor;

public class GestorComponentes {
    private List<Componente> componentes;

    public GestorComponentes() {
        this.componentes = new ArrayList<>();
    }

    // Registrar un nuevo componente con profesor asignado
    public void registrarComponente(String nombre, Profesor profesor) {
        Componente componente = new Componente(nombre, profesor);
        componentes.add(componente);
        
        // Tabla de confirmación de registro
        System.out.println("┌──────────────────────────────────────────────────────┐");
        System.out.println("│                COMPONENTE REGISTRADO                 │");
        System.out.println("├──────────────────────────────────────────────────────┤");
        System.out.println("│ ID: " + String.format("%-47s", componente.getId()) + "│");
        System.out.println("│ Nombre: " + String.format("%-43s", componente.getNombre()) + "│");
        System.out.println("│ Profesor: " + String.format("%-41s", 
                          (profesor != null ? profesor.getNombre() : "Sin asignar")) + "│");
        System.out.println("└──────────────────────────────────────────────────────┘");
    }

    // Listar todos los componentes
    public void listarComponentes() {
        if (componentes.isEmpty()) {
            System.out.println("┌──────────────────────────────────────────────────────┐");
            System.out.println("│                      ATENCIÓN                       │");
            System.out.println("├──────────────────────────────────────────────────────┤");
            System.out.println("│            No hay componentes registrados           │");
            System.out.println("└──────────────────────────────────────────────────────┘");
            return;
        }

        System.out.println("┌──────┬─────────────────────────┬─────────────────────────┐");
        System.out.println("│  ID  │         NOMBRE          │        PROFESOR         │");
        System.out.println("├──────┼─────────────────────────┼─────────────────────────┤");
        
        for (Componente c : componentes) {
            String profesorNombre = (c.getProfesor() != null) ? c.getProfesor().getNombre() : "Sin asignar";
            System.out.println("│ " + String.format("%-4d", c.getId()) + 
                             " │ " + String.format("%-23s", truncarTexto(c.getNombre(), 23)) + 
                             " │ " + String.format("%-23s", truncarTexto(profesorNombre, 23)) + " │");
        }
        
        System.out.println("└──────┴─────────────────────────┴─────────────────────────┘");
    }

    // Buscar componente por ID
    public Componente buscarComponentePorId(int id) {
        for (Componente c : componentes) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null;
    }

    // Editar un componente (nombre y/o profesor)
    public boolean editarComponente(int id, String nuevoNombre, Profesor nuevoProfesor) {
        Componente componente = buscarComponentePorId(id);
        if (componente != null) {
            // Verifica si ya existe un componente con el mismo id y nombre (excepto el actual)
            for (Componente c : componentes) {
                if (c != componente && c.getId() == id && c.getNombre().equalsIgnoreCase(nuevoNombre)) {
                    System.out.println("┌──────────────────────────────────────────────────────┐");
                    System.out.println("│                        ERROR                         │");
                    System.out.println("├──────────────────────────────────────────────────────┤");
                    System.out.println("│      Ya existe un componente con el mismo ID        │");
                    System.out.println("│                     y nombre                         │");
                    System.out.println("└──────────────────────────────────────────────────────┘");
                    return false;
                }
            }
            
            String nombreAnterior = componente.getNombre();
            String profesorAnterior = (componente.getProfesor() != null) ? 
                                    componente.getProfesor().getNombre() : "Sin asignar";
            
            if (nuevoNombre != null && !nuevoNombre.isEmpty()) {
                componente.setNombre(nuevoNombre);
            }
            if (nuevoProfesor != null) {
                componente.setProfesor(nuevoProfesor);
            }
            
            // Tabla de confirmación de actualización
            System.out.println("┌──────────────────────────────────────────────────────┐");
            System.out.println("│               COMPONENTE ACTUALIZADO                 │");
            System.out.println("├──────────────────────────────────────────────────────┤");
            System.out.println("│ ID: " + String.format("%-47s", componente.getId()) + "│");
            System.out.println("├──────────────────────────────────────────────────────┤");
            System.out.println("│ Nombre anterior: " + String.format("%-34s", truncarTexto(nombreAnterior, 34)) + "│");
            System.out.println("│ Nombre actual: " + String.format("%-36s", truncarTexto(componente.getNombre(), 36)) + "│");
            System.out.println("├──────────────────────────────────────────────────────┤");
            System.out.println("│ Profesor anterior: " + String.format("%-32s", truncarTexto(profesorAnterior, 32)) + "│");
            System.out.println("│ Profesor actual: " + String.format("%-34s", 
                              truncarTexto((componente.getProfesor() != null ? 
                              componente.getProfesor().getNombre() : "Sin asignar"), 34)) + "│");
            System.out.println("└──────────────────────────────────────────────────────┘");
            return true;
        }
        return false;
    }

    // Eliminar componente por ID
    public boolean eliminarComponente(int id) {
        Componente componente = buscarComponentePorId(id);
        if (componente != null) {
            componentes.remove(componente);
            
            System.out.println("┌──────────────────────────────────────────────────────┐");
            System.out.println("│               COMPONENTE ELIMINADO                   │");
            System.out.println("├──────────────────────────────────────────────────────┤");
            System.out.println("│ ID eliminado: " + String.format("%-37s", id) + "│");
            System.out.println("│ Nombre: " + String.format("%-43s", truncarTexto(componente.getNombre(), 43)) + "│");
            System.out.println("└──────────────────────────────────────────────────────┘");
            return true;
        }
        return false;
    }

    // Obtener componentes asignados a un profesor (uso de vista del profesor)
    public List<Componente> obtenerComponentesPorProfesor(int idProfesor) {
        List<Componente> resultado = new ArrayList<>();
        for (Componente c : componentes) {
            if (c.getProfesor() != null && c.getProfesor().getId() == idProfesor) {
                resultado.add(c);
            }
        }
        return resultado;
    }

    // Getter general por si necesitas acceso directo
    public List<Componente> getComponentes() {
        return componentes;
    }

    public List<Componente> obtenerTodosLosComponentes() {
        return new ArrayList<>(componentes);
    }

    // Método auxiliar para truncar texto si es demasiado largo
    private String truncarTexto(String texto, int longitudMaxima) {
        if (texto == null) return "";
        if (texto.length() <= longitudMaxima) {
            return texto;
        }
        return texto.substring(0, longitudMaxima - 3) + "...";
    }
}