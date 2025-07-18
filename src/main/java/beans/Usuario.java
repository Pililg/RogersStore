package beans;

public class Usuario {
    private String id_usuario;
    private String contrasena;
    private String nombre;
    private String apellido;
    private String correo;
    private String telefono;
    private String direccion;  

    public Usuario(String id_usuario, String contrasena, String nombre, String apellido, String correo, String telefono, String direccion) {
        this.id_usuario = id_usuario;
        this.contrasena = contrasena;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    public String getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(String id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return "Usuario{" + "id_usuario=" + id_usuario + ", contrasena=" + contrasena + ", nombre=" + nombre + ", apellido=" + apellido + ", correo=" + correo + ", telefono=" + telefono + ", direccion=" + direccion + '}';
    }


    
}
