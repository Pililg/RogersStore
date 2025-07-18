package controller;

import java.util.Map;

public interface IUsuarioController {

    public String login(String id_usuario, String contrasena);

    public String register(String id_usuario, String contrasena,
            String nombre, String apellido, String correo, String telefono, String direccion);

    public String pedir(String id_usuario);

    public String modificar(String id_usuario, String nuevaContrasena,
            String nuevoNombre, String nuevoApellido, String nuevoCorreo,
            String nuevoTelefono, String nuevaDireccion);

    public String verStock(String id_usuario);

    public String devolverProducto(String id_usuario, Map<Integer, Integer> stock);

    public String eliminar(String id_usuario);


}
