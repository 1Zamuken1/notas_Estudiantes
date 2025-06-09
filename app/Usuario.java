package app;
import java.util.Arrays;

public class Usuario {
    private int id;
    protected static int contadorId = 1;
    private String nombre;
    private String apellido;
    private Rol rol;
    private char[] password;

    public Usuario(int id, String nombre, String apellido, Rol rol, char[] password) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.rol = rol;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public char[] getPassword() {
        return password;
    }

    public void setPassword(char[] password) {
        this.password = password;
    }

    public void clearPassword() {
        if (password != null) {
            Arrays.fill(password, '\0'); // Llena el arreglo con caracteres nulos
        }
    }

    public boolean verificarPassword(char[] password) {
        if (this.password == null || password == null) {
            return false;
        }
        if (this.password.length != password.length) {
            return false;
        }
        for (int i = 0; i < this.password.length; i++) {
            if (this.password[i] != password[i]) {
                return false;
            }
        }
        return true;
    }

}
